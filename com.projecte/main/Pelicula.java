package main;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.util.ArrayList;
//import java.util.Scanner;

public class Pelicula implements Serializable{
    
    //Varibles de instancia
    private String nomPelicula;
    private String director;
    private String actor;

    //Constructor
    public Pelicula(String nomPelicula, String director, String actor) {
        this.nomPelicula = nomPelicula;
        this.director = director;
        this.actor = actor;

    }

    //Getters - Setters

    public String getNomPelicula() {
        return nomPelicula;
    }

    public void setNomPelicula(String nomPelicula) {
        this.nomPelicula = nomPelicula;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    //MÉTODES
    public void llistarPelicules(){

        System.out.println("Títol: " + nomPelicula 
        + "\nDirector: " + director
        + "\nActor: " + actor
        );


    }

    public void inserirPelicules() throws ClassNotFoundException{

        //Inserir pel·lícules a un fitxer binari
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../Llistes/pelicules.txt"))) {
            ArrayList<Pelicula> llistaPelicules = new ArrayList<>();


            while (true) {
                if (nomPelicula.equalsIgnoreCase("exit")) {
                    llistaPelicules.add(new Pelicula(nomPelicula, director, actor));
                    oos.writeObject(llistaPelicules);
                    break;
                }
            }


        } catch (IOException e) {
            System.out.println("Error en la inserció del llistat de pel·licules " + e.getMessage());
        }

        //Legir el llistat de pel·lícules d'un fitxer binari
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../fitxers/pelicules.txt"))) {
            ArrayList<Pelicula> llistaPelicules = (ArrayList<Pelicula>)ois.readObject();
            
            for (Pelicula pelicula : llistaPelicules) {
                System.out.println("Títol: " + pelicula.getNomPelicula() 
                + "\nDirector: " + pelicula.getDirector()
                + "\nActor: " + pelicula.getActor()
                );
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la lectura del llistat de pel·licules " + e.getMessage());
        }

        
    }
}
