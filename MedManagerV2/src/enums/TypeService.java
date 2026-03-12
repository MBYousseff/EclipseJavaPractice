package enums;

public enum TypeService {

    CARDIOLOGIE("Cardiologie"),
    SOINS_INTENSIFS("Soins intensifs"),
    URGENCES("Urgences"),
    PEDIATRIE("Pédiatrie"),
    NEUROLOGIE("Neurologie"),
    CHIRURGIE("Chirurgie"),
    RADIOLOGIE("Radiologie"),
    ONCOLOGIE("Oncologie");

    private final String label;

    TypeService(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
