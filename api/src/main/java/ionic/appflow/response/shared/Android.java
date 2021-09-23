package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Android {

    @JsonProperty("created")
    private String created;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("fingerprint")
    private String fingerprint;

    @JsonProperty("key_alias")
    private String keyAlias;

    @JsonProperty("not_valid_after")
    private String notValidAfter;

    @JsonProperty("not_valid_before")
    private String notValidBefore;

    @JsonProperty("subject_common_name")
    private String subjectCommonName;

    @JsonProperty("type")
    private String type;
}
