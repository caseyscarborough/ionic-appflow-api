package ionic.appflow;

import java.io.InputStream;
import java.util.Scanner;

class GraphQLReader {
    public static String getSchema(final String filename) {
        final InputStream is = GraphQLReader.class.getClassLoader()
            .getResourceAsStream("graphql/" + filename + ".graphql");
        if (is == null) {
            throw new IllegalStateException("Input stream was null for file graphql/" + filename + ".graphql");
        }
        try (Scanner scanner = new Scanner(is)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
