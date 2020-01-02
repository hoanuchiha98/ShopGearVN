package com.uchiha.gearshop.common.dto.mapper;

import com.uchiha.gearshop.common.dto.model.ProductDto;
import com.uchiha.gearshop.model.CategoryEntity;
import com.uchiha.gearshop.model.ManufacturerEntity;
import com.uchiha.gearshop.model.ProductEntity;

public class ProductMapper {
    public static ProductDto toProductDto(ProductEntity productEntity) {
        CategoryEntity categoryEntity = productEntity.getCategory();
        ManufacturerEntity manufacturerEntity = productEntity.getManufacturer();

        return new ProductDto()
                .setId(productEntity.getId())
                .setAvailable(productEntity.getAvailable())
                .setDescription(productEntity.getDescription())
                .setHot(productEntity.getHot())
                .setIs_new(productEntity.getIs_new())
                .setName(productEntity.getName())
                .setPhoto(productEntity.getPhoto())
                .setStock(productEntity.getStock())
                .setPrice(productEntity.getPrice())
                .setPrice_old(productEntity.getPrice_old())
                .setCategory_id(categoryEntity.getId())
                .setManufacturer_id(manufacturerEntity.getId())
                .setSpecial(productEntity.getSpecial());
    }
}
