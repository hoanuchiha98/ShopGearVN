package com.uchiha.gearshop.controller.request;

import lombok.Data;

@Data
public class ProductCartDTO {
    private int id;
    private String name;
    private String photo;
    private int quantity;
    private int price;
    private int totalPrice;
}
