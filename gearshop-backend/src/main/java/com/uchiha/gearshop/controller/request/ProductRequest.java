package com.uchiha.gearshop.controller.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private int available;
    private String description;
    private Float price;
    private Float price_old;
    private int hot;
    private int special;
    private int is_new;
    private String photo;
    private int stock;
    private int category_id;
    private int manufacturer_id;
}
