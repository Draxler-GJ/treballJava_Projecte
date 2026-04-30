package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

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

    private static File usuaris = new File("./Usuaris");
    private static File llistes = new File("./Llistes");
    private static int idLliure = llistes.listFiles().length -1;

    //Constructor
    public Usuari(String nom, String cognoms, String correu, String password, String poblacio, LocalDate data) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.correu = correu;
        this.password = password;
        this.poblacio = poblacio;
        this.rolsUsuari = Rol.ROL_USUARI;//sols poden haber usuaris, el admin el creem amb antelacio
        this.data = data;
        this.id = idLliure;
        File usuari = new File(usuaris + "/" + this.nom + ".dat");
        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream(usuari))) {
            ou.writeObject(this);
            File carpetaPersonal = new File(llistes + "/" + this.id + this.correu.split("@")[0]);
            carpetaPersonal.mkdir();
        } catch (IOException e) {
            System.out.println("Error creant el usuari: " + e.getMessage());
        }
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
    
    // public boolean comprobarContrasenya(String contrasenya){

    // }

    public boolean comprobarSessioUsuari(String correu, String contraseyna){
        boolean esActiu = false;

        if(this.correu.equals(correu) && this.password.equals(contraseyna)){
            esActiu = true;
        }

        return esActiu;
    }

}
