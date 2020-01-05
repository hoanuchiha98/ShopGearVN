package com.uchiha.gearshop.service;

import com.uchiha.gearshop.common.dto.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductDto findOne(int productId);

    Page<ProductDto> findAll(Pageable pageable);

    List<ProductDto> findAllv2();

    Page<ProductDto> findAllInCategory(Integer categoryType, Pageable pageable);

    ProductDto update(ProductDto productDto);

    ProductDto create(ProductDto productDto);

    void delete(int productId);

    Page<ProductDto> findAllByCategoryIdAndManufacturerId(Integer categoryId, Integer manufacturerId, Pageable pageable);
}
