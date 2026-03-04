package m.m1;

import java.time.LocalDateTime;

public class RendezVous {
 private Patient patient;
 private Medecin medecin;
 private LocalDateTime dateHeure;

 public RendezVous(Patient patient, Medecin medecin, LocalDateTime dateHeure) {
     this.patient = patient;
     this.medecin = medecin;
     this.dateHeure = dateHeure;
 }

 @Override
 public String toString() {
     return "Rendez-vous : " + patient.getIdentiteComplete() + " avec " + medecin.toString() + " le " + dateHeure;
 }
}

