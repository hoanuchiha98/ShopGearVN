package com.uchiha.gearshop.common.dto.mapper;

import com.uchiha.gearshop.common.dto.model.CategoryDto;
import com.uchiha.gearshop.model.CategoryEntity;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(CategoryEntity categoryEntity) {
        return new CategoryDto()
                .setId(categoryEntity.getId())
                .setName(categoryEntity.getName())
                .setDescription(categoryEntity.getDescription())
                .setPhoto(categoryEntity.getPhoto());
    }
}
