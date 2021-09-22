package ionic.appflow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.shared.Meta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppflowResponse<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("meta")
    private Meta meta;
}
