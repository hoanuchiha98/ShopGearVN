package com.uchiha.gearshop.service.impl;

import com.uchiha.gearshop.controller.request.OrderRequest;
import com.uchiha.gearshop.controller.request.ProductCartDTO;
import com.uchiha.gearshop.model.BillDetailEntity;
import com.uchiha.gearshop.model.BillEntity;
import com.uchiha.gearshop.repository.BillRepository;
import com.uchiha.gearshop.repository.OrderDAO;
import com.uchiha.gearshop.repository.ProductRepository;
import com.uchiha.gearshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderDAO orderDAO;

    @Override
    public boolean Order(OrderRequest orderRequest) {
            return orderDAO.Order(orderRequest);
    }
}
