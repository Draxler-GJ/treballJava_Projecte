package main;

import java.io.Serializable;

public class Director implements Serializable {

    private String nom;
    private String cognoms;

    public Director(String nom) {
        this.nom = nom;
    }

    public Director(String nom, String cognoms, String nacionalitat) {
        this.nom = nom;
        this.cognoms = cognoms;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }





    @Override
    public String toString() {
        if (cognoms == null || cognoms.isEmpty()) {
            return nom;
        }
        return nom + " " + cognoms;
    }
}
