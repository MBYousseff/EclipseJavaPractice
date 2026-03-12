package model;

import java.time.LocalDate;

// Exercice 1 — Classe Administrateur
// Hérite de Personne (donc implémente déjà Identifiable par héritage)
public class Administrateur extends Personne {

    private String departement; // Comptabilité, RH, Direction...
    private String poste;       // Directeur, Secrétaire...

    public Administrateur(String id, String nom, String prenom,
                          LocalDate dateNaissance, String departement, String poste) {
        super(id, nom, prenom, dateNaissance);
        this.departement = departement;
        this.poste       = poste;
    }

    @Override
    public String getRole() {
        return "Admin.";
    }

    public String getDepartement() { return departement; }
    public String getPoste()       { return poste; }

    @Override
    public String toString() {
        return super.toString() + " — " + poste + " (" + departement + ")";
    }
}
