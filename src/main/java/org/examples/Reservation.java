package org.examples;

import java.time.LocalDate;

public class Reservation {
    private Chambre chambre;
    private LocalDate arrival;
    private LocalDate departure;
    private Client client;

    public Reservation(Chambre chambre, LocalDate arrival, LocalDate departure, Client client) {
        this.chambre = chambre;
        this.arrival = arrival;
        this.departure = departure;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }




}
