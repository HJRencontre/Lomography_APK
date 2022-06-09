package com.example.applicationlomography.model;

import java.io.Serializable;
import java.sql.Date;

public class CommandesModel implements Serializable
{
    private int id;
    private String name;
    private String image;
    private String description;

    public CommandesModel(int id, Date dateExpedition, Date datePrevu, String serviceLivraison, String adresse, String typeLivraison) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
