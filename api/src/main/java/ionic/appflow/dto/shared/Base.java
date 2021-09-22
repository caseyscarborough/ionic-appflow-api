package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Base{

    @JsonProperty("bundle_id")
    private String bundleId;

    @JsonProperty("name")
    private String name;
}
