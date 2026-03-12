package model;

import java.time.LocalDate;

public class Consultation {

    private Patient   patient;
    private Medecin   medecin;
    private LocalDate date;
    private String    motif;

    public Consultation(Patient patient, Medecin medecin, LocalDate date) {
        this.patient = patient;
        this.medecin = medecin;
        this.date    = date;
    }

    public void    setMotif(String motif) { this.motif = motif; }
    public String  getMotif()             { return motif; }
    public Patient getPatient()           { return patient; }
    public Medecin getMedecin()           { return medecin; }
    public LocalDate getDate()            { return date; }

    @Override
    public String toString() {
        String base = "Consultation du " + date + " : "
                + medecin.getRole() + " " + medecin.getPrenom() + " " + medecin.getNom()
                + " → " + patient.getRole() + " " + patient.getPrenom() + " " + patient.getNom();
        if (motif != null && !motif.isEmpty()) {
            base += " | Motif : " + motif;
        }
        return base;
    }
}
