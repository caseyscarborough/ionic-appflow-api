package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.deployments.Snapshot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Channel {

    @JsonProperty("ref")
    private Object ref;

    @JsonProperty("created")
    private String created;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("updated")
    private String updated;

    @JsonProperty("snapshot")
    private Snapshot snapshot;
}
