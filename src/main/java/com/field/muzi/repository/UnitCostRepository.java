package com.field.muzi.repository;

import com.field.muzi.domain.entity.UnitCostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitCostRepository extends JpaRepository<UnitCostEntity, String> {
    Optional<UnitCostEntity> findTopByOrderByUnitCostSeqDesc();
}
