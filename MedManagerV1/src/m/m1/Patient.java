package m.m1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Patient {

    // ── Attributs (les données du patient) ──
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String telephone;
    private String groupeSanguin;

    // ── Constructeur (comment créer un patient) ──
    public Patient(String id, String nom, String prenom,
                   LocalDate dateNaissance, String telephone,
                   String groupeSanguin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.groupeSanguin = groupeSanguin;
    }

    public Patient(String id, String nom, String prenom,
                   LocalDate dateNaissance) {
        this(id, nom, prenom, dateNaissance, null, null);
    }

    // ── Méthodes (les comportements du patient) ──
    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now())
                     .getYears();
    }

    public String getIdentiteComplete() {
        return prenom + " " + nom + " (ID: " + id + ")";
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public String getTelephone() { return telephone; }
    public String getGroupeSanguin() { return groupeSanguin; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setGroupeSanguin(String groupeSanguin) { this.groupeSanguin = groupeSanguin; }

    @Override
    public String toString() {
        return "Patient{"
            + "id='" + id + "'"
            + ", nom='" + nom + "'"
            + ", prenom='" + prenom + "'"
            + ", age=" + getAge()
            + "}";
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Même référence mémoire ? → Forcément égal
        if (this == obj) return true;

        // 2. Null ou type différent ? → Pas égal
        if (obj == null || getClass() != obj.getClass()) return false;

        // 3. Comparer le contenu — ici, l'ID est l'identité
        Patient autre = (Patient) obj;
        return id.equals(autre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}