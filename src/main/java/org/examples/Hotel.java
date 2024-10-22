package org.examples;

import java.util.ArrayList;

public class Hotel {
    private String nom;
    private Adress adress;
    private int nbretoiles;
    private ArrayList<Chambre> chambre;

    public Hotel(int nbretoiles, ArrayList<Chambre> chambre, Adress adress, String nom) {
        this.nbretoiles = nbretoiles;
        this.chambre = chambre;
        this.adress = adress;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }




    public int getNbretoiles() {
        return nbretoiles;
    }

    public void setNbretoiles(int nbretoiles) {
        this.nbretoiles = nbretoiles;
    }

    public ArrayList<Chambre> getChambre() {
        return chambre;
    }

    public void setChambre(ArrayList<Chambre> chambre) {
        this.chambre = chambre;
    }
}