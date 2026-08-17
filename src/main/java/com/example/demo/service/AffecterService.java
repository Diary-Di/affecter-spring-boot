package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
public Affecter updateAffectation(
        AffecterId oldId,
        AffecterRequest request) {

    // 1. Check that the old affectation exists
    Affecter oldAffectation = affecterRepository
            .findById(oldId)
            .orElseThrow(() ->
                    new RuntimeException("Affectation not found"));

    // 2. Check that the employee exists
    Employe employe = employeRepository
            .findById(request.getCodeemp())
            .orElseThrow(() ->
                    new RuntimeException("Employee not found"));

    // 3. Check that the location exists
    Lieu lieu = lieuRepository
            .findById(request.getCodelieu())
            .orElseThrow(() ->
                    new RuntimeException("Location not found"));

    // 4. Build the new composite ID
    AffecterId newId = new AffecterId(
            request.getCodeemp(),
            request.getCodelieu(),
            request.getDateAffecter()
    );

    // 5. Make sure the new ID isn't already used
    if (!oldId.equals(newId)
            && affecterRepository.existsById(newId)) {

        throw new RuntimeException(
                "The new affectation already exists");
    }

    // 6. Delete the old record
    affecterRepository.delete(oldAffectation);

    // 7. Create the new record
    Affecter newAffectation = new Affecter();

    newAffectation.setId(newId);
    newAffectation.setEmploye(employe);
    newAffectation.setLieu(lieu);

    // 8. Save the new record
    return affecterRepository.save(newAffectation);
}

    public void deleteAffectation(AffecterId id) {
        affecterRepository.deleteById(id);
    }
}