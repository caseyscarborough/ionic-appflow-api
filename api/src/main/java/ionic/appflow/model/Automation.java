package ionic.appflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Automation {

    @JsonProperty("ref")
    private String ref;

    @JsonProperty("stack")
    private Stack stack;

    @JsonProperty("environment")
    private Environment environment;

    @JsonProperty("environment_id")
    private int environmentId;

    @JsonProperty("created")
    private String created;

    @JsonProperty("name")
    private String name;

    @JsonProperty("creator_id")
    private String creatorId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("notifications")
    private List<Notification> notifications;

    @JsonProperty("platform")
    private Platform platform;

    @JsonProperty("channel")
    private Channel channel;

    @JsonProperty("web_preview")
    private boolean webPreview;

    @JsonProperty("channels")
    private List<Channel> channels;

    @JsonProperty("channel_id")
    private String channelId;

    @JsonProperty("distribution_credential")
    private Object distributionCredential;

    @JsonProperty("native_config")
    private NativeConfig nativeConfig;

    @JsonProperty("native_config_id")
    private int nativeConfigId;

    @JsonProperty("profile_name")
    private String profileName;

    @JsonProperty("profile_tag")
    private String profileTag;

    @JsonProperty("build_type")
    private String buildType;

}
