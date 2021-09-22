package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import ionic.appflow.dto.deployments.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commit {

    @JsonProperty("before_sha")
    private String beforeSha;

    @JsonProperty("note")
    private String note;

    @JsonProperty("created")
    private String created;

    @JsonProperty("short_sha")
    private String shortSha;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("commit_repo_formatted_type")
    private String commitRepoFormattedType;

    @JsonProperty("repository_id")
    private int repositoryId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("commit_repo_link")
    private String commitRepoLink;

    @JsonProperty("ref_type")
    private String refType;

    @JsonProperty("user")
    private User user;

    @JsonProperty("app_id")
    private String appId;
}
