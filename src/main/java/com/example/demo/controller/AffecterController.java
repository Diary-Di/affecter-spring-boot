package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Affecter;
import com.example.demo.entity.AffecterId;
import com.example.demo.service.AffecterService;

@RestController
@RequestMapping("/api/affectations")
public class AffecterController {

    private final AffecterService affecterService;

    public AffecterController(AffecterService affecterService) {
        this.affecterService = affecterService;
    }

    @GetMapping
    public List<Affecter> getAllAffectations() {
        return affecterService.getAllAffectations();
    }

    @GetMapping("/{codeemp}/{codelieu}/{date}")
    public ResponseEntity<Affecter> getAffectation(
            @PathVariable String codeemp,
            @PathVariable String codelieu,
            @PathVariable LocalDate date) {

        AffecterId id =
                new AffecterId(codeemp, codelieu, date);

        return affecterService.getAffectationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codeemp}/{codelieu}/{date}")
    public ResponseEntity<Void> deleteAffectation(
            @PathVariable String codeemp,
            @PathVariable String codelieu,
            @PathVariable LocalDate date) {

        AffecterId id =
                new AffecterId(codeemp, codelieu, date);

        if (affecterService.getAffectationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        affecterService.deleteAffectation(id);

        return ResponseEntity.noContent().build();
    }
}