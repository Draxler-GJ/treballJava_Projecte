package main;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Usuari implements Serializable{
    
    //Variables de instancia
    private String nom;
    private String cognoms;
    private String correu;
    private String password;
    private String poblacio;
    private Rol rolsUsuari;
    private LocalDate data;
    private int id;
    private ArrayList<Pelicula> pelicules;
    private ArrayList<String> actors;
    private ArrayList<String> directors;

    
    private static File llistes = obtenirDirectoriLlistes();
    private static int idLliure = llistes.exists() && llistes.isDirectory() ? llistes.listFiles().length -1 : 0;

    private static File obtenirDirectoriLlistes() {
        File fitxerLocal = new File("Llistes");
        if (fitxerLocal.exists() && fitxerLocal.isDirectory()) {
            return fitxerLocal;
        }
        File fitxerAlternatiu = new File("com.projecte", "Llistes");
        return fitxerAlternatiu;
    }

    //Constructor
    public Usuari(String nom, String cognoms, String correu, String password, String poblacio, LocalDate data) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.correu = correu;
        this.password = password;
        this.poblacio = poblacio;
        this.rolsUsuari = Rol.ROL_ADMIN;//sols poden haber usuaris, el admin el creem amb antelacio
        this.data = data;
        this.id = idLliure;
        this.pelicules = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.directors = new ArrayList<>();
        idLliure++;
    }

    //Getters - Setters

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

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public Rol getRolsUsuari() {
        return rolsUsuari;
    }

    public void setRolsUsuari(Rol rolsUsuari) {
        this.rolsUsuari = rolsUsuari;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

   
    //Métdodos de la clase

    public ArrayList<Pelicula> getPelicules() {
        return pelicules;
    }

    public void setPelicules(ArrayList<Pelicula> personalList) {
        this.pelicules = personalList;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> personalList) {
        this.actors = personalList;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public boolean comprobarSessioUsuari(String correu, String contraseyna){
        boolean esActiu = false;

        if(this.correu.equals(correu) && this.password.equals(contraseyna)){
            esActiu = true;
        }

        return esActiu;
    }

    //<T> es un tipus que se sobreescriu per un tipus mes especific al retornar el metode
    @SuppressWarnings("unchecked")//si no dona 3 avisos de unchecked cast  que no fan res pero molesten
    public <T> ArrayList<T> getLlista(String tipusLlista){

        switch (tipusLlista) {
            case "pelicules":

                ArrayList<Pelicula> pelicules = getPelicules();

                Collections.sort(pelicules, new Comparator<Pelicula>(){
                    @Override
                    public int compare(Pelicula p1, Pelicula p2) {
                        return p1.getNomPelicula().compareTo(p2.getNomPelicula());
                    }
                });
                
                return (ArrayList<T>) getPelicules();
            case "actors":
                return (ArrayList<T>) getActors();
            case "directors":
                return (ArrayList<T>) getDirectors();
            default:
                return new ArrayList<>();
        }
    }
}
