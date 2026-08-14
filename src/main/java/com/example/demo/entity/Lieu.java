package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lieu")
public class Lieu {

    @Id
    private String codelieu;

    private String designation;
    private String province;

    public Lieu() {
    }

    public String getCodelieu() {
        return codelieu;
    }

    public void setCodelieu(String codelieu) {
        this.codelieu = codelieu;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @OneToMany(mappedBy = "lieu")
@JsonIgnore
private List<Affecter> affectations;

public List<Affecter> getAffectations() {
    return affectations;
}

public void setAffectations(List<Affecter> affectations) {
    this.affectations = affectations;
}
}