package vn.midatri.repository.model;

public enum OrderStatus {
    NEW("New"), CHECKOUT("Checkout"), PAID("Paid"), FAILED("Failed"), SHIPPED("Shipped"), DELIVERED("Delivered"), RETURNED("Returned"), COMPLETE("Complete");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus parseOrderStatus(String value) {
        OrderStatus[] values = values();
        for (OrderStatus orderStatus : values) {
            if (orderStatus.value.equals(value)) return orderStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}