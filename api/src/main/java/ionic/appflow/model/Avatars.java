package ionic.appflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Avatars {

    @JsonProperty("small")
    private String small;

    @JsonProperty("large")
    private String large;

    @JsonProperty("medium")
    private String medium;
}
