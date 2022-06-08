package com.example.applicationlomography.services;

import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.model.Personnage;

import java.util.ArrayList;

public interface IListenerAPI {
    public void receiveLivraison(ArrayList<Livraison> livraisons);
}
