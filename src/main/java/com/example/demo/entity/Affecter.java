package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "affecter")
public class Affecter {

    @EmbeddedId
    private AffecterId id;

    @ManyToOne
    @MapsId("codeemp")
    @JoinColumn(name = "codeemp")
    private Employe employe;

    @ManyToOne
    @MapsId("codelieu")
    @JoinColumn(name = "codelieu")
    private Lieu lieu;

    public Affecter() {
    }

    public AffecterId getId() {
        return id;
    }

    public void setId(AffecterId id) {
        this.id = id;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
}