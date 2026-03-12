package interfaces;

public interface Identifiable {

    String getId();
    String getIdentiteComplete();

    // Méthode par défaut — les implémenteurs n'ont pas besoin de la redéfinir
    default String getIdentiteCourte() {
        return getId(); // implémentation par défaut, surchargeable
    }
}
