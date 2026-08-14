package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employe")
public class Employe {

    @Id
    private String codeemp;

    private String nom;
    private String prenom;
    private String poste;

    public Employe() {
    }

    public String getCodeemp() {
        return codeemp;
    }

    public void setCodeemp(String codeemp) {
        this.codeemp = codeemp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    @OneToMany(mappedBy = "employe")
@JsonIgnore
private List<Affecter> affectations;

public List<Affecter> getAffectations() {
    return affectations;
}

public void setAffectations(List<Affecter> affectations) {
    this.affectations = affectations;
}
}