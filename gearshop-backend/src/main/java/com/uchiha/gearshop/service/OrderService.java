package com.uchiha.gearshop.service;

import com.uchiha.gearshop.controller.request.OrderRequest;

public interface OrderService {
    boolean Order(OrderRequest orderRequest);
}
