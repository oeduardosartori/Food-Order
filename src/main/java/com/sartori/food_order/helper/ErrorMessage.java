package com.sartori.food_order.helper;

public enum ErrorMessage {
    // === CLIENT MESSAGES ===
    CLIENT_NOT_FOUND_BY_ID("client.notfound.id"),
    CLIENT_EMAIL_DUPLICATE("client.email.duplicate"),
    CLIENT_CPF_DUPLICATE("client.cpf.duplicate"),

    // === PRODUCT MESSAGES ===
    PRODUCT_NOT_FOUND_BY_ID("product.notfound.id"),
    PRODUCT_NAME_DUPLICATE("product.name.duplicate"),
    PRODUCT_PRICE_INVALID("product.price.positive"),
    PRODUCT_PRICE_DECIMAL("product.price.decimal"),

    // === ORDER MESSAGES ===
    ORDER_NOT_FOUND_BY_ID("order.notfound.id"),
    ORDER_EMPTY_ITEMS("order.items.empty"),
    ORDER_INVALID_STATUS("order.status.invalid"),

    // === GLOBAL / GENERAL MESSAGES ===
    INTERNAL_SERVER_ERROR("internal.server.error"),
    INCONSISTENT_VALIDATION("validation.inconsistent");

    private final String code;

    ErrorMessage(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
