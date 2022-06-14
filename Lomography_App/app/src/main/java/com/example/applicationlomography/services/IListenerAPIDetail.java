package com.example.applicationlomography.services;

import com.example.applicationlomography.model.Livraison;

import java.util.ArrayList;

public interface IListenerAPIDetail {
    public void receiveLivraison(ArrayList<Livraison> livraisons);
}
