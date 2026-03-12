package model;

import interfaces.Consultable;
import java.time.LocalDate;

public class Infirmier extends Personne implements Consultable {

    private String numeroDiplome;
    private String service;

    public Infirmier(String id, String nom, String prenom,
                     LocalDate dateNaissance, String numeroDiplome, String service) {
        super(id, nom, prenom, dateNaissance);
        this.numeroDiplome = numeroDiplome;
        this.service       = service;
    }

    @Override
    public String getRole() {
        return "Inf.";
    }

    // ----------------------------------------------------------------
    // Surcharge administrerMedicament (Exercice — overloading)
    // ----------------------------------------------------------------

    // Version 1 : dose par défaut de 500.0 mg
    public void administrerMedicament(String nom) {
        administrerMedicament(nom, 500.0);
    }

    // Version 2 : dose précise en mg
    public void administrerMedicament(String nom, double doseMg) {
        System.out.println("[MÉDICAMENT] " + nom + " (" + doseMg + " mg)" +
                           " administré par Inf. " + getPrenom() + " " + getNom());
    }

    // ----------------------------------------------------------------
    // Implémentation de Consultable (Exercice 4)
    // Vrai seulement si le patient est dans le même service que l'infirmier
    // ----------------------------------------------------------------
    @Override
    public boolean peutConsulter(Patient patient) {
        if (patient.getService() == null) return false;
        return service.equalsIgnoreCase(patient.getService());
    }

    // ----------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------
    public String getNumeroDiplome() { return numeroDiplome; }
    public String getSpecialite()    { return service; }   // alias pour l'affichage
    public String getService()       { return service; }

    @Override
    public String toString() {
        return super.toString() + " — " + service;
    }
}
