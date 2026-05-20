package main;



import java.io.Serializable;

//import java.util.Scanner;

public class Pelicula implements Serializable{
    
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
}
