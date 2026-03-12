package model;

import enums.GroupeSanguin;
import java.time.LocalDate;

public class Patient extends Personne {

    private GroupeSanguin groupeSanguin;
    private String service; // Service où est hospitalisé le patient (pour Consultable)

    public Patient(String id, String nom, String prenom, LocalDate dateNaissance) {
        super(id, nom, prenom, dateNaissance);
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    // ----------------------------------------------------------------
    // Getters / Setters
    // ----------------------------------------------------------------
    public GroupeSanguin getGroupeSanguin()                  { return groupeSanguin; }
    public void setGroupeSanguin(GroupeSanguin groupeSanguin) { this.groupeSanguin = groupeSanguin; }

    public String getService()              { return service; }
    public void setService(String service)  { this.service = service; }

    // toString() hérité de Personne → "Patient Prénom Nom"
    @Override
    public String toString() {
        return super.toString();
    }
}
