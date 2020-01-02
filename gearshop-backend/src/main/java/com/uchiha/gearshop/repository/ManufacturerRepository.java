package com.uchiha.gearshop.repository;

import com.uchiha.gearshop.model.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Integer> {
    @Query(value = "select * from manufacturer c where c.id in (:manufacturerTypes) order by c.id", nativeQuery = true)
    List<ManufacturerEntity> findByNameInOrderByIdAsc(List<Integer> manufacturerTypes);

    @Query(value = "select * from manufacturer c order by c.id", nativeQuery = true)
    List<ManufacturerEntity> findAllByOrderById();

    @Query(value = "select * from manufacturer where id = ?1", nativeQuery = true)
    ManufacturerEntity findByManufacturerId(@Param("id") Integer id);

    @Query(value = "select * from manufacturer where manufacturer.name = ?1", nativeQuery = true)
    ManufacturerEntity findByManufacturerName(@Param("name") String name);
}
