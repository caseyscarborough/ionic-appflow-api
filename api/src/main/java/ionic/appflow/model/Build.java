package ionic.appflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Build {

    @JsonProperty("stack")
    private Stack stack;

    @JsonProperty("env_secrets")
    private List<Config> envSecrets;

    @JsonProperty("environment_id")
    private int environmentId;

    @JsonProperty("preview_hash")
    private Object previewHash;

    @JsonProperty("created")
    private String created;

    @JsonProperty("commit")
    private Commit commit;

    @JsonProperty("finished")
    private String finished;

    @JsonProperty("type")
    private String type;

    @JsonProperty("platform")
    private Platform platform;

    @JsonProperty("env_config")
    private List<Config> envConfig;

    @JsonProperty("automation_id")
    private int automationId;

    @JsonProperty("number")
    private int number;

    @JsonProperty("deployments")
    private List<Deployment> deployments;

    @JsonProperty("job_id")
    private int jobId;

    @JsonProperty("caller_id")
    private int callerId;

    @JsonProperty("artifact_name")
    private String artifactName;

    @JsonProperty("id")
    private String id;

    @JsonProperty("state")
    private String status;

    @JsonProperty("automation_name")
    private String automationName;

    @JsonProperty("environment_name")
    private String environmentName;

    @JsonProperty("artifacts")
    private List<Artifact> artifacts;

    @JsonProperty("note")
    private String note;

    @JsonProperty("ios_version")
    private IosVersion iosVersion;

    @JsonProperty("before_sha")
    private String beforeSha;

    @JsonProperty("distribution_trace")
    private String distributionTrace;

    @JsonProperty("short_sha")
    private String shortSha;

    @JsonProperty("has_manifest")
    private boolean hasManifest;

    @JsonProperty("android_version")
    private AndroidVersion androidVersion;

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("repository_id")
    private int repositoryId;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("app")
    private App app;

    @JsonProperty("org")
    private Organization organization;

    @JsonProperty("pending_channels")
    private List<Object> pendingChannels;

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("environment")
    private String environment;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("org_id")
    private String orgId;

    @JsonProperty("ref_type")
    private String refType;

    @JsonProperty("job")
    private Job job;

    @JsonProperty("user")
    private User user;
}
