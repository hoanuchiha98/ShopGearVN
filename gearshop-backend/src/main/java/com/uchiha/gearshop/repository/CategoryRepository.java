package com.uchiha.gearshop.repository;

import com.uchiha.gearshop.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "select * from category c where c.id in (:id) order by c.id", nativeQuery = true)
    List<CategoryEntity> findByIdInOrderByIdAsc(@Param("id") List<Integer> id);

    @Query(value = "select * from category order by id", nativeQuery = true)
    List<CategoryEntity> findAllByOrderById();

    @Query(value = "select * from category where id = ?1", nativeQuery = true)
    CategoryEntity findByCategoryId(@Param("id") Integer id);

    @Query(value = "select * from category where category.name = ?1", nativeQuery = true)
    CategoryEntity findByCategoryName(@Param("name") String name);
}
