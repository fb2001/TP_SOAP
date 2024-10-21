package org.examples;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

import static org.examples.Chambre.createChambresWithRandomPrices;

public class Main {
    public static void main(String[] args) {

        // Création des hôtels
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel(4, new ArrayList<>(), "Paris, Rue de l'Exemple", 100, "Hotel Paradis"));
        hotels.add(new Hotel(5, new ArrayList<>(), "Chamonix, Rue de la Montagne", 120, "Hotel Montagne"));
        hotels.add(new Hotel(3, new ArrayList<>(), "Nice, Promenade des Anglais", 90, "Hotel Cote d'Azur"));
        hotels.add(new Hotel(4, new ArrayList<>(), "Lyon, Rue de la République", 110, "Hotel Lyonnais"));
        hotels.add(new Hotel(3, new ArrayList<>(), "Marseille, Vieux Port", 95, "Hotel du Vieux Port"));
        hotels.add(new Hotel(5, new ArrayList<>(), "Bordeaux, Place de la Bourse", 150, "Hotel de Bordeaux"));
        hotels.add(new Hotel(4, new ArrayList<>(), "Strasbourg, Grande Île", 105, "Hotel Alsace"));
        hotels.add(new Hotel(3, new ArrayList<>(), "Toulouse, Capitole", 85, "Hotel du Capitole"));
        hotels.add(new Hotel(4, new ArrayList<>(), "Nantes, Cours des 50 Otages", 115, "Hotel Atlantique"));
        hotels.add(new Hotel(5, new ArrayList<>(), "Lille, Place du Général de Gaulle", 160, "Hotel de Lille"));
        hotels.add(new Hotel(2, new ArrayList<>(), "Avignon, Rue des Teinturiers", 70, "Hotel Avignon"));
        hotels.add(new Hotel(2, new ArrayList<>(), "Clermont-Ferrand, Boulevard François Mitterrand", 75, "Hotel Clermont"));
        hotels.add(new Hotel(2, new ArrayList<>(), "Brest, Quai de la Douane", 65, "Hotel Brestois"));

        // Ajouter les chambres aux hôtels
        for (Hotel hotel : hotels) {
            hotel.setChambre(createChambresWithRandomPrices(hotel));
        }

