package com.sorz.cachingspring.controllers;

import com.sorz.cachingspring.entities.ChemicalCompoundEntity;
import com.sorz.cachingspring.services.CCService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CCRestController {
    private CCService ccService;

    public CCRestController(CCService ccService) {
        this.ccService = ccService;
    }

    @GetMapping("/create")
    public ResponseEntity<String> create(@RequestParam("name") String ccName, @RequestParam("formula") String ccFormula){
        ccService.create(ccName,ccFormula);
        return ResponseEntity.ok("Chemical Compound added");
    }

    @GetMapping("/get")
    public ResponseEntity<ChemicalCompoundEntity> getChemical(@RequestParam("name") String name){
        return ResponseEntity.ok(ccService.getByName(name));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFormula(@RequestParam("name") String ccName, @RequestParam("formula") String ccFormula){
        ccService.updateFormula(ccName,ccFormula);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("name") String ccName){
        ccService.deleteCompound(ccName);
        return ResponseEntity.ok("Deleted");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll(){
        ccService.deleteAllCompound();
        return ResponseEntity.ok("All Deleted.");
    }
}

