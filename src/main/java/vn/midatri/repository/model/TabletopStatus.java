package vn.midatri.repository.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TabletopStatus {
    AVAILABLE("AVAILABLE"), ACTIVE("ACTIVE"), UNAVAILABLE("UNAVAILABLE");

    private final String value;

    TabletopStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TabletopStatus parseTabletopStatus(String value) {
        TabletopStatus[] values = values();
        for (TabletopStatus tabletopStatus : values) {
            if (tabletopStatus.value.equals(value))
                return tabletopStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
