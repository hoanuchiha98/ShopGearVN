package com.uchiha.gearshop.service;

import com.uchiha.gearshop.common.dto.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto findByCategoryType(Integer categoryType);

    List<CategoryDto> findByCategoryTypeIn(List<Integer> categoryTypeList);

    CategoryDto update(CategoryDto categoryDto);

    CategoryDto create(CategoryDto categoryDto);

    void delete(int categoryId);
}
