package com.example.applicationlomography.model;

import java.io.Serializable;

public class Livraison implements Serializable{

    private int idlivraison;
    private String dateExpedition;
    private String datePrevu;
    private String serviceLivraison;
    private String adresse;
    private String typeLivraison;

    public Livraison(int idlivraison, String dateExpedition, String datePrevu, String serviceLivraison, String adresse, String typeLivraison) {
        this.idlivraison = idlivraison;
        this.dateExpedition = dateExpedition;
        this.datePrevu = datePrevu;
        this.serviceLivraison = serviceLivraison;
        this.adresse = adresse;
        this.typeLivraison = typeLivraison;
    }

    public int getIdlivraison() {
        return idlivraison;
    }

    public void setIdlivraison(int idlivraison) {
        this.idlivraison = idlivraison;
    }

    public String getDateExpedition() {
        return dateExpedition;
    }

    public void setDateExpedition(String dateExpedition) {
        this.dateExpedition = dateExpedition;
    }

    public String getDatePrevu() {
        return datePrevu;
    }

    public void setDatePrevu(String datePrevu) {
        this.datePrevu = datePrevu;
    }

    public String getServiceLivraison() {
        return serviceLivraison;
    }

    public void setServiceLivraison(String serviceLivraison) {
        this.serviceLivraison = serviceLivraison;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeLivraison() {
        return typeLivraison;
    }

    public void setTypeLivraison(String typeLivraison) {
        this.typeLivraison = typeLivraison;
    }
}
