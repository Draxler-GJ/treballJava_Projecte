package main;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LlistaPelicules implements Serializable{
    
    //Varibles de instancia
    private String nomPelicula;
    private String director;
    private String actor;

    //Constructor
    public LlistaPelicules(String nomPelicula, String director, String actor) {
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

    public void inserirPelicules(String nomPelicula, String  director, String actor){

        //Inserir pel·lícules a un fitxer binari
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../fitxers/pelicules.dat"))) {
            ArrayList<LlistaPelicules> llistaPelicules = new ArrayList<>();

            Scanner sc = new Scanner(System.in);
            System.out.println("Introdueix el nom de la pel·lícula: ");
            nomPelicula = sc.nextLine();
            System.out.println("Introdueix el nom del director: ");
            director = sc.nextLine();
            System.out.println("Introdueix el nom de l'actor: ");
            actor = sc.nextLine();

            while (sc.hasNext()) {
                if (nomPelicula.equalsIgnoreCase("exit")) {
                    llistaPelicules.add(new LlistaPelicules(nomPelicula, director, actor));
                    oos.writeObject(llistaPelicules);
                    break;
                }
            }

            sc.close();

        } catch (IOException e) {
            System.out.println("Error en la inserció del llistat de pel·licules " + e.getMessage());
        }

        //Legir el llistat de pel·lícules d'un fitxer binari
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../fitxers/pelicules.dat"))) {
            ArrayList<LlistaPelicules> llistaPelicules = (ArrayList<LlistaPelicules>)ois.readObject();
            
            for (LlistaPelicules pelicula : llistaPelicules) {
                System.out.println("Títol: " + pelicula.getNomPelicula() 
                + "\nDirector: " + pelicula.getDirector()
                + "\nActor: " + pelicula.getActor()
                );
            }
        } catch (IOException e) {
            System.out.println("Error en la lectura del llistat de pel·licules " + e.getMessage());
        }

        
    }
}
