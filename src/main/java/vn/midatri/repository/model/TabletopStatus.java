package vn.midatri.repository.model;

public enum TabletopStatus {
    AVAILABLE("Available"), ACTIVE("Active"), UNAVAILABLE("UnAvailable");

    private final String value;

    TabletopStatus(String value) {
        this.value = value;
    }

    public static TabletopStatus parseTabletopStatus(String value) {
        TabletopStatus[] values = values();
        for (TabletopStatus tabletopStatus : values) {
            if (tabletopStatus.value.equals(value))
                return tabletopStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
