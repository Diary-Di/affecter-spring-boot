package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Affecter;
import com.example.demo.entity.AffecterId;
import com.example.demo.repository.AffecterRepository;

@Service
public class AffecterService {

    private final AffecterRepository affecterRepository;

    public AffecterService(AffecterRepository affecterRepository) {
        this.affecterRepository = affecterRepository;
    }

    public List<Affecter> getAllAffectations() {
        return affecterRepository.findAll();
    }

    public Optional<Affecter> getAffectationById(AffecterId id) {
        return affecterRepository.findById(id);
    }

    public Affecter saveAffectation(Affecter affecter) {
        return affecterRepository.save(affecter);
    }

    public void deleteAffectation(AffecterId id) {
        affecterRepository.deleteById(id);
    }
}