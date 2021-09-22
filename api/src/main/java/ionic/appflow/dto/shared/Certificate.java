package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.shared.Credentials;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Certificate {

    @JsonProperty("credentials")
    private Credentials credentials;

    @JsonProperty("created")
    private String created;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    @JsonProperty("tag")
    private String tag;

    @JsonProperty("type")
    private String type;

}
