package ionic.appflow;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ionic.appflow.dto.AutomationsResponse;
import ionic.appflow.dto.BuildsResponse;
import ionic.appflow.dto.CertificatesResponse;
import ionic.appflow.dto.ChannelsResponse;
import ionic.appflow.dto.CommitsResponse;
import ionic.appflow.dto.DeploymentsResponse;
import ionic.appflow.dto.EnvironmentsResponse;
import ionic.appflow.dto.NativeConfigsResponse;
import ionic.appflow.dto.deployments.Build;
import ionic.appflow.dto.deployments.Deployment;
import ionic.appflow.dto.shared.Automation;
import ionic.appflow.dto.shared.Certificate;
import ionic.appflow.dto.shared.Channel;
import ionic.appflow.dto.shared.Commit;
import ionic.appflow.dto.shared.Environment;
import ionic.appflow.dto.shared.NativeConfig;
import ionic.appflow.exception.AppflowException;
import ionic.appflow.exception.AppflowHttpException;
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

    public List<NativeConfig> getNativeConfigs(final String appId) {
        return this.request("GET", String.format("/apps/%s/native-configs", appId), NativeConfigsResponse.class).getData();
    }

    public List<Certificate> getSigningCertificates(final String appId) {
        return this.request("GET", String.format("/apps/%s/certificates", appId), CertificatesResponse.class).getData();
    }

    public List<Environment> getEnvironments(final String appId) {
        return this.request("GET", String.format("/apps/%s/environments", appId), EnvironmentsResponse.class).getData();
    }

    public List<Automation> getAutomations(final String appId) {
        return this.request("GET", String.format("/apps/%s/automations", appId), AutomationsResponse.class).getData();
    }

    public List<Channel> getChannels(final String appId) {
        return this.request("GET", String.format("/apps/%s/channels?page=1&page_size=25&meta_fields=total", appId),
            ChannelsResponse.class).getData();
    }

    public List<Commit> getCommits(final String appId) {
        return this.request("GET", String.format("/apps/%s/commits?page=1&page_size=25&meta_fields=total", appId),
            CommitsResponse.class).getData();
    }

    public List<Build> getBuilds(final String appId) {
        return this.request("GET", String.format("/apps/%s/builds?page=1&page_size=25&meta_fields=total", appId),
            BuildsResponse.class).getData();
    }

    public List<Deployment> getDeployments(final String appId) {
        return this.request("GET", String.format("/apps/%s/deployments?page=1&page_size=25&meta_fields=total", appId),
            DeploymentsResponse.class).getData();
    }

    private <T> T request(final String method, final String path, Class<T> clazz) {
        final String url = BASE_URL + path;
        final Request request = builder(method)
            .url(url)
            .build();

        log.debug("Making {} request to {}{}...", method, BASE_URL, path);
        try (Response response = this.client.newCall(request).execute()) {
            final int code = response.code();
            final String body = Objects.requireNonNull(response.body()).string();
            if (response.isSuccessful()) {
                log.trace("Response body: {}", body);
                return this.mapper.readValue(body, clazz);
            }

            throw new AppflowHttpException(code, url);
        } catch (IOException e) {
            throw new AppflowException("Could not make call to " + url, e);
        }
    }

    private Request.Builder builder(final String method) {
        return this.builder(method, null);
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
