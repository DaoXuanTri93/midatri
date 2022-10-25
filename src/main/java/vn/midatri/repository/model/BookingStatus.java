package vn.midatri.repository.model;

public enum BookingStatus {
    NEW("New"), ACTIVE("Active"), COMPLETE("Complete");

    private final String value;

    BookingStatus(String value) {
        this.value = value;
    }

    public static BookingStatus parseBookingStatus(String value) {
        BookingStatus[] values = values();
        for (BookingStatus bookingStatus : values) {
            if (bookingStatus.value.equals(value))
                return bookingStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
