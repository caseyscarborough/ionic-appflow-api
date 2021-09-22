package ionic.appflow.dto.deployments;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.shared.Automation;
import ionic.appflow.dto.shared.Channel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Deployment {

    @JsonProperty("automation")
    private Automation automation;

    @JsonProperty("build")
    private Build build;

    @JsonProperty("created")
    private String created;

    @JsonProperty("channel")
    private Channel channel;

    @JsonProperty("id")
    private int id;

    @JsonProperty("completed")
    private String completed;

    @JsonProperty("triggered_by")
    private String triggeredBy;

    @JsonProperty("type")
    private String type;

    @JsonProperty("user")
    private User user;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("status")
    private String status;
}
