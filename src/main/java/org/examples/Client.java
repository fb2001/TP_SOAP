package org.examples;

public class Client {
    private String nom;
    private String prenom;
    private long cartecredit;
    public Client(String nom, String prenom, long cartecredit) {
        this.nom = nom;
        this.prenom = prenom;
        this.cartecredit = cartecredit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getCartecredit() {
        return cartecredit;
    }

    public void setCartecredit(long cartecredit) {
        this.cartecredit = cartecredit;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
