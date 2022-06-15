package com.example.applicationlomography.services;

import com.example.applicationlomography.model.Livraison;
import com.example.applicationlomography.model.LivraisonDetail;

import java.util.ArrayList;

public interface IListenerAPIDetail {
    public void receiveLivraison(ArrayList<LivraisonDetail> livraisons);
}
