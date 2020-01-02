package com.uchiha.gearshop.service;

import com.uchiha.gearshop.common.dto.model.ManufacturerDto;

import java.util.List;

public interface ManufacturerService {
    List<ManufacturerDto> findAll();

    ManufacturerDto findByManufacturerId(Integer categoryType);

    List<ManufacturerDto> findByManufacturerIdIn(List<Integer> manufacturerTypeList);

    ManufacturerDto update(ManufacturerDto manufacturerDto);

    ManufacturerDto create(ManufacturerDto manufacturerDto);

    void delete(int manufacturerId);
}
