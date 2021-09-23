package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Job{

    @JsonProperty("job_type")
    private String jobType;

    @JsonProperty("trace")
    private String trace;

    @JsonProperty("distribution_trace")
    private String distributionTrace;

    @JsonProperty("id")
    private int id;

    @JsonProperty("state")
    private String state;
}
