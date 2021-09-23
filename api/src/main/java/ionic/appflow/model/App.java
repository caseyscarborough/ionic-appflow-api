package ionic.appflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class App {

    @JsonProperty("owner")
    private int owner;

    @JsonProperty("org")
    private Organization organization;

    @JsonProperty("created")
    private String created;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("association")
    private Object association;

    @JsonProperty("web_preview")
    private boolean webPreview;

    @JsonProperty("product_keys")
    private List<Integer> productKeys;

    @JsonProperty("web_preview_email_sent")
    private boolean webPreviewEmailSent;

    @JsonProperty("created_by_wizard")
    private boolean createdByWizard;

    @JsonProperty("last_activity")
    private String lastActivity;

    @JsonProperty("channels")
    private List<String> channels;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("updated")
    private String updated;

    @JsonProperty("s3_domain")
    private Object s3Domain;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("dash_metadata")
    private Object dashMetadata;
}
