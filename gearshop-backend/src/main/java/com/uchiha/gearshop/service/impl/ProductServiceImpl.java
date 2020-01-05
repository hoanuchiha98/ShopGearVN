package com.uchiha.gearshop.service.impl;

import com.uchiha.gearshop.common.dto.mapper.ProductMapper;
import com.uchiha.gearshop.common.dto.model.ProductDto;
import com.uchiha.gearshop.common.enums.ResultEnum;
import com.uchiha.gearshop.common.exception.CustomException;
import com.uchiha.gearshop.model.CategoryEntity;
import com.uchiha.gearshop.model.ManufacturerEntity;
import com.uchiha.gearshop.model.ProductEntity;
import com.uchiha.gearshop.repository.CategoryRepository;
import com.uchiha.gearshop.repository.ManufacturerRepository;
import com.uchiha.gearshop.repository.ProductRepository;
import com.uchiha.gearshop.service.CategoryService;
import com.uchiha.gearshop.service.ManufacturerService;
import com.uchiha.gearshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ManufacturerRepository manufacturerRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ManufacturerService manufacturerService;


    @Override
    public ProductDto findOne(int productId) {
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductId(productId));
        if (productEntity.isPresent()) {
            ProductEntity productEntity1 = productEntity.get();
            return ProductMapper.toProductDto(productEntity1);
        }
        throw new CustomException(ResultEnum.PRODUCT_NOT_EXIST);
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAllByOrderById(pageable);
        int totalElements = (int) productEntities.getTotalElements();
        if (!productEntities.isEmpty()) {
            return new PageImpl<ProductDto>(productEntities
                    .stream()
                    .map(productEntity -> ProductMapper.toProductDto(productEntity))
                    .collect(Collectors.toList()), pageable, totalElements);
        }
        return new PageImpl<ProductDto>(Collections.emptyList());
    }

    @Override
    public List<ProductDto> findAllv2() {
        List<ProductEntity> productEntities = productRepository.findAll();
        if (!productEntities.isEmpty()) {
            return productEntities
                    .stream()
                    .map(productEntity -> ProductMapper.toProductDto(productEntity))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Page<ProductDto> findAllInCategory(Integer categoryType, Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAllByCategoryIdOrderByIdAsc(categoryType, pageable);
        int totalElements = (int) productEntities.getTotalElements();
        if (!productEntities.isEmpty()) {
            return new PageImpl<ProductDto>(productEntities
                    .stream()
                    .map(productEntity -> ProductMapper.toProductDto(productEntity))
                    .collect(Collectors.toList()), pageable, totalElements);
        }
        return new PageImpl<ProductDto>(Collections.emptyList());
    }

    @Override
    public Page<ProductDto> findAllByCategoryIdAndManufacturerId(Integer categoryId, Integer manufacturerId, Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAllByCategoryIdAndManufacturerId(categoryId, manufacturerId, pageable);
        int totalElements = (int) productEntities.getTotalElements();
        if (!productEntities.isEmpty()) {
            return new PageImpl<ProductDto>(productEntities
                    .stream()
                    .map(productEntity -> ProductMapper.toProductDto(productEntity))
                    .collect(Collectors.toList()), pageable, totalElements);
        }
        return new PageImpl<ProductDto>(Collections.emptyList());
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {
        categoryService.findByCategoryType(productDto.getCategory_id());
        manufacturerService.findByManufacturerId(productDto.getManufacturer_id());

        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductId(productDto.getId()));
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(productDto.getCategory_id());
        ManufacturerEntity manufacturerEntity = manufacturerRepository.findByManufacturerId(productDto.getManufacturer_id());

        if (productEntity.isPresent()) {
            ProductEntity productEntity1 = productEntity.get();
            productEntity1.setName(productDto.getName())
                    .setDescription(productDto.getDescription())
                    .setAvailable(productDto.getAvailable())
                    .setCategory(categoryEntity)
                    .setManufacturer(manufacturerEntity)
                    .setDescription(productDto.getDescription())
                    .setHot(productDto.getHot())
                    .setIs_new(productDto.getIs_new())
                    .setSpecial(productDto.getSpecial())
                    .setName(productDto.getName())
                    .setPrice(productDto.getPrice())
                    .setPrice_old(productDto.getPrice_old())
                    .setStock(productDto.getStock())
                    .setPhoto(productDto.getPhoto())
            ;
            return ProductMapper.toProductDto(productRepository.save(productEntity1));
        }
        throw new CustomException(ResultEnum.CATEGORY_NOT_FOUND);
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto productDto) {
        categoryService.findByCategoryType(productDto.getCategory_id());
        manufacturerService.findByManufacturerId(productDto.getManufacturer_id());

        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(productDto.getCategory_id());
        ManufacturerEntity manufacturerEntity = manufacturerRepository.findByManufacturerId(productDto.getManufacturer_id());
        System.out.println("Catogory::Category============" + categoryEntity);
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setName(productDto.getName())
                .setDescription(productDto.getDescription())
                .setAvailable(productDto.getAvailable())
                .setCategory(categoryEntity)
                .setManufacturer(manufacturerEntity)
                .setDescription(productDto.getDescription())
                .setHot(productDto.getHot())
                .setIs_new(productDto.getIs_new())
                .setSpecial(productDto.getSpecial())
                .setName(productDto.getName())
                .setPrice(productDto.getPrice())
                .setPrice_old(productDto.getPrice_old())
                .setStock(productDto.getStock())
                .setPhoto(productDto.getPhoto())
        ;
        return ProductMapper.toProductDto(productRepository.save(productEntity1));
    }

    @Override
    @Transactional
    public void delete(int productId) {
        Optional<ProductEntity> productEntity = Optional.ofNullable(productRepository.findByProductId(productId));
        System.out.println("Product::Delete==============" + productEntity);
        if (productEntity.isPresent()) {
            ProductEntity productModel = productEntity.get();
            System.out.println("ProductModel::Delete==============" + productModel);
            productRepository.delete(productModel);
            System.out.println("ProductDelete::Delete==============" + productModel);
        } else {
            throw new CustomException(ResultEnum.PRODUCT_NOT_EXIST);
        }

    }
}
