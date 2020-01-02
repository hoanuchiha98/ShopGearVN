package com.uchiha.gearshop.service.impl;

import com.uchiha.gearshop.common.dto.mapper.CategoryMapper;
import com.uchiha.gearshop.common.dto.model.CategoryDto;
import com.uchiha.gearshop.common.enums.ResultEnum;
import com.uchiha.gearshop.common.exception.CustomException;
import com.uchiha.gearshop.model.CategoryEntity;
import com.uchiha.gearshop.repository.CategoryRepository;
import com.uchiha.gearshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAllByOrderById();
        if (!categoryEntities.isEmpty()) {
            return categoryEntities
                    .stream()
                    .map(categoryEntity -> CategoryMapper.toCategoryDto(categoryEntity))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public CategoryDto findByCategoryType(Integer categoryType) {
        Optional<CategoryEntity> categoryEntity = Optional.ofNullable(categoryRepository.findByCategoryId(categoryType));
        if (categoryEntity.isPresent()) {
            CategoryEntity categoryEntity1 = categoryEntity.get();
            return CategoryMapper.toCategoryDto(categoryEntity1);
        }
        throw new CustomException(ResultEnum.CATEGORY_NOT_FOUND);
    }

    @Override
    public List<CategoryDto> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<CategoryEntity> categoryEntities = categoryRepository.findByIdInOrderByIdAsc(categoryTypeList);
        if (!categoryEntities.isEmpty()) {
            return categoryEntities
                    .stream()
                    .map(categoryEntity -> CategoryMapper.toCategoryDto(categoryEntity))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public CategoryDto update(CategoryDto productCategory) {
        Optional<CategoryEntity> categoryEntity = Optional.ofNullable(categoryRepository.findByCategoryId(productCategory.getId()));
        if (categoryEntity.isPresent()) {
            CategoryEntity categoryModel = categoryEntity.get();
            categoryModel.setName(productCategory.getName())
                    .setDescription(productCategory.getDescription())
                    .setPhoto(productCategory.getPhoto());
            return CategoryMapper.toCategoryDto(categoryRepository.save(categoryModel));
        }
        throw new CustomException(ResultEnum.CATEGORY_NOT_FOUND);
    }

    @Override
    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        Optional<CategoryEntity> categoryEntity = Optional.ofNullable(categoryRepository.findByCategoryName(categoryDto.getName()));
        if (!categoryEntity.isPresent()) {
            CategoryEntity categoryModel = categoryEntity.get();
            categoryModel.setName(categoryDto.getName())
                    .setDescription(categoryDto.getDescription())
                    .setPhoto(categoryDto.getPhoto());
            return CategoryMapper.toCategoryDto(categoryRepository.save(categoryModel));
        }
        throw new CustomException(ResultEnum.CATEGORY_ALREADY_EXIT);
    }

    @Transactional
    @Override
    public void delete(int categoryId) {
        Optional<CategoryEntity> categoryEntity = Optional.ofNullable(categoryRepository.findByCategoryId(categoryId));
        System.out.println("Product::Delete==============" + categoryEntity);
        if (categoryEntity.isPresent()) {
            CategoryEntity categoryModel = categoryEntity.get();
            categoryRepository.delete(categoryModel);
        } else {
            throw new CustomException(ResultEnum.CATEGORY_NOT_FOUND);
        }
    }

}
