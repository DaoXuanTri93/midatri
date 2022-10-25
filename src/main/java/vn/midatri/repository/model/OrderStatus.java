package vn.midatri.repository.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    NEW("NEW"), CHECKOUT("CHECKOUT"), PAID("PAID"), FAILED("FAILED"), SHIPPED("SHIPPED"), DELIVERED("DELIVERED"), RETURNED("RETURNED"), COMPLETE("COMPLETE");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static OrderStatus parseOrderStatus(String value) {
        OrderStatus[] values = values();
        for (OrderStatus orderStatus : values) {
            if (orderStatus.value.equals(value)) return orderStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}