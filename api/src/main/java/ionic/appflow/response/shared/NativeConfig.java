package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NativeConfig{

    @JsonProperty("created")
    private String created;

    @JsonProperty("creator_id")
    private String creatorId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("ionic")
    private Ionic ionic;

    @JsonProperty("id")
    private int id;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("base")
    private Base base;
}
