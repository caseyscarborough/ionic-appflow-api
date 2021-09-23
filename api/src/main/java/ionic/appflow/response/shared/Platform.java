package ionic.appflow.response.shared;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@RequiredArgsConstructor
public enum Platform {
    IOS("iOS"),
    ANDROID("Android"),
    WEB("Web");

    @Getter
    private final String friendlyName;

    public String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
