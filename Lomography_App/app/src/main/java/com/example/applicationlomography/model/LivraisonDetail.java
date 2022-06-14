package com.example.applicationlomography.model;

import java.io.Serializable;

public class LivraisonDetail {

    private int idproduit;
    private String img;
    private String nom;
    private float prix;
    private int qte;
    private float prixPanier;

    public LivraisonDetail(int idproduit, String img, String nom, float prix, int qte,
                           float prixPanier)
    {
        this.idproduit = idproduit;
        this.img = img;
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
        this.prixPanier = prixPanier;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrixPanier() {
        return prixPanier;
    }

    public void setPrixPanier(float prixPanier) {
        this.prixPanier = prixPanier;
    }
}
