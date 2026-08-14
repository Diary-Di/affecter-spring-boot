package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Affecter;
import com.example.demo.entity.AffecterId;

public interface AffecterRepository
        extends JpaRepository<Affecter, AffecterId> {
}