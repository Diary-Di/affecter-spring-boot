package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AffecterRequest;
import com.example.demo.entity.Affecter;
import com.example.demo.entity.AffecterId;
import com.example.demo.entity.Employe;
import com.example.demo.entity.Lieu;
import com.example.demo.repository.AffecterRepository;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.repository.LieuRepository;

@Service
public class AffecterService {

    private final AffecterRepository affecterRepository;
    private final EmployeRepository employeRepository;
    private final LieuRepository lieuRepository;

    public AffecterService(
            AffecterRepository affecterRepository,
            EmployeRepository employeRepository,
            LieuRepository lieuRepository) {

        this.affecterRepository = affecterRepository;
        this.employeRepository = employeRepository;
        this.lieuRepository = lieuRepository;
    }

    public List<Affecter> getAllAffectations() {
        return affecterRepository.findAll();
    }

    public Optional<Affecter> getAffectationById(AffecterId id) {
        return affecterRepository.findById(id);
    }

    public Affecter createAffectation(AffecterRequest request) {

        Employe employe = employeRepository
                .findById(request.getCodeemp())
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        Lieu lieu = lieuRepository
                .findById(request.getCodelieu())
                .orElseThrow(() ->
                        new RuntimeException("Location not found"));

        AffecterId id = new AffecterId(
                request.getCodeemp(),
                request.getCodelieu(),
                request.getDateAffecter()
        );

        if (affecterRepository.existsById(id)) {
            throw new RuntimeException(
                    "This assignment already exists");
        }

        Affecter affecter = new Affecter();

        affecter.setId(id);
        affecter.setEmploye(employe);
        affecter.setLieu(lieu);

        return affecterRepository.save(affecter);
    }

    public void deleteAffectation(AffecterId id) {
        affecterRepository.deleteById(id);
    }
}