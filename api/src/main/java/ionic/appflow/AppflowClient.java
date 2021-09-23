package ionic.appflow;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ionic.appflow.exception.AppflowException;
import ionic.appflow.exception.AppflowHttpException;
import ionic.appflow.model.App;
import ionic.appflow.model.Automation;
import ionic.appflow.model.Build;
import ionic.appflow.model.Certificate;
import ionic.appflow.model.Channel;
import ionic.appflow.model.Commit;
import ionic.appflow.model.Deployment;
import ionic.appflow.model.Environment;
import ionic.appflow.model.Meta;
import ionic.appflow.model.NativeConfig;
import ionic.appflow.model.Organization;
import ionic.appflow.model.Stack;
import ionic.appflow.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class AppflowClient {

    private static final MediaType MEDIA_TYPE_APPLICATION_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String BASE_URL = "https://api.ionicjs.com";
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public AppflowClient(String token) {
        this.mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.client = new OkHttpClient.Builder().addInterceptor(chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", String.format("Bearer %s", token))
                    .build()
            )).build();
    }

    public List<User> getUsers(final String organizationId) {
        return this.get(String.format("/orgs/%s/users", organizationId), UsersResponse.class);
    }

    public User getUser(final String organizationId, final int userId) {
        return this.getUser(organizationId, String.valueOf(userId));
    }

    public User getUser(final String organizationId, final String userId) {
        return this.get(String.format("/orgs/%s/users/%s", organizationId, userId), UserResponse.class);
    }

    public Organization getOrganization(final String slug) {
        return this.get(String.format("/orgs/%s?slug=true", slug), OrganizationResponse.class);
    }

    public List<App> getApps() {
        return this.get("/apps?page=1&page_size=25&meta_fields=total", AppsResponse.class);
    }

    public App getApp(final String appId) {
        return this.get(String.format("/apps/%s", appId), AppResponse.class);
    }

    public List<Stack> getStacks() {
        return this.get("/stacks", StacksResponse.class);
    }

    public List<NativeConfig> getNativeConfigs(final String appId) {
        return this.get(String.format("/apps/%s/native-configs", appId), NativeConfigsResponse.class);
    }

    public List<Certificate> getSigningCertificates(final String appId) {
        return this.get(String.format("/apps/%s/certificates", appId), CertificatesResponse.class);
    }

    public List<Environment> getEnvironments(final String appId) {
        return this.get(String.format("/apps/%s/environments", appId), EnvironmentsResponse.class);
    }

    public Automation getAutomation(final String appId, final int automationId) {
        return this.getAutomation(appId, String.valueOf(automationId));
    }

    public Automation getAutomation(final String appId, final String automationId) {
        return this.get(String.format("/apps/%s/automations/%s", appId, automationId), AutomationResponse.class);
    }

    public List<Automation> getAutomations(final String appId) {
        return this.get(String.format("/apps/%s/automations", appId), AutomationsResponse.class);
    }

    public Channel getChannel(final String appId, final String channelId) {
        return this.get(String.format("/apps/%s/channels/%s", appId, channelId), ChannelResponse.class);
    }

    public List<Channel> getChannels(final String appId) {
        return this.get(String.format("/apps/%s/channels?page=1&page_size=25&meta_fields=total", appId),
            ChannelsResponse.class);
    }

    public List<Commit> getCommits(final String appId) {
        return this.get(String.format("/apps/%s/commits?page=1&page_size=25&meta_fields=total", appId),
            CommitsResponse.class);
    }

    public Build getBuild(final String appId, final int jobId) {
        return this.getBuild(appId, String.valueOf(jobId));
    }

    public Build getBuild(final String appId, final String jobId) {
        return this.get(String.format("/apps/%s/builds/%s", appId, jobId), BuildResponse.class);
    }

    public List<Build> getBuilds(final String appId) {
        return this.get(String.format("/apps/%s/builds?page=1&page_size=25&meta_fields=total", appId),
            BuildsResponse.class);
    }

    public Deployment getDeployment(final String appId, final int deploymentId) {
        return this.getDeployment(appId, String.valueOf(deploymentId));
    }

    public Deployment getDeployment(final String appId, final String deploymentId) {
        return this.get(String.format("/apps/%s/deployments/%s", appId, deploymentId), DeploymentResponse.class);
    }

    public List<Deployment> getDeployments(final String appId) {
        return this.get(String.format("/apps/%s/deployments?page=1&page_size=25&meta_fields=total", appId),
            DeploymentsResponse.class);
    }

    private <T extends AppflowResponse<D>, D> D get(final String path, final Class<T> clazz) {
        return this.request("GET", path, null, clazz);
    }

    private <T extends AppflowResponse<D>, D, B> D post(final String path, final B body, final Class<T> clazz) {
        try {
            return this.request("POST", path, this.mapper.writeValueAsString(body), clazz);
        } catch (JsonProcessingException e) {
            throw new AppflowException("Could not create request body", e);
        }
    }

    private <T extends AppflowResponse<D>, D> D request(final String method,
                                                        final String path,
                                                        final String requestBody,
                                                        final Class<T> clazz) {
        final String url = BASE_URL + path;
        final Request request = builder(method, requestBody)
            .url(url)
            .build();

        try (Response response = this.client.newCall(request).execute()) {
            final int code = response.code();
            final String body = Objects.requireNonNull(response.body()).string();
            if (!response.isSuccessful()) {
                throw new AppflowHttpException(code, url);
            }

            final T entity = this.mapper.readValue(body, clazz);
            final D data = entity.getData();
            final Meta meta = entity.getMeta();
            log.trace("Response HTTP status: {}, version: {}, body: {}", response.code(), meta.getVersion(), body);
            return data;
        } catch (IOException e) {
            throw new AppflowException("Could not make call to " + url, e);
        }
    }

    private Request.Builder builder(final String method, final String body) {
        Request.Builder builder = new Request.Builder();
        switch (method) {
            case "GET":
                return builder.get();
            case "PUT":
                return builder.put(RequestBody.create(body, MEDIA_TYPE_APPLICATION_JSON));
            case "POST":
                return builder.post(RequestBody.create(body, MEDIA_TYPE_APPLICATION_JSON));
            case "DELETE":
                return builder.delete();
            default:
                throw new IllegalArgumentException("Could not create request for " + method + " method");
        }
    }
}
