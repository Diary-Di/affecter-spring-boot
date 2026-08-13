package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Lieu;
import com.example.demo.repository.LieuRepository;

@Service
public class LieuService {

    private final LieuRepository lieuRepository;

    public LieuService(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    public List<Lieu> getAllLieux() {
        return lieuRepository.findAll();
    }

    public Optional<Lieu> getLieuById(String codelieu) {
        return lieuRepository.findById(codelieu);
    }

    public Lieu saveLieu(Lieu lieu) {
        return lieuRepository.save(lieu);
    }

    public void deleteLieu(String codelieu) {
        lieuRepository.deleteById(codelieu);
    }
}