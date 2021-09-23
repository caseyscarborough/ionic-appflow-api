package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildType {

    @JsonProperty("name")
    private String name;
    @JsonProperty("friendly_name")
    private String friendlyName;
    @JsonProperty("profile_type")
    private String profileType;
}
