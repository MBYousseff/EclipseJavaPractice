package model;

import enums.TypeService;
import java.util.ArrayList;
import java.util.List;

public class ServiceHospitalier {

    private String       nom;
    private TypeService  type;
    private List<Personne> personnel;

    public ServiceHospitalier(String nom, TypeService type) {
        this.nom       = nom;
        this.type      = type;
        this.personnel = new ArrayList<>();
    }

    public void ajouterPersonne(Personne p) {
        if (!personnel.contains(p)) {
            personnel.add(p);
        }
    }

    public void retirerPersonne(Personne p) {
        personnel.remove(p);
    }

    public String           getNom()            { return nom; }
    public TypeService      getType()           { return type; }
    public List<Personne>   getPersonnel()      { return personnel; }
    public int              getNombrePersonnel(){ return personnel.size(); }

    @Override
    public String toString() {
        return "Service " + nom + " [" + type.getLabel() + "] — "
                + personnel.size() + " personne(s)";
    }
}
