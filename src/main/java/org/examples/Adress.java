package org.examples;

public class Adress {
    private String pays;
    private String ville;
    private String rue;
    private String lieu;

    public Adress(String pays, String ville, String rue, String lieu) {
        this.pays = pays;
        this.ville = ville;
        this.rue = rue;
        this.lieu = lieu;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return rue + ", " + lieu + ", " + ville + ", " + pays;
    }
}
