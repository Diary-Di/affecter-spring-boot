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

import com.example.demo.entity.Employe;
import com.example.demo.service.EmployeService;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{codeemp}")
    public ResponseEntity<Employe> getEmployeById(
            @PathVariable String codeemp) {

        return employeService.getEmployeById(codeemp)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.saveEmploye(employe);
    }

    @PutMapping("/{codeemp}")
    public ResponseEntity<Employe> updateEmploye(
            @PathVariable String codeemp,
            @RequestBody Employe employe) {

        if (employeService.getEmployeById(codeemp).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        employe.setCodeemp(codeemp);

        return ResponseEntity.ok(
                employeService.saveEmploye(employe)
        );
    }

    @DeleteMapping("/{codeemp}")
    public ResponseEntity<Void> deleteEmploye(
            @PathVariable String codeemp) {

        if (employeService.getEmployeById(codeemp).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        employeService.deleteEmploye(codeemp);

        return ResponseEntity.noContent().build();
    }
}