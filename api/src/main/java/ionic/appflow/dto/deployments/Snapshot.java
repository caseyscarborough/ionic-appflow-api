package ionic.appflow.dto.deployments;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.shared.Artifact;
import ionic.appflow.dto.shared.Commit;
import ionic.appflow.dto.shared.Config;
import ionic.appflow.dto.shared.Deployment;
import ionic.appflow.dto.shared.Stack;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Snapshot {

    @JsonProperty("note")
    private String note;

    @JsonProperty("before_sha")
    private String beforeSha;

    @JsonProperty("ios_version")
    private IosVersion iosVersion;

    @JsonProperty("stack")
    private Stack stack;

    @JsonProperty("environment_id")
    private int environmentId;

    @JsonProperty("commit")
    private Commit commit;

    @JsonProperty("distribution_trace")
    private String distributionTrace;

    @JsonProperty("short_sha")
    private String shortSha;

    @JsonProperty("android_version")
    private AndroidVersion androidVersion;

    @JsonProperty("has_manifest")
    private boolean hasManifest;

    @JsonProperty("type")
    private String type;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("env_config")
    private List<Config> envConfig;

    @JsonProperty("automation_id")
    private int automationId;

    @JsonProperty("number")
    private int number;

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("artifact_name")
    private String artifactName;

    @JsonProperty("repository_id")
    private int repositoryId;

    @JsonProperty("id")
    private String id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("automation_name")
    private String automationName;

    @JsonProperty("artifacts")
    private List<Artifact> artifacts;

    @JsonProperty("environment_name")
    private String environmentName;

    @JsonProperty("env_secrets")
    private List<Config> envSecrets;

    @JsonProperty("preview_hash")
    private Object previewHash;

    @JsonProperty("created")
    private String created;

    @JsonProperty("finished")
    private String finished;

    @JsonProperty("pending_channels")
    private List<String> pendingChannels;

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("environment")
    private String environment;

    @JsonProperty("deployments")
    private List<Deployment> deployments;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("org_id")
    private String orgId;

    @JsonProperty("job_id")
    private int jobId;

    @JsonProperty("caller_id")
    private int callerId;

    @JsonProperty("ref_type")
    private String refType;
}
