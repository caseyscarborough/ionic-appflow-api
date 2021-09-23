package ionic.appflow.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Organization {

    @JsonProperty("owner")
    private int owner;

    @JsonProperty("stripe_customer")
    private boolean stripeCustomer;

    @JsonProperty("created")
    private String created;

    @JsonProperty("trial_expiration")
    private String trialExpiration;

    @JsonProperty("description")
    private String description;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("pre_trial_limits_override")
    private PreTrialLimitsOverride preTrialLimitsOverride;

    @JsonProperty("seats")
    private int seats;

    @JsonProperty("trial")
    private boolean trial;

    @JsonProperty("sso")
    private boolean sso;

    @JsonProperty("owner_user")
    private int ownerUser;

    @JsonProperty("product_keys")
    private List<Integer> productKeys;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("plan")
    private String plan;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("email")
    private String email;

    @JsonProperty("available_studio_seats")
    private int availableStudioSeats;

    @JsonProperty("owner_id")
    private int ownerId;

    @JsonProperty("available_seats")
    private int availableSeats;

    @JsonProperty("trial_creation")
    private String trialCreation;

    @JsonProperty("studio_seats_purchased")
    private int studioSeatsPurchased;

    @JsonProperty("pending_invites")
    private int pendingInvites;

    @JsonProperty("has_native_access")
    private boolean hasNativeAccess;

    @JsonProperty("member_total")
    private int memberTotal;

    @JsonProperty("updated")
    private String updated;
}
