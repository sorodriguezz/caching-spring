package com.sorz.cachingspring.services;

import com.sorz.cachingspring.entities.ChemicalCompoundEntity;
import com.sorz.cachingspring.repositories.CCRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"chemical_compounds"})
public class CCService {
    private CCRepository repository;

    public CCService(CCRepository repository) {
        this.repository = repository;
    }

    @CachePut(value = "chemical_compounds", key = "#root.args[0]")
    public ChemicalCompoundEntity create(String ccName, String ccFormula) {
        return repository.save(ChemicalCompoundEntity.builder().ccName(ccName).ccFormula(ccFormula).build());
    }

    @Cacheable(value = "chemical_compounds", key = "#root.args[0]")
    public ChemicalCompoundEntity getByName(String name) {
        return repository.findByCcName(name).get();
    }

    @CachePut(value = "chemical_compounds", key = "#root.args[0]")
    public ChemicalCompoundEntity updateFormula(String ccName, String ccFormula) {
        Optional<ChemicalCompoundEntity> compound = repository.findByCcName(ccName);
        if (compound.isPresent()) {
            ChemicalCompoundEntity chemComp = compound.get();
            chemComp.setCcFormula(ccFormula);
            return repository.save(chemComp);
        }
        return null;
    }

    @Transactional
    @CacheEvict(value="chemical_compounds", key="#root.args[0]")
    public void deleteCompound(String ccName) {
        repository.deleteByCcName(ccName);
    }

    @CacheEvict(value="chemical_compounds", allEntries = true, key="#root.args[0]")
    public void deleteAllCompound() {
        repository.deleteAll();
    }
}
