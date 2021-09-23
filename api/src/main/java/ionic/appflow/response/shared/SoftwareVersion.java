package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SoftwareVersion {

    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;
}
