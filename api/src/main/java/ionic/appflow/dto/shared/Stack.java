package ionic.appflow.dto.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Stack {

    @JsonProperty("friendly_name")
    private String friendlyName;

    @JsonProperty("eol_date")
    private Object eolDate;

    @JsonProperty("software_versions")
    private List<SoftwareVersion> softwareVersions;

    @JsonProperty("name")
    private String name;

    @JsonProperty("available")
    private boolean available;

    @JsonProperty("id")
    private int id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("latest")
    private boolean latest;
}
