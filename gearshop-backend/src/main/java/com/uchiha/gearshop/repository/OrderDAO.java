package com.uchiha.gearshop.repository;

import com.uchiha.gearshop.controller.request.OrderRequest;
import com.uchiha.gearshop.controller.request.ProductCartDTO;
import com.uchiha.gearshop.model.BillDetailEntity;
import com.uchiha.gearshop.model.BillEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional(rollbackFor = Exception.class)
public class OrderDAO {
    @Autowired
    ProductRepository productRepository;
    @PersistenceContext
    private EntityManager entityManager;
    public boolean Order(OrderRequest orderRequest){
        try{
            //Lưu vào bill
            BillEntity billEntity = new BillEntity();
            billEntity.setCode(orderRequest.getUser_id());
            billEntity.setCreate_date(new Timestamp(System.currentTimeMillis()));
            System.out.println("Bill::===========" + billEntity);
            entityManager.getTransaction().begin();
            System.out.println("Bill::===========" + billEntity);
            entityManager.merge(billEntity);
            entityManager.flush();
            entityManager.refresh(billEntity);
            System.out.println("Bill::===========" + billEntity);
            List<ProductCartDTO> list_product = orderRequest.getCart();
            //Lưu bill detail
            for (ProductCartDTO productCartDTO: list_product){
                BillDetailEntity billDetailEntity = new BillDetailEntity();
                billDetailEntity.setBill(billEntity);
                billDetailEntity.setProduct(productRepository.findByProductId(productCartDTO.getId()));
                billDetailEntity.setAmount(productCartDTO.getQuantity());
                billDetailEntity.setPrice(productCartDTO.getTotalPrice());
                entityManager.merge(billDetailEntity);
                entityManager.flush();
                entityManager.refresh(billDetailEntity);
                System.out.println("Bill Detail::===========" + billDetailEntity);
            }
            // commit
            entityManager.getTransaction().commit();
            return true;
        }
        catch (Exception errr){
            return false;
        }
    }

}
