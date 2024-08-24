package com.test.models.enums;

public enum OrderStatus {
    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    public final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
