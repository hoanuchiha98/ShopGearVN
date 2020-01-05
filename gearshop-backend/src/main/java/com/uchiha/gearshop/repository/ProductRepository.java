package com.uchiha.gearshop.repository;

import com.uchiha.gearshop.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query("select u from ProductEntity u")
    List<ProductEntity> findAll();

    @Query("select u from ProductEntity u where u.id = :id")
    ProductEntity findByProductId(@Param("id") int id);

    @Query(value = "select * from product p order by p.id", nativeQuery = true)
    Page<ProductEntity> findAllByOrderById(Pageable pageable);

    @Query(value = "select * from product p where p.category_id = :category_id", nativeQuery = true)
    Page<ProductEntity> findAllByCategoryIdOrderByIdAsc(@Param("category_id") Integer category_id, Pageable pageable);

    @Query(value = "select * from product p where p.category_id = ?1 and p.manufacturer_id=?2 order by p.id", nativeQuery = true)
    Page<ProductEntity> findAllByCategoryIdAndManufacturerId(@Param("category_id") Integer categoryId, @Param("manufacturer_id") Integer manufacturerId, Pageable pageable);
}
