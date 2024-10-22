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
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Paris", "Rue de l'Exemple", "Quartier Paradis"), "Hotel Paradis"));
        hotels.add(new Hotel(5, new ArrayList<>(), new Adress("France", "Paris", "Avenue des Champs-Élysées", "Centre-Ville"), "Hotel des Champs"));
        hotels.add(new Hotel(5, new ArrayList<>(), new Adress("France", "Chamonix", "Rue de la Montagne", "Centre-Ville"), "Hotel Montagne"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Chamonix", "Place Balmat", "Quartier Balmat"), "Hotel du Mont Blanc"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Nice", "Promenade des Anglais", "Front de mer"), "Hotel Cote d'Azur"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Nice", "Avenue Jean Médecin", "Centre-Ville"), "Hotel Nice Médecin"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Lyon", "Rue de la République", "Presqu'île"), "Hotel Lyonnais"));
        hotels.add(new Hotel(5, new ArrayList<>(), new Adress("France", "Lyon", "Place Bellecour", "Quartier Bellecour"), "Hotel Bellecour"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Marseille", "Vieux Port", "Centre historique"), "Hotel du Vieux Port"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Marseille", "Rue Saint-Ferréol", "Quartier des Noailles"), "Hotel Saint-Ferréol"));
        hotels.add(new Hotel(5, new ArrayList<>(), new Adress("France", "Bordeaux", "Place de la Bourse", "Quartier Saint-Pierre"), "Hotel de Bordeaux"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Bordeaux", "Cours de l'Intendance", "Centre-Ville"), "Hotel Intendance"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Strasbourg", "Grande Île", "Centre historique"), "Hotel Alsace"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Strasbourg", "Rue des Frères", "Quartier Cathédrale"), "Hotel de la Cathédrale"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Toulouse", "Capitole", "Place du Capitole"), "Hotel du Capitole"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Toulouse", "Rue Alsace Lorraine", "Centre-Ville"), "Hotel Alsace Lorraine"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Nantes", "Cours des 50 Otages", "Centre-Ville"), "Hotel Atlantique"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Nantes", "Place Royale", "Quartier Bouffay"), "Hotel Royal Nantes"));
        hotels.add(new Hotel(5, new ArrayList<>(), new Adress("France", "Lille", "Place du Général de Gaulle", "Grand'Place"), "Hotel de Lille"));
        hotels.add(new Hotel(4, new ArrayList<>(), new Adress("France", "Lille", "Rue de Paris", "Centre historique"), "Hotel Lille Opéra"));
        hotels.add(new Hotel(2, new ArrayList<>(), new Adress("France", "Avignon", "Rue des Teinturiers", "Centre historique"), "Hotel Avignon"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Avignon", "Place de l'Horloge", "Quartier de l'Horloge"), "Hotel de l'Horloge"));
        hotels.add(new Hotel(2, new ArrayList<>(), new Adress("France", "Clermont-Ferrand", "Boulevard François Mitterrand", "Centre-Ville"), "Hotel Clermont"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Clermont-Ferrand", "Place de Jaude", "Quartier Jaude"), "Hotel Jaude"));
        hotels.add(new Hotel(2, new ArrayList<>(), new Adress("France", "Brest", "Quai de la Douane", "Port"), "Hotel Brestois"));
        hotels.add(new Hotel(3, new ArrayList<>(), new Adress("France", "Brest", "Rue Jean Jaurès", "Centre-Ville"), "Hotel Jean Jaurès"));


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
                if (hotel.getAdress().getVille().toLowerCase().contains(villeHotel.toLowerCase())) {
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


            // Demande des personnes à héberger :

            System.out.print("Nombre de personnes à héberger : ");
            int nombreDePersonnes = scanner.nextInt();

            // Intervalle de prix :

            System.out.print("Intervalle de prix (départ) : ");
            int prixMin = scanner.nextInt();

            System.out.print("Intervalle de prix (limite) : ");
            int prixMax = scanner.nextInt();



            // Vérification de la validité des dates
            if (departureDate.isAfter(arrivalDate)) {
                ArrayList<Chambre> chambresDisponibles = new ArrayList<>();

                // Parcours des chambres pour trouver celles qui sont disponibles
                for (Chambre chambre : hotelChoisi.getChambre()) {
                    if (chambre.estDisponible(arrivalDate, departureDate)
                            && chambre.getNombrepersonne() >= nombreDePersonnes // Vérification du nombre de personnes
                            && chambre.getPrix() >= prixMin && chambre.getPrix() <= prixMax) { // Vérification de l'intervalle de prix
                        chambresDisponibles.add(chambre);
                    }
                }

                // Afficher les chambres disponibles
                if (chambresDisponibles.isEmpty()) {
                    System.out.println("Aucune chambre disponible pour ces critères.");
                    return;
                } else {
                    System.out.println("Chambres disponibles : ");
                    for (Chambre chambre : chambresDisponibles) {
                        System.out.println("Chambre  "  + " - Type : " + chambre.getTypechambre() +
                                " - Prix : " + chambre.getPrix() + " - Capacité : " + chambre.getNombrepersonne() + " personnes.");
                    }
                }

                // Demande à l'utilisateur de choisir une chambre
                System.out.print("Veuillez choisir une chambre  : ");
                int choixChambre = scanner.nextInt();
                scanner.nextLine();


                // Vérification si le choix est valide
                if (choixChambre < 1 || choixChambre > chambresDisponibles.size()) {
                    System.out.println("Choix invalide. Veuillez refaire une autre recherche.");
                    scanner.close();
                    return;
                }

                // Récupérer la chambre choisie
                Chambre chambreChoisie = chambresDisponibles.get(choixChambre - 1);



                System.out.print("Numéro de carte de crédit : ");
                long carteCredit = scanner.nextLong();
                scanner.nextLine();  // Consomme la nouvelle ligne restante

                CarteBancaire carte  = new CarteBancaire(carteCredit);

                Client client = new Client(nomClient, prenomClient, carte);


                // Calcul du nombre de jours entre les deux dates
                long daysBetween = ChronoUnit.DAYS.between(arrivalDate, departureDate);

                // Création de la réservation
                Reservation reservation = new Reservation(chambreChoisie, arrivalDate, departureDate, client);

                // Réserver la chambre
                chambreChoisie.reserver(arrivalDate, departureDate, client);


                // Affichage de la réservation
                System.out.println("\nRéservation effectuée :");
                System.out.println("Client : " + reservation.getClient().getPrenom() + " " + reservation.getClient().getNom());
                System.out.println("Hôtel : " + reservation.getChambre().getHotel().getNom());
                System.out.println("Date d'arrivée : " + reservation.getArrival());
                System.out.println("Date de départ : " + reservation.getDeparture());
                System.out.println("Prix total : " + (chambreChoisie.getPrix() * daysBetween) + " €");

                System.out.println("La chambre n°" + chambreChoisie.getNumero() + " a été réservée avec succès du "
                        + arrivalDate + " au " + departureDate + ".");
                System.out.println("Avec une carte : "+carteCredit);
                System.out.print("\nSouhaitez-vous faire une autre réservation ? (oui/non) : ");




            } else {
                System.out.println("Erreur : La date de départ doit être postérieure à la date d'arrivée. Veuillez réessayer.");
            }
            String reponse = scanner.nextLine();
            if (!reponse.equalsIgnoreCase("oui")) {
                break;
            }
        }

        scanner.close();



    }
}
