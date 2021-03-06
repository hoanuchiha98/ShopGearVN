package com.uchiha.gearshop.common.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "Parameter Error!"),
    PRODUCT_NOT_EXIST(10, "Product does not exit!"),
    PRODUCT_NOT_FOUND(11, "Not enough products in stock!"),
    PRODUCT_STATUS_ERROR(12, "Status is incorrect!"),
    PRODUCT_OFF_SALE(13, "Product is off sale!"),
    PRODUCT_NOT_IN_CART(14, "Product is not in the cart!"),
    CART_CHECKOUT_SUCCESS(20, "Checkout successfully! "),

    CATEGORY_NOT_FOUND(30, "Category does not exit!"),
    CATEGORY_ALREADY_EXIT(41, "Category is already exits!"),

    MANUFACTURER_NOT_FOUND(31, "Manufacturer does not exit!"),
    MANUFACTURER_ALREADY_EXIT(41, "Manufacturer is already exits!"),

    ORDER_NOT_FOUND(60, "OrderMain is not exit!"),
    ORDER_STATUS_ERROR(61, "Status is not correct"),


    VALID_ERROR(50, "Wrong information"),
    USER_NOT_FOUNT(40, "User is not found!"),
    USER_ALREADY_EXIT(41, "User is already exits!"),
    PASSWORD_WRONG(42, "Password is wrong");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
