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
    private String platform;

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
}
