package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;
}