        // Création d'un scanner pour la saisie utilisateur
        Scanner scanner = new Scanner(System.in);
        while (true) {

            // Saisie des détails du client
            System.out.println("\nVeuillez entrer les détails du client :");
            System.out.print("Nom : ");
            String nomClient = scanner.nextLine();
            System.out.print("Prénom : ");
            String prenomClient = scanner.nextLine();

            // Demande au client d'intégrer la ville pour chercher dans l'adresse des hôtels disponibles
            System.out.print("\nEntrer la ville où vous voulez réserver : ");
            String villeHotel = scanner.nextLine();

            // Liste pour stocker les hôtels disponibles dans la ville recherchée
            ArrayList<Hotel> hotelsDisponibles = new ArrayList<>();
            int compteur = 0;
            for (Hotel hotel : hotels) {
                if (hotel.getAdress().contains(villeHotel)) {
                    hotelsDisponibles.add(hotel);
                    compteur++;
                    System.out.println("\nOn propose l'hôtel : ");
                    System.out.println(compteur + ") " + hotel.getNom() + " - " + hotel.getNbretoiles() + " étoiles - " + hotel.getAdress());
                }
            }

            // Vérification si des hôtels ont été trouvés
            if (hotelsDisponibles.isEmpty()) {
                System.out.println("\nAucun hôtel trouvé dans cette ville. Veuillez réessayer.");
                scanner.close();
                return; // Terminer le programme si aucun hôtel n'est trouvé
            }

            // Création d'une demande dépendant des hôtels disponibles
            System.out.print("\nVeuillez choisir un hôtel (entrez le numéro correspondant) : ");
            int choixHotel = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne restante

            // Vérification si le choix est valide
            if (choixHotel < 1 || choixHotel > hotelsDisponibles.size()) {
                System.out.println("Choix invalide. Veuillez relancer le programme.");
                scanner.close();
                return;
            }

            // Récupérer l'hôtel choisi
            Hotel hotelChoisi = hotelsDisponibles.get(choixHotel - 1);


            // Saisie des dates de réservation
            System.out.print("Date d'arrivée (format yyyy-mm-dd) : ");
            String dateArriveeStr = scanner.nextLine();
            System.out.print("Date de départ (format yyyy-mm-dd) : ");
            String dateDepartStr = scanner.nextLine();

            // Convertir les chaînes en objets Date
            LocalDate arrivalDate = LocalDate.parse(dateArriveeStr);
            LocalDate departureDate = LocalDate.parse(dateDepartStr);


            // Vérification de la validité des dates
            if (departureDate.isAfter(arrivalDate)) {
                ArrayList<Chambre> chambresDisponibles = new ArrayList<>();

                // donner les chambres disponibles dans cette date
                for (Chambre chambre : hotelChoisi.getChambre()) {
                    if (chambre.estDisponible(arrivalDate, departureDate)) {
                        chambresDisponibles.add(chambre);
                    }
                }

                // Afficher les chambres disponibles

                if (chambresDisponibles.isEmpty()) {
                    System.out.println("Désolé, aucune chambre n'est disponible pour ces dates.");
                } else {
                    System.out.println("Chambres disponibles :");
                    for (Chambre chambre : chambresDisponibles) {
                        System.out.println("Chambre n°" + chambre.getNumero() + " - " + chambre.getTypechambre() + " - Prix: " + chambre.getPrix() + " €");
                    }
                }

                // Demande à l'utilisateur de choisir une chambre
                System.out.print("Veuillez choisir une chambre (entrez le numéro correspondant) : ");
                int choixChambre = scanner.nextInt();
                scanner.nextLine(); // Consomme la nouvelle ligne restante

                // Récupérer la chambre choisie
                Chambre chambreChoisie = chambresDisponibles.get(choixChambre - 1);

                Client client = new Client(nomClient, prenomClient, 0);


                System.out.print("Numéro de carte de crédit : ");
                int carteCredit = scanner.nextInt();
                scanner.nextLine();  // Consomme la nouvelle ligne restante
                client.setCartecredit(carteCredit);

                // Calcul du nombre de jours entre les deux dates
                long daysBetween = ChronoUnit.DAYS.between(arrivalDate, departureDate);

                // Création de la réservation
                Reservation reservation = new Reservation(chambreChoisie, hotelChoisi, arrivalDate, departureDate, client);

                // Réserver la chambre
                chambreChoisie.reserver(arrivalDate, departureDate, client);


                // Affichage de la réservation
                System.out.println("\nRéservation effectuée :");
                System.out.println("Client : " + reservation.getClient().getPrenom() + " " + reservation.getClient().getNom());
                System.out.println("Hôtel : " + reservation.getHotel().getNom());
                System.out.println("Date d'arrivée : " + reservation.getArrival());
                System.out.println("Date de départ : " + reservation.getDeparture());
                System.out.println("Prix total : " + (chambreChoisie.getPrix() * daysBetween) + " €");

                System.out.println("La chambre n°" + chambreChoisie.getNumero() + " a été réservée avec succès du "
                        + arrivalDate + " au " + departureDate + ".");


                // Changer l'énumération de la chambre de disponible à réservée
                //chambreChoisie.setDisponibilite(false); // Assurez-vous d'avoir cette méthode pour changer l'état de la chambre



            } else {
                System.out.println("Erreur : La date de départ doit être postérieure à la date d'arrivée. Veuillez réessayer.");
            }
            System.out.print("\nSouhaitez-vous faire une autre réservation ? (oui/non) : ");
            String reponse = scanner.nextLine();
            if (!reponse.equalsIgnoreCase("oui")) {
                break;
            }

        }

        scanner.close();



    }
}
