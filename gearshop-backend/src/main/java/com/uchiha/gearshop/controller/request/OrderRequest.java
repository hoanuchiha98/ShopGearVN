package com.uchiha.gearshop.controller.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderRequest {
    private int user_id;
    private List<ProductCartDTO> cart;
    private int money_bill;
    private Date date_created;
}


