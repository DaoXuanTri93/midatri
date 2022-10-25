package vn.midatri.repository.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingItemStatus {

    NEW("NEW"), KITCHEN("KITCHEN"),

    COOKING("COOKING"), COOKED("COOKED"),

    SERVER("SERVER");

    private final String value;

    BookingItemStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
@JsonCreator
    public static BookingItemStatus parseBookingStatus(String value) {
        BookingItemStatus[] values = values();
        for (BookingItemStatus bookingStatus : values) {
            if (bookingStatus.value.equals(value)) return bookingStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
