package main;

import java.io.Serializable;

public class Actor implements Serializable, Gestionable, Comparable<Actor> {

    private String nom;
    private String cognoms;

    public Actor(String nom) {
        this.nom = nom;
    }

    public Actor(String nom, String cognoms, String nacionalitat) {
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

    //Metodes implementats de la interface Gestionable 
    @Override
    public String getIdentificador() {
        return nom;
    }

    @Override
    public String resumen() {
        return "Fitxa completa: " + nom + " " + cognoms;
    }

    @Override
    public void mostrarDetalls() {
        System.out.println("===============FITXA TÉCNICA==================");
        System.out.println("Nom de l'actor/actriu: " + nom);
        System.out.println("==============================================");
        System.out.println("Director del curtmetratje: " + cognoms);
        System.out.println("==============================================");
    }

    //Metodes importats de COmparable<>

    @Override
    public int compareTo(Actor o) {
        return this.nom.compareToIgnoreCase(o.nom);
    }
}
