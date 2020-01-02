package com.uchiha.gearshop.service.impl;

import com.uchiha.gearshop.common.dto.mapper.ManufaturerMapper;
import com.uchiha.gearshop.common.dto.model.ManufacturerDto;
import com.uchiha.gearshop.common.enums.ResultEnum;
import com.uchiha.gearshop.common.exception.CustomException;
import com.uchiha.gearshop.model.ManufacturerEntity;
import com.uchiha.gearshop.repository.ManufacturerRepository;
import com.uchiha.gearshop.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<ManufacturerDto> findAll() {
        List<ManufacturerEntity> manufacturerEntities = manufacturerRepository.findAllByOrderById();
        if (!manufacturerEntities.isEmpty()) {
            return manufacturerEntities
                    .stream()
                    .map(manufacturerEntity -> ManufaturerMapper.toManufacturerDto(manufacturerEntity))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public ManufacturerDto findByManufacturerId(Integer manufacturerId) {
        Optional<ManufacturerEntity> manufacturerEntity = Optional.ofNullable(manufacturerRepository.findByManufacturerId(manufacturerId));
        if (manufacturerEntity.isPresent()) {
            ManufacturerEntity manufacturerEntity1 = manufacturerEntity.get();
            return ManufaturerMapper.toManufacturerDto(manufacturerEntity1);
        }
        throw new CustomException(ResultEnum.MANUFACTURER_NOT_FOUND);
    }

    @Override
    public List<ManufacturerDto> findByManufacturerIdIn(List<Integer> manufacturerTypeList) {
        List<ManufacturerEntity> manufacturerEntities = manufacturerRepository.findByNameInOrderByIdAsc(manufacturerTypeList);
        if (!manufacturerEntities.isEmpty()) {
            return manufacturerEntities
                    .stream()
                    .map(manufacturerEntity -> ManufaturerMapper.toManufacturerDto(manufacturerEntity))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public ManufacturerDto update(ManufacturerDto manufacturerDto) {
        Optional<ManufacturerEntity> manufacturerEntity = Optional.ofNullable(manufacturerRepository.findByManufacturerId(manufacturerDto.getId()));
        if (manufacturerEntity.isPresent()) {
            ManufacturerEntity manufacturerEntity1 = manufacturerEntity.get();
            manufacturerEntity1.setName(manufacturerDto.getName())
                    .setDescription(manufacturerDto.getDescription())
                    .setPhoto(manufacturerDto.getPhoto());
            return ManufaturerMapper.toManufacturerDto(manufacturerRepository.save(manufacturerEntity1));
        }
        throw new CustomException(ResultEnum.MANUFACTURER_NOT_FOUND);
    }

    @Override
    public ManufacturerDto create(ManufacturerDto manufacturerDto) {
        Optional<ManufacturerEntity> manufacturerEntity = Optional.ofNullable(manufacturerRepository.findByManufacturerName(manufacturerDto.getName()));
        if (!manufacturerEntity.isPresent()) {
            ManufacturerEntity manufacturerEntity1 = manufacturerEntity.get();
            manufacturerEntity1.setName(manufacturerDto.getName())
                    .setDescription(manufacturerDto.getDescription())
                    .setPhoto(manufacturerDto.getPhoto());
            return ManufaturerMapper.toManufacturerDto(manufacturerRepository.save(manufacturerEntity1));
        }
        throw new CustomException(ResultEnum.MANUFACTURER_ALREADY_EXIT);
    }

    @Override
    public void delete(int manufacturerId) {
        Optional<ManufacturerEntity> manufacturerEntity = Optional.ofNullable(manufacturerRepository.findByManufacturerId(manufacturerId));
        if (manufacturerEntity.isPresent()) {
            ManufacturerEntity manufacturerEntity1 = manufacturerEntity.get();
            manufacturerRepository.delete(manufacturerEntity1);
        } else {
            throw new CustomException(ResultEnum.MANUFACTURER_NOT_FOUND);
        }
    }
}
