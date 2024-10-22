package org.examples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Chambre {
    private int numero;
    private String typechambre;
    private float prix;
    private enumchambre etatchambre;
    private ArrayList<Reservation> reservations;  // Liste des réservations
    private Hotel hotel;
    private int nombrepersonne;

    public Chambre(int numero, String typechambre, float prix, enumchambre etatchambre , Hotel hotel , int nombrepersonne) {
        this.numero = numero;
        this.typechambre = typechambre;
        this.prix = prix;
        this.etatchambre = etatchambre;
        this.hotel = hotel;
        this.nombrepersonne = nombrepersonne;
        this.reservations = new ArrayList<>();
    }

    // Getters et Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTypechambre() {
        return typechambre;
    }

    public void setTypechambre(String typechambre) {
        this.typechambre = typechambre;
    }

    public enumchambre getEtatchambre() {
        return etatchambre;
    }

    public void setEtatchambre(enumchambre etatchambre) {
        this.etatchambre = etatchambre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNombrepersonne() {
        return nombrepersonne;
    }

    public void setNombrepersonne(int nombrepersonne) {
        this.nombrepersonne = nombrepersonne;
    }

    // Méthode pour vérifier si la chambre est disponible sur une période donnée
    public boolean estDisponible(LocalDate dateDebut, LocalDate dateFin) {
        for (Reservation reservation : reservations) {
            if (reservation.getArrival().isBefore(dateFin) && reservation.getDeparture().isAfter(dateDebut)) {
                // Si les dates de la réservation chevauchent la période donnée
                return false;
            }
        }
        return true;
    }
    public Hotel getHotel() {
        return hotel;
    }
    // Méthode pour ajouter une réservation si la chambre est disponible
    public boolean reserver(LocalDate dateDebut, LocalDate dateFin, Client client) {
        if (estDisponible(dateDebut, dateFin)) {
            reservations.add(new Reservation(this, dateDebut, dateFin, client));
            this.etatchambre = enumchambre.reservé;
            return true;
        }
        return false;
    }


    public void libererChambre(LocalDate dateDebut, LocalDate dateFin) {
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) {
            Reservation reservation = it.next();
            if (reservation.getArrival().isEqual(dateDebut) && reservation.getDeparture().isEqual(dateFin)) {
                it.remove();
                if (reservations.isEmpty()) {
                    this.etatchambre = enumchambre.disponible;
                }
                break;
            }
        }
    }



    // Méthode pour créer des chambres avec des prix aléatoires
    public static ArrayList<Chambre> createChambresWithRandomPrices(Hotel hotel) {
        ArrayList<Chambre> chambres = new ArrayList<>();
        Random rand = new Random();

        chambres.add(new Chambre(1, "Simple", 50 + rand.nextInt(151), enumchambre.disponible, hotel, 1));
        chambres.add(new Chambre(2, "Double", 50 + rand.nextInt(151), enumchambre.disponible, hotel, 2));
        chambres.add(new Chambre(3, "Suite", 50 + rand.nextInt(151), enumchambre.disponible, hotel, 3));
        chambres.add(new Chambre(4, "Suite familliale", 102 + rand.nextInt(151), enumchambre.disponible, hotel, 4));
        chambres.add(new Chambre(5, "Villa", 140 + rand.nextInt(151), enumchambre.disponible, hotel, 6));

        return chambres;
    }

}
