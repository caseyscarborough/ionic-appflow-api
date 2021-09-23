package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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
