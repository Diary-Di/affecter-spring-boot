package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.dto.AffecterRequest;
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

    @PostMapping
public Affecter createAffectation(
        @RequestBody AffecterRequest request) {

    return affecterService.createAffectation(request);
}

@PutMapping("/{codeemp}/{codelieu}/{date}")
public ResponseEntity<Affecter> updateAffectation(
        @PathVariable String codeemp,
        @PathVariable String codelieu,
        @PathVariable LocalDate date,
        @RequestBody AffecterRequest request) {

    AffecterId oldId =
            new AffecterId(codeemp, codelieu, date);

    if (affecterService.getAffectationById(oldId).isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    Affecter updated =
            affecterService.updateAffectation(oldId, request);

    return ResponseEntity.ok(updated);
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