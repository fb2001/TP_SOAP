package org.examples;

import java.util.ArrayList;

public class Hotel {
    private String nom;
    private String adress;
    private int prix;
    private int nbretoiles;
    private ArrayList<Chambre> chambre;

    public Hotel(int nbretoiles, ArrayList<Chambre> chambre, String adress, int prix, String nom) {
        this.nbretoiles = nbretoiles;
        this.chambre = chambre;
        this.adress = adress;
        this.prix = prix;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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