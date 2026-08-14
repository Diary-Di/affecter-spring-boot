package com.example.demo.dto;

import java.time.LocalDate;

public class AffecterRequest {

    private String codeemp;
    private String codelieu;
    private LocalDate dateAffecter;

    public AffecterRequest() {
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

    public LocalDate getDateAffecter() {
        return dateAffecter;
    }

    public void setDateAffecter(LocalDate dateAffecter) {
        this.dateAffecter = dateAffecter;
    }
}