package ionic.appflow;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
class GraphQLQuery {
    private String query;
    private Map<String, String> variables;
}
