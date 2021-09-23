package ionic.appflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Deployment {

    @JsonProperty("distribution_credential")
    private String distributionCredential;

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

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("automation")
    private Automation automation;

    @JsonProperty("build")
    private Build build;

    @JsonProperty("user")
    private User user;

}
