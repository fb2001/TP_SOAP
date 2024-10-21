package org.examples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class Chambre {
    private int numero;
    private String typechambre;
    private float prix;
    private enumchambre etatchambre;
    private ArrayList<Reservation> reservations;  // Liste des réservations
    private Hotel hotel;

    public Chambre(int numero, String typechambre, float prix, enumchambre etatchambre , Hotel hotel) {
        this.numero = numero;
        this.typechambre = typechambre;
        this.prix = prix;
        this.etatchambre = etatchambre;
        this.hotel = hotel;
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
    private Hotel getHotel() {
        return hotel;
    }
    // Méthode pour ajouter une réservation si la chambre est disponible
    public boolean reserver(LocalDate dateDebut, LocalDate dateFin, Client client) {
        if (estDisponible(dateDebut, dateFin)) {
            reservations.add(new Reservation(this, this.getHotel(), dateDebut, dateFin, client));
            this.etatchambre = enumchambre.reservé;
            return true;
        }
        return false;
    }

    public void libererChambre(LocalDate dateDebut, LocalDate dateFin) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getArrival().isEqual(dateDebut) && reservation.getDeparture().isEqual(dateFin)) {
                reservations.remove(i);
                if (reservations.isEmpty()) {
                    this.etatchambre = enumchambre.disponible; // Remettre à disponible si aucune réservation
                }
                break; // Sortir de la boucle après avoir libéré la chambre
            }
        }
    }


    // Méthode pour créer des chambres avec des prix aléatoires
    public static ArrayList<Chambre> createChambresWithRandomPrices(Hotel hotel) {
        ArrayList<Chambre> chambres = new ArrayList<>();
        Random rand = new Random();

        // Prix aléatoires entre 50 et 200 (par exemple)
        int prixSimple = 50 + rand.nextInt(151);  // Prix entre 50 et 200
        int prixDouble = 50 + rand.nextInt(151);
        int prixSuite = 50 + rand.nextInt(151);

        chambres.add(new Chambre(1, "Simple", prixSimple, enumchambre.disponible , hotel));
        chambres.add(new Chambre(2, "Double", prixDouble, enumchambre.disponible , hotel));
        chambres.add(new Chambre(3, "Suite", prixSuite, enumchambre.disponible ,hotel));

        return chambres;
    }
}
