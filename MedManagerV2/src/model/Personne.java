package model;

import interfaces.Identifiable;
import java.time.LocalDate;
import java.time.Period;

public abstract class Personne implements Identifiable {

    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String telephone;

    public Personne(String id, String nom, String prenom, LocalDate dateNaissance) {
        this.id           = id;
        this.nom          = nom;
        this.prenom       = prenom;
        this.dateNaissance = dateNaissance;
    }

    // ----------------------------------------------------------------
    // Méthode abstraite — les enfants DOIVENT fournir leur propre implémentation
    // ----------------------------------------------------------------
    public abstract String getRole();

    // ----------------------------------------------------------------
    // Méthode concrète — code partagé par tous les enfants, ils en héritent tel quel
    // ----------------------------------------------------------------
    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    // ----------------------------------------------------------------
    // Implémentation de l'interface Identifiable
    // ----------------------------------------------------------------
    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getIdentiteComplete() {
        return getRole() + " " + prenom + " " + nom + " (ID: " + id + ")";
    }

    // getIdentiteCourte() héritée de l'interface (default method) — retourne getId()

    // ----------------------------------------------------------------
    // Getters / Setters
    // ----------------------------------------------------------------
    public String getNom()               { return nom; }
    public String getPrenom()            { return prenom; }
    public LocalDate getDateNaissance()  { return dateNaissance; }
    public String getTelephone()         { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    // ----------------------------------------------------------------
    // Implémentation par défaut — les enfants PEUVENT la redéfinir
    // ----------------------------------------------------------------
    @Override
    public String toString() {
        return getRole() + " " + prenom + " " + nom;
    }
}
