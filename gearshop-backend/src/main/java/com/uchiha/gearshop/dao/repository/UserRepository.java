package com.uchiha.gearshop.dao.repository;

import com.uchiha.gearshop.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "select u from UserEntity u where u.username = :username")
    UserEntity findByUsername(String username);
}
