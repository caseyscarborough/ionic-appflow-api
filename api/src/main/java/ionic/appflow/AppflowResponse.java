package ionic.appflow;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.model.Meta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class AppflowResponse<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("meta")
    private Meta meta;
}
