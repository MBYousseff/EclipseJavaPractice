package m.m;
import java.util.Scanner;

public class Main {

    // ── Constantes ──
    static final int MAX_PATIENTS = 100;
    static String[] nomsPatients = new String[MAX_PATIENTS];
    static String[] prenomsPatients = new String[MAX_PATIENTS];
    static int[] anneesNaissance = new int[MAX_PATIENTS];
    static int nbPatients = 0;
    //EX4
    static String[] services = {"Urgences", "Cardiologie", "Pédiatrie"};
    static int[] capacite = {2, 2, 2};
    static int[] nbDansService = {0, 0, 0};


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            choix = lireChoix(scanner);

            switch (choix) {
                case 1 -> ajouterPatient(scanner);
                case 2 -> afficherPatients();
                case 3 -> rechercherPatient(scanner);
                case 4 -> afficherStatistiques(); //EX2
                case 5 -> trierParNom(); //EX3
                case 0 -> System.out.println("\n👋 Au revoir !");
                default -> System.out.println("⚠ Choix invalide.");
            }
        } while (choix != 0);

        scanner.close();
    }

    // ── Affichage du menu ──
    static void afficherMenu() {
        System.out.println("\n══════ MedManager v0.1 ══════");
        System.out.println("  1. ➕ Ajouter un patient");
        System.out.println("  2. 📋 Afficher tous les patients");
        System.out.println("  3. 🔍 Rechercher un patient");
        System.out.println("  4. 📊 Statistiques"); //EX2
        System.out.println("  5. 📊 Trier par nom"); //EX3
        System.out.println("  0. 🚪 Quitter");
        System.out.print("Votre choix : ");
    }

    // ── Lire un choix entier en toute sécurité ──
    static int lireChoix(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("⚠ Entrez un nombre : ");
            scanner.next();  // consomme l'entrée invalide
        }
        int choix = scanner.nextInt();
        scanner.nextLine();  // nettoie le buffer
        return choix;
    }

    // ── Ajouter un patient ──
    static void ajouterPatient(Scanner scanner) {
        if (nbPatients >= MAX_PATIENTS) {
            System.out.println("⚠ Capacité maximale atteinte !");
            return;
        }

        System.out.println("\n--- Nouveau Patient ---");

        System.out.print("Nom : ");
        nomsPatients[nbPatients] = scanner.nextLine();

        System.out.print("Prénom : ");
        prenomsPatients[nbPatients] = scanner.nextLine();

        //EXERCISE 1: 
        int annee;
        int age;

        do {
            System.out.print("Année de naissance : ");
            annee = lireChoix(scanner);

            age = 2026 - annee;

            if (age < 0 || age > 150) {
                System.out.println("Âge incorrect. Réessayez.");
            }

        } while (age < 0 || age > 150);

        anneesNaissance[nbPatients] = annee;
        nbPatients++;
        
        //EXERCICE4
        System.out.println("Choisir un service :");

        for (int i = 0; i < services.length; i++) {
            System.out.println((i + 1) + ". " + services[i]);
        }

        int choixService = lireChoix(scanner) - 1;

        if (choixService >= 0 && choixService < services.length) {

            if (nbDansService[choixService] < capacite[choixService]) {
                nbDansService[choixService]++;
                
            } else {
                System.out.println("Service complet.");
                return;
            }

        } else {
            System.out.println("Service invalide.");
            return;
        }


        System.out.println("✅ Patient enregistré (" + age + " ans) est au service "+ services[choixService]);
    }
    	//FIN EX1
    
    	//EXERCICE 2
    
    static void afficherStatistiques() {

        if (nbPatients == 0) {
            System.out.println("Aucun patient.");
            return;
        }

        int totalAge = 0;
        int ageMin = 2026 - anneesNaissance[0];
        int ageMax = ageMin;

        for (int i = 0; i < nbPatients; i++) {

            int age = 2026 - anneesNaissance[i];

            totalAge += age;

            if (age < ageMin) {
                ageMin = age;
            }

            if (age > ageMax) {
                ageMax = age;
            }
        }

        double ageMoyen = (double) totalAge / nbPatients;

        System.out.println("Total patients : " + nbPatients);
        System.out.println("Âge moyen : " + ageMoyen);
        System.out.println("Plus jeune : " + ageMin);
        System.out.println("Plus âgé : " + ageMax);
    }
    //FIN EX2
    
    	//EXERCICE 3
    
    static void trierParNom() {

        for (int i = 0; i < nbPatients - 1; i++) {

            for (int j = 0; j < nbPatients - 1; j++) {

                if (nomsPatients[j].compareToIgnoreCase(nomsPatients[j + 1]) > 0) {

                    // swap nom
                    String tempNom = nomsPatients[j];
                    nomsPatients[j] = nomsPatients[j + 1];
                    nomsPatients[j + 1] = tempNom;

                    // swap prenom
                    String tempPrenom = prenomsPatients[j];
                    prenomsPatients[j] = prenomsPatients[j + 1];
                    prenomsPatients[j + 1] = tempPrenom;

                    // swap annee
                    int tempAnnee = anneesNaissance[j];
                    anneesNaissance[j] = anneesNaissance[j + 1];
                    anneesNaissance[j + 1] = tempAnnee;
                }
            }
        }

        System.out.println("Tri terminé.");
    }
    //FIN EX3

    // ── Afficher tous les patients ──
    static void afficherPatients() {
        if (nbPatients == 0) {
            System.out.println("\nAucun patient enregistré.");
            return;
        }

        System.out.println("\n--- Liste des Patients ---");
        System.out.printf("%-4s %-15s %-15s %s%n",
            "#", "Nom", "Prénom", "Âge");
        System.out.println("─".repeat(45));

        for (int i = 0; i < nbPatients; i++) {
            int age = 2026 - anneesNaissance[i];
            System.out.printf("%-4d %-15s %-15s %d ans%n",
                (i + 1), nomsPatients[i], prenomsPatients[i], age);
        }
        System.out.println("Total : " + nbPatients + " patient(s)");
    }

    // ── Rechercher un patient par nom ──
    static void rechercherPatient(Scanner scanner) {
        System.out.print("\nRechercher (nom) : ");
        String recherche = scanner.nextLine().toLowerCase();
        boolean trouve = false;

        for (int i = 0; i < nbPatients; i++) {
            if (nomsPatients[i].toLowerCase().contains(recherche)) {
                int age = 2026 - anneesNaissance[i];
                System.out.println("→ " + prenomsPatients[i] + " "
                    + nomsPatients[i] + " (" + age + " ans)");
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun résultat pour \"" + recherche + "\"");
        }
    }
}