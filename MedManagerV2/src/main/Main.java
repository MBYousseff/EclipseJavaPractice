package main;

import enums.GroupeSanguin;
import enums.Specialite;
import model.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // ----------------------------------------------------------------
    // Une seule liste pour TOUT le personnel grâce au polymorphisme
    // ----------------------------------------------------------------
    static List<Personne> personnel = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // ----------------------------------------------------------------
    // Point d'entrée
    // ----------------------------------------------------------------
    public static void main(String[] args) {

        // Données de démonstration (correspondant à la sortie attendue du PDF page 27)
        initialiserDonnees();

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = lireEntier();
            switch (choix) {
                case 1:  ajouterMedecin();              break;
                case 2:  ajouterPatient();              break;
                case 3:  ajouterInfirmier();            break;
                case 4:  ajouterAdministrateur();       break;
                case 5:  afficherToutLePersonnel();     break;
                case 6:  afficherStatistiquesPersonnel(); break;
                case 7:  rechercherPersonne();          break;
                case 8:  demontrerPolymorphisme();      break;
                case 9:  demontrerConsultable();        break;
                case 0:
                    System.out.println("\nAu revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
        sc.close();
    }

    // ----------------------------------------------------------------
    // Données de démo — exactement comme dans le PDF (pages 9 et 27)
    // ----------------------------------------------------------------
    static void initialiserDonnees() {
        // Médecin
        Medecin dr = new Medecin("M001", "Benjelloun", "Sara",
                LocalDate.of(1980, 3, 15), Specialite.CARDIOLOGIE, "MAT-442");

        // Patient avec groupe sanguin
        Patient p1 = new Patient("P001", "Amrani", "Youssef",
                LocalDate.of(1995, 7, 22));
        p1.setGroupeSanguin(GroupeSanguin.A_POSITIF);
        p1.setService("Soins intensifs");

        // Infirmier
        Infirmier inf = new Infirmier("I001", "Chakir", "Fatima",
                LocalDate.of(1992, 1, 10), "DIP-789", "Soins intensifs");

        personnel.add(dr);
        personnel.add(p1);
        personnel.add(inf);
    }

    // ----------------------------------------------------------------
    // Menu principal
    // ----------------------------------------------------------------
    static void afficherMenu() {
       
        System.out.println("        MedManager  v1.1           ");
        System.out.println("-----------------------------------");
        System.out.println("  1. Ajouter un médecin            ");
        System.out.println("  2. Ajouter un patient            ");
        System.out.println("  3. Ajouter un infirmier          ");
        System.out.println("  4. Ajouter un administrateur     ");
        System.out.println("  5. Afficher tout le personnel    ");
        System.out.println("  6. Statistiques du personnel     ");
        System.out.println("  7. Rechercher une personne       ");
        System.out.println("  8. Démo polymorphisme            ");
        System.out.println("  9. Démo Consultable              ");
        System.out.println("  0. Quitter                       ");
        System.out.println("-----------------------------------");
        System.out.print("Votre choix : ");
    }

    // ----------------------------------------------------------------
    // Affichage unifié — exactement comme le PDF page 26
    // ----------------------------------------------------------------
    static void afficherToutLePersonnel() {
        if (personnel.isEmpty()) {
            System.out.println("\nAucun personnel enregistré.");
            return;
        }

        System.out.println("\n===== Personnel de l'hôpital =====");
        System.out.printf("%-8s %-8s %-15s %-15s %s%n",
                "ID", "Rôle", "Nom", "Prénom", "Âge");
        System.out.println("-".repeat(55));

        for (Personne p : personnel) {
            System.out.printf("%-8s %-8s %-15s %-15s %d ans%n",
                    p.getId(), p.getRole(), p.getNom(),
                    p.getPrenom(), p.getAge());

            // Détails spécifiques selon le type (instanceof + cast explicite Java 8+)
            if (p instanceof Medecin) {
                Medecin m = (Medecin) p;
                System.out.println("          \u2514\u2500 Sp\u00e9: " + m.getSpecialite()
                        + " | Mat: " + m.getMatricule());
            } else if (p instanceof Infirmier) {
                Infirmier inf = (Infirmier) p;
                System.out.println("          \u2514\u2500 Sp\u00e9: " + inf.getSpecialite()
                        + " | Dip: " + inf.getNumeroDiplome());
            } else if (p instanceof Patient) {
                Patient pat = (Patient) p;
                String gs = pat.getGroupeSanguin() != null
                        ? pat.getGroupeSanguin().getLabel() : "\u2014";
                System.out.println("          \u2514\u2500 Sang: " + gs);
            } else if (p instanceof Administrateur) {
                Administrateur adm = (Administrateur) p;
                System.out.println("          \u2514\u2500 Dept: " + adm.getDepartement()
                        + " | Poste: " + adm.getPoste());
            }
        }
        System.out.println("Total : " + personnel.size() + " personne(s)");
    }

    // ----------------------------------------------------------------
    // Exercice 3 — Statistiques par rôle
    // ----------------------------------------------------------------
    static void afficherStatistiquesPersonnel() {
        long nbMedecins = 0, nbPatients = 0, nbInfirmiers = 0, nbAdmins = 0;

        for (Personne p : personnel) {
            if      (p instanceof Medecin)         nbMedecins++;
            else if (p instanceof Patient)         nbPatients++;
            else if (p instanceof Infirmier)       nbInfirmiers++;
            else if (p instanceof Administrateur)  nbAdmins++;
        }

        System.out.println("\n===== Statistiques du personnel =====");
        System.out.println("Médecins        : " + nbMedecins);
        System.out.println("Patients        : " + nbPatients);
        System.out.println("Infirmiers      : " + nbInfirmiers);
        System.out.println("Administrateurs : " + nbAdmins);
        System.out.println("-------------------------------------");
        System.out.println("Total           : " + personnel.size());

        // Réflexion : on pourrait aussi utiliser getRole() sans instanceof
        System.out.println("\n[INFO] Approche polymorphe alternative (sans instanceof) :");
        System.out.println("       getRole() retourne déjà un String différent par type.");
        System.out.println("       La méthode afficherToutLePersonnel() ne modifie jamais ce code");
        System.out.println("       quand on ajoute un nouveau type → Open/Closed Principle.");
    }

    // ----------------------------------------------------------------
    // Exercice 5 — Recherche polymorphe (sans instanceof)
    // Le polymorphisme fait que ça marche pour tous les types
    // ----------------------------------------------------------------
    static void rechercherPersonne() {
        System.out.print("\nTerme de recherche (nom, prénom ou ID) : ");
        String terme = sc.nextLine().trim().toLowerCase();

        List<Personne> resultats = new ArrayList<>();
        for (Personne p : personnel) {
            if (p.getNom().toLowerCase().contains(terme)
                    || p.getPrenom().toLowerCase().contains(terme)
                    || p.getId().toLowerCase().contains(terme)) {
                resultats.add(p);
            }
        }

        if (resultats.isEmpty()) {
            System.out.println("Aucun résultat pour : \"" + terme + "\"");
        } else {
            System.out.println("\n--- Résultats : " + resultats.size() + " trouvé(s) ---");
            for (Personne p : resultats) {
                System.out.println("  " + p.getIdentiteComplete());
            }
        }
    }

    // ----------------------------------------------------------------
    // Démo polymorphisme — comme le PDF page 9
    // ----------------------------------------------------------------
    static void demontrerPolymorphisme() {
        System.out.println("\n===== Démo polymorphisme (PDF page 9) =====");
        System.out.println("// UNE seule boucle — Java appelle automatiquement");
        System.out.println("// la BONNE version de toString() et getRole()");
        System.out.println();

        for (Personne p : personnel) {
            System.out.println(p); // → toString() polymorphe
        }

        System.out.println("\nObjet réel vs type déclaré :");
        Personne p = new Patient("P999", "Test", "Demo", LocalDate.of(2000, 1, 1));
        // type déclaré = Personne, type réel = Patient
        System.out.println("Type déclaré : Personne | Rôle réel : " + p.getRole());
    }

    // ----------------------------------------------------------------
    // Démo Consultable (Exercice 4)
    // ----------------------------------------------------------------
    static void demontrerConsultable() {
        System.out.println("\n===== Démo interface Consultable (Exercice 4) =====");

        Patient patSoins = new Patient("P100", "Dupont", "Marie", LocalDate.of(1985, 6, 1));
        patSoins.setService("Soins intensifs");

        Patient patCardio = new Patient("P101", "Martin", "Jean", LocalDate.of(1990, 3, 15));
        patCardio.setService("Cardiologie");

        Medecin dr = new Medecin("M100", "Alami", "Hassan",
                LocalDate.of(1975, 9, 20), Specialite.NEUROLOGIE, "MAT-555");

        Infirmier inf = new Infirmier("I100", "Benali", "Nadia",
                LocalDate.of(1988, 4, 10), "DIP-100", "Soins intensifs");

        System.out.println("Dr. " + dr.getNom() + " peut consulter " + patSoins.getNom()
                + " ? → " + dr.peutConsulter(patSoins));   // true (toujours)
        System.out.println("Dr. " + dr.getNom() + " peut consulter " + patCardio.getNom()
                + " ? → " + dr.peutConsulter(patCardio));  // true (toujours)

        System.out.println("Inf. " + inf.getNom() + " (Soins intensifs) peut consulter "
                + patSoins.getNom() + " (Soins intensifs) ? → "
                + inf.peutConsulter(patSoins));   // true — même service

        System.out.println("Inf. " + inf.getNom() + " (Soins intensifs) peut consulter "
                + patCardio.getNom() + " (Cardiologie) ? → "
                + inf.peutConsulter(patCardio));  // false — services différents

        // Démo surcharge administrerMedicament
        System.out.println();
        inf.administrerMedicament("Paracétamol");           // dose défaut 500 mg
        inf.administrerMedicament("Ibuprofène", 400.0);     // dose précise
    }

    // ----------------------------------------------------------------
    // Ajout d'un médecin
    // ----------------------------------------------------------------
    static void ajouterMedecin() {
        System.out.println("\n--- Ajouter un médecin ---");
        try {
            System.out.print("ID       : "); String id     = sc.nextLine().trim();
            System.out.print("Nom      : "); String nom    = sc.nextLine().trim();
            System.out.print("Prénom   : "); String prenom = sc.nextLine().trim();
            System.out.print("Date naissance (AAAA-MM-JJ) : ");
            LocalDate dn = LocalDate.parse(sc.nextLine().trim());

            System.out.println("Spécialités disponibles :");
            Specialite[] specs = Specialite.values();
            for (int i = 0; i < specs.length; i++) {
                System.out.printf("  %d. %-15s — %s%n",
                        i + 1, specs[i].getLabel(), specs[i].getDescription());
            }
            System.out.print("Choix spécialité (numéro) : ");
            int idx = lireEntier() - 1;
            if (idx < 0 || idx >= specs.length) {
                System.out.println("Numéro invalide. Opération annulée.");
                return;
            }
            Specialite spec = specs[idx];

            System.out.print("Matricule : "); String mat = sc.nextLine().trim();

            Medecin m = new Medecin(id, nom, prenom, dn, spec, mat);
            personnel.add(m);
            System.out.println("✓ Médecin ajouté : " + m);

        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Utilisez AAAA-MM-JJ.");
        }
    }

    // ----------------------------------------------------------------
    // Ajout d'un patient
    // ----------------------------------------------------------------
    static void ajouterPatient() {
        System.out.println("\n--- Ajouter un patient ---");
        try {
            System.out.print("ID       : "); String id     = sc.nextLine().trim();
            System.out.print("Nom      : "); String nom    = sc.nextLine().trim();
            System.out.print("Prénom   : "); String prenom = sc.nextLine().trim();
            System.out.print("Date naissance (AAAA-MM-JJ) : ");
            LocalDate dn = LocalDate.parse(sc.nextLine().trim());

            Patient p = new Patient(id, nom, prenom, dn);

            System.out.print("Groupe sanguin (A+, A-, B+, B-, AB+, AB-, O+, O-) — Entrée pour ignorer : ");
            String gsStr = sc.nextLine().trim();
            if (!gsStr.isEmpty()) {
                try {
                    p.setGroupeSanguin(GroupeSanguin.fromLabel(gsStr));
                } catch (IllegalArgumentException e) {
                    System.out.println("Groupe sanguin invalide, ignoré.");
                }
            }

            System.out.print("Service hospitalier (laisser vide si non hospitalisé) : ");
            String svc = sc.nextLine().trim();
            if (!svc.isEmpty()) p.setService(svc);

            personnel.add(p);
            System.out.println("✓ Patient ajouté : " + p);

        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Utilisez AAAA-MM-JJ.");
        }
    }

    // ----------------------------------------------------------------
    // Ajout d'un infirmier
    // ----------------------------------------------------------------
    static void ajouterInfirmier() {
        System.out.println("\n--- Ajouter un infirmier/infirmière ---");
        try {
            System.out.print("ID              : "); String id      = sc.nextLine().trim();
            System.out.print("Nom             : "); String nom     = sc.nextLine().trim();
            System.out.print("Prénom          : "); String prenom  = sc.nextLine().trim();
            System.out.print("Date naissance (AAAA-MM-JJ) : ");
            LocalDate dn = LocalDate.parse(sc.nextLine().trim());
            System.out.print("Numéro diplôme  : "); String diplome = sc.nextLine().trim();
            System.out.print("Service         : "); String service = sc.nextLine().trim();

            Infirmier inf = new Infirmier(id, nom, prenom, dn, diplome, service);
            personnel.add(inf);
            System.out.println("✓ Infirmier ajouté : " + inf);

        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Utilisez AAAA-MM-JJ.");
        }
    }

    // ----------------------------------------------------------------
    // Ajout d'un administrateur (Exercice 1)
    // ----------------------------------------------------------------
    static void ajouterAdministrateur() {
        System.out.println("\n--- Ajouter un administrateur ---");
        try {
            System.out.print("ID           : "); String id      = sc.nextLine().trim();
            System.out.print("Nom          : "); String nom     = sc.nextLine().trim();
            System.out.print("Prénom       : "); String prenom  = sc.nextLine().trim();
            System.out.print("Date naissance (AAAA-MM-JJ) : ");
            LocalDate dn = LocalDate.parse(sc.nextLine().trim());
            System.out.print("Département  : "); String dept   = sc.nextLine().trim();
            System.out.print("Poste        : "); String poste  = sc.nextLine().trim();

            Administrateur adm = new Administrateur(id, nom, prenom, dn, dept, poste);
            personnel.add(adm);
            System.out.println("✓ Administrateur ajouté : " + adm);

        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Utilisez AAAA-MM-JJ.");
        }
    }

    // ----------------------------------------------------------------
    // Utilitaire — lire un entier sans planter si l'utilisateur tape autre chose
    // ----------------------------------------------------------------
    static int lireEntier() {
        try {
            String ligne = sc.nextLine().trim();
            return Integer.parseInt(ligne);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
