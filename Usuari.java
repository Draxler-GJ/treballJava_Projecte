package treballJava_Projecte;

import java.time.LocalDate;

public class Usuari {
    
    //Variables de instancia
    private String nom;
    private String cognoms;
    private String correu;
    private String password;
    private String poblacio;
    private Rol rolsUsuari;
    private LocalDate data;

    //Constructor
    public Usuari(String nom, String cognoms, String correu, String password, String poblacio, Rol rolsUsuari,
            LocalDate data) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.correu = correu;
        this.password = password;
        this.poblacio = poblacio;
        this.rolsUsuari = rolsUsuari;
        this.data = data;
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

   
    //Métdodos de la clase
    
    // public boolean comprobarContrasenya(String contrasenya){

    // }

    // public void iniciSessio(){

    // }
}
