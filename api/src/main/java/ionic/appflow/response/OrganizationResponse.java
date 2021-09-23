package ionic.appflow.response;

import ionic.appflow.response.shared.Organization;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationResponse {

    Data data;

    @Getter
    @Setter
    public static class Data {
        private Organization organization;
    }
}
