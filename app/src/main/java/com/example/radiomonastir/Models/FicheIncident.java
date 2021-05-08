package com.example.radiomonastir.Models;

public class FicheIncident {
    private String ficheId;
    private String ficheNom;
    private String ficheDate;
    private String fichePanne;
    private String ficheObservation;


    public FicheIncident(String ficheNom, String ficheId, String ficheDate, String fichePanne, String ficheObservation) {
        this.ficheId = ficheId;
        this.ficheNom = ficheNom;
        this.ficheDate = ficheDate;
        this.fichePanne = fichePanne;
        this.ficheObservation = ficheObservation;

    }

    public FicheIncident() {
    }

    public String getFicheId() {
        return ficheId;
    }

    public void setFicheId(String ficheId) {
        this.ficheId = ficheId;
    }

    public String getFicheNom() {
        return ficheNom;
    }

    public void setFicheNom(String ficheNom) {
        this.ficheNom = ficheNom;
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
}
