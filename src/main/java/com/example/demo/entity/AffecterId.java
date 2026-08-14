package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AffecterId implements Serializable {

    private String codeemp;
    private String codelieu;
    private LocalDate date_affecter;

    public AffecterId() {
    }

    public AffecterId(String codeemp, String codelieu, LocalDate date_affecter) {
        this.codeemp = codeemp;
        this.codelieu = codelieu;
        this.date_affecter = date_affecter;
    }

    public String getCodeemp() {
        return codeemp;
    }

    public void setCodeemp(String codeemp) {
        this.codeemp = codeemp;
    }

    public String getCodelieu() {
        return codelieu;
    }

    public void setCodelieu(String codelieu) {
        this.codelieu = codelieu;
    }

    public LocalDate getDate_affecter() {
        return date_affecter;
    }

    public void setDate_affecter(LocalDate date_affecter) {
        this.date_affecter = date_affecter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AffecterId)) return false;

        AffecterId that = (AffecterId) o;

        return Objects.equals(codeemp, that.codeemp)
                && Objects.equals(codelieu, that.codelieu)
                && Objects.equals(date_affecter, that.date_affecter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeemp, codelieu, date_affecter);
    }
}