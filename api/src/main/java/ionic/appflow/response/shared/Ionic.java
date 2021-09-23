package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ionic{

    @JsonProperty("channel_name")
    private String channelName;

    @JsonProperty("update_method")
    private String updateMethod;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("disable_deploy")
    private boolean disableDeploy;

    @JsonProperty("max_store")
    private Integer maxStore;

    @JsonProperty("min_background_duration")
    private Integer minBackgroundDuration;
}
