package com.uchiha.gearshop.common.dto.mapper;

import com.uchiha.gearshop.common.dto.model.BillDetailDto;
import com.uchiha.gearshop.model.BillDetailEntity;
import com.uchiha.gearshop.model.BillEntity;
import com.uchiha.gearshop.model.ProductEntity;

public class BillDetailMapper {
    public static BillDetailDto toBillDetailDto(BillDetailEntity billDetailEntity) {
        BillEntity billEntity = billDetailEntity.getBill();
        ProductEntity productEntity = billDetailEntity.getProduct();
        return new BillDetailDto()
                .setBill_detail_id(billDetailEntity.getBill_detail_id())
                .setBill_id(billEntity.getId())
                .setProduct_id(productEntity.getId())
                .setAmount(billDetailEntity.getAmount())
                .setPrice(billDetailEntity.getPrice());
    }
}
