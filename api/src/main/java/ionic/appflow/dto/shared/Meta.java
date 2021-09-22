package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {

    @JsonProperty("total")
    private int total;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("version")
    private String version;

    @JsonProperty("status")
    private int status;
}
