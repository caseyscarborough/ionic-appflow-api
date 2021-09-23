package ionic.appflow.response.shared;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ios{

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("not_valid_after")
    private String notValidAfter;

    @JsonProperty("created")
    private String created;

    @JsonProperty("provisioning_profiles")
    private List<ProvisioningProfile> provisioningProfiles;

    @JsonProperty("fingerprint")
    private String fingerprint;

    @JsonProperty("not_valid_before")
    private String notValidBefore;

    @JsonProperty("subject_common_name")
    private String subjectCommonName;

    @JsonProperty("type")
    private String type;
}
