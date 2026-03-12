package enums;

public enum Specialite {

    CARDIOLOGIE("Cardiologie",       "Spécialité du cœur et des vaisseaux sanguins"),
    NEUROLOGIE("Neurologie",         "Spécialité du système nerveux central et périphérique"),
    PEDIATRIE("Pédiatrie",           "Médecine de l'enfant et de l'adolescent"),
    CHIRURGIE("Chirurgie",           "Interventions chirurgicales générales"),
    RADIOLOGIE("Radiologie",         "Imagerie médicale et diagnostique"),
    ONCOLOGIE("Oncologie",           "Traitement et suivi des cancers"),
    URGENCES("Urgences",             "Médecine d'urgence et réanimation"),
    GYNECOLOGIE("Gynécologie",       "Santé de la femme et obstétrique"),
    DERMATOLOGIE("Dermatologie",     "Maladies de la peau, des cheveux et des ongles");

    private final String label;
    private final String description;

    Specialite(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return label;
    }
}
