package org.examples;

public class Client {
    private String nom;
    private String prenom;
    private CarteBancaire cartebancaire;
    public Client(String nom, String prenom, CarteBancaire cartebancaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.cartebancaire = cartebancaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CarteBancaire getCartebancaire() {
        return cartebancaire;
    }

    public void setCartebancaire(CarteBancaire cartebancaire) {
        this.cartebancaire = cartebancaire;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
