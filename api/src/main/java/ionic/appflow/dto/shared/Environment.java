package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.shared.Config;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Environment {

    @JsonProperty("created")
    private String created;

    @JsonProperty("appId")
    private String appId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("creatorId")
    private String creatorId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("config")
    private List<Config> config;

    @JsonProperty("secrets")
    private List<Config> secrets;
}
