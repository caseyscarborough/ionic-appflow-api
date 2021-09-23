package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artifact {

    @JsonProperty("artifact_type")
    private String artifactType;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}
