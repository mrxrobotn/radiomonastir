package com.example.radiomonastir.Models;

public class FicheIncident {
    private String ficheId;
    private String ficheDate;
    private String fichePanne;
    private String ficheObservation;
    private String fichenomtech;

    public FicheIncident(String ficheId, String ficheDate, String fichePanne, String ficheObservation) {
        this.ficheId = ficheId;
        this.ficheDate = ficheDate;
        this.fichePanne = fichePanne;
        this.ficheObservation = ficheObservation;
        this.fichenomtech=fichenomtech;
    }

    public FicheIncident(String id, String nom, String date, String panne, String observation) {
    }

    public String getFicheId() {
        return ficheId;
    }

    public void setFicheId(String ficheId) {
        this.ficheId = ficheId;
    }

    public String getFicheDate() {
        return ficheDate;
    }

    public void setFicheDate(String ficheDate) {
        this.ficheDate = ficheDate;
    }

    public String getFichePanne() {
        return fichePanne;
    }

    public void setFichePanne(String fichePanne) {
        this.fichePanne = fichePanne;
    }

    public String getFicheObservation() {
        return ficheObservation;
    }

    public void setFicheObservation(String ficheObservation) {
        this.ficheObservation = ficheObservation;
    }
    public String getFichenomtech(){return fichenomtech;}
    public void setFichenomtech(String fichenomtech){this.fichenomtech=fichenomtech;}
}
