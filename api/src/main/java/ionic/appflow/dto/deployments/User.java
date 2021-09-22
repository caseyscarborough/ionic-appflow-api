package ionic.appflow.dto.deployments;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.shared.Avatars;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("username")
    private String username;

    @JsonProperty("avatars")
    private Avatars avatars;
}
