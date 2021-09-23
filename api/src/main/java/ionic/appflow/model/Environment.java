package ionic.appflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
