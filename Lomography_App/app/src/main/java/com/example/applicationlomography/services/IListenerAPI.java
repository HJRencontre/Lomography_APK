package com.example.applicationlomography.services;

import com.example.applicationlomography.model.Personnage;

import java.util.ArrayList;

public interface IListenerAPI {

    public  void receivePersonnages(ArrayList<Personnage> personnages);
}
