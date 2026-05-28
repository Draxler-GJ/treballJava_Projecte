package main;

import java.io.Serializable;

//import java.util.Scanner;

public class Pelicula implements Serializable, Gestionable, Comparable<Pelicula>{
    
    //Varibles de instancia
    private String nomPelicula;
    private Director director;
    private Actor actor;

    //Constructor
    public Pelicula(String nomPelicula, Director director, Actor actor) {
        this.nomPelicula = nomPelicula;
        this.director = director;
        this.actor = actor;
    }

    public Pelicula(String nomPelicula, String director, String actorNom) {
        this(nomPelicula, new Director(director), new Actor(actorNom));

    }

    //Getters - Setters

    public String getNomPelicula() {
        return nomPelicula;
    }

    public void setNomPelicula(String nomPelicula) {
        this.nomPelicula = nomPelicula;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    //MÉTODES
    public void llistarPelicules(){

        System.out.println("Títol: " + nomPelicula 
            + "\nDirector: " + director
            + "\nActor: " + actor
        );


    }

    //Métodes implementats de Gestionable
    @Override
    public String getIdentificador() {
        return nomPelicula;
    }
        

    @Override
    public String resumen() {
        return  "La pel·lícula: " + nomPelicula;
    }

    @Override
    public void mostrarDetalls() {
        System.out.println("===============FITXA TÉCNICA==================");
        System.out.println("Títol de la pel·lícula: " + nomPelicula);
        System.out.println("==============================================");
        System.out.println("Director del curtmetratje: " + director);
        System.out.println("==============================================");
        System.out.println("Actor: " + actor);
        System.out.println("==============================================");
    }


    //Metodes importats de COmparable<>

    @Override
    public int compareTo(Pelicula o) {
         return this.nomPelicula.compareToIgnoreCase(o.nomPelicula);
    }
}
