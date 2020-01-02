package com.uchiha.gearshop.common.dto.mapper;

import com.uchiha.gearshop.common.dto.model.BillDto;
import com.uchiha.gearshop.model.BillEntity;

public class BillMapper {
    public static BillDto toBillDto(BillEntity billEntity) {
        return new BillDto()
                .setId(billEntity.getId())
                .setCode(billEntity.getCode())
                .setCreate_date(billEntity.getCreate_date());

    }
}
