package model;

import enums.Specialite;
import interfaces.Consultable;
import java.time.LocalDate;

public class Medecin extends Personne implements Consultable {

    // Exercice 2 — specialite est maintenant un enum Specialite (plus un String fragile)
    private Specialite specialite;
    private String     matricule;

    public Medecin(String id, String nom, String prenom,
                   LocalDate dateNaissance, Specialite specialite, String matricule) {
        super(id, nom, prenom, dateNaissance);
        this.specialite = specialite;
        this.matricule  = matricule;
    }

    @Override
    public String getRole() {
        return "Dr.";
    }

    // ----------------------------------------------------------------
    // Surcharge de planifierConsultation — même nom, signatures différentes
    // (overloading — résolu à la COMPILATION)
    // ----------------------------------------------------------------

    // Version 1 : consultation immédiate (date = aujourd'hui)
    public Consultation planifierConsultation(Patient patient) {
        return new Consultation(patient, this, LocalDate.now());
    }

    // Version 2 : consultation à une date précise
    public Consultation planifierConsultation(Patient patient, LocalDate date) {
        return new Consultation(patient, this, date);
    }

    // Version 3 : consultation avec motif et date
    public Consultation planifierConsultation(Patient patient, LocalDate date, String motif) {
        Consultation c = new Consultation(patient, this, date);
        c.setMotif(motif);
        return c;
    }

    // ----------------------------------------------------------------
    // Implémentation de Consultable — un médecin peut TOUJOURS consulter
    // ----------------------------------------------------------------
    @Override
    public boolean peutConsulter(Patient patient) {
        return true;
    }

    // ----------------------------------------------------------------
    // Getters
    // ----------------------------------------------------------------
    public Specialite getSpecialite() { return specialite; }
    public String     getMatricule()  { return matricule; }

    // ----------------------------------------------------------------
    // Redéfinition (overriding) — même signature, comportement spécialisé
    // ----------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + " — " + specialite.getLabel();
    }
}
