package com.sorz.cachingspring.repositories;

import com.sorz.cachingspring.entities.ChemicalCompoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CCRepository extends JpaRepository<ChemicalCompoundEntity,Long> {
    Optional<ChemicalCompoundEntity> findByCcName(String ccName);
    void deleteByCcName(String ccName);
}
