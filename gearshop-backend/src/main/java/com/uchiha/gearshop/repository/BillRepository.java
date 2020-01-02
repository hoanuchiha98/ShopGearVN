package com.uchiha.gearshop.repository;

import com.uchiha.gearshop.model.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<BillEntity, Integer> {
//    BillEntity findByIdOrderByCreate_dateAsc(Integer billId);
}
