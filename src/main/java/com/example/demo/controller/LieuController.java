package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Lieu;
import com.example.demo.service.LieuService;

@RestController
@RequestMapping("/api/lieux")
public class LieuController {

    private final LieuService lieuService;

    public LieuController(LieuService lieuService) {
        this.lieuService = lieuService;
    }

    @GetMapping
    public List<Lieu> getAllLieux() {
        return lieuService.getAllLieux();
    }

    @GetMapping("/{codelieu}")
    public ResponseEntity<Lieu> getLieuById(
            @PathVariable String codelieu) {

        return lieuService.getLieuById(codelieu)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Lieu createLieu(@RequestBody Lieu lieu) {
        return lieuService.saveLieu(lieu);
    }

    @PutMapping("/{codelieu}")
    public ResponseEntity<Lieu> updateLieu(
            @PathVariable String codelieu,
            @RequestBody Lieu lieu) {

        if (lieuService.getLieuById(codelieu).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        lieu.setCodelieu(codelieu);

        return ResponseEntity.ok(
                lieuService.saveLieu(lieu)
        );
    }

    @DeleteMapping("/{codelieu}")
    public ResponseEntity<Void> deleteLieu(
            @PathVariable String codelieu) {

        if (lieuService.getLieuById(codelieu).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        lieuService.deleteLieu(codelieu);

        return ResponseEntity.noContent().build();
    }
}