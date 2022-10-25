package vn.midatri.repository.model;

public enum BookingItemStatus {

    NEW("New"), KITCHEN("Kitchen"),

    COOKING("Cooking"), COOKED("Cooked"),

    SERVER("Served");

    private final String value;

    BookingItemStatus(String value) {
        this.value = value;
    }

    public static BookingItemStatus parseBookingStatus(String value) {
        BookingItemStatus[] values = values();
        for (BookingItemStatus bookingStatus : values) {
            if (bookingStatus.value.equals(value)) return bookingStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
