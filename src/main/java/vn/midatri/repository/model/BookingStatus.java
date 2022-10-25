package vn.midatri.repository.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatus {
    NEW("NEW"), ACTIVE("ACTIVE"), COMPLETE("COMPLETE");

    private final String value;

    BookingStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator

    public static BookingStatus parseBookingStatus(String value) {
        BookingStatus[] values = values();
        for (BookingStatus bookingStatus : values) {
            if (bookingStatus.value.equals(value))
                return bookingStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
