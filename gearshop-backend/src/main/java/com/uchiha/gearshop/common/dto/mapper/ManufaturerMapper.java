package com.uchiha.gearshop.common.dto.mapper;

import com.uchiha.gearshop.common.dto.model.ManufacturerDto;
import com.uchiha.gearshop.model.ManufacturerEntity;

public class ManufaturerMapper {
    public static ManufacturerDto toManufacturerDto(ManufacturerEntity manufacturerEntity) {
        return new ManufacturerDto()
                .setId(manufacturerEntity.getId())
                .setName(manufacturerEntity.getName())
                .setDescription(manufacturerEntity.getDescription())
                .setPhoto(manufacturerEntity.getPhoto());
    }
}
