package ionic.appflow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvisioningProfile {

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("name")
    private String name;

    @JsonProperty("application_identifier")
    private String applicationIdentifier;

    @JsonProperty("id")
    private int id;

    @JsonProperty("creation_date")
    private String creationDate;

    @JsonProperty("expiration_date")
    private String expirationDate;

    @JsonProperty("uuid")
    private String uuid;
}
