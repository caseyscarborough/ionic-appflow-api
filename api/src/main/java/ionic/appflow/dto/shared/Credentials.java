package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials{

    @JsonProperty("ios")
    private Ios ios;

    @JsonProperty("android")
    private Android android;
}
