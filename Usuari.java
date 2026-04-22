import java.sql.Date;
public class Usuari {

    private String nom;
    private String cognoms;
    private String correu;
    private String contraseñya;
    private String poblacio;
    private Date naixement;
    private int id;
    //falta el rol, no recorde com es fea un enum


    //el contador deuria de tindre en conter usuaris ja creats cuant fem lo dels arxius
    private static int contadorId = 0;

    public Usuari(String nom, String cognoms, String correu, String contraseñya, String poblacio, Date naixement) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.correu = correu;
        this.contraseñya = contraseñya;
        this.poblacio = poblacio;
        this.naixement = naixement;

        id = contadorId;
        contadorId++;
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

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getContraseñya() {
        return contraseñya;
    }

    public void setContraseñya(String contraseñya) {
        this.contraseñya = contraseñya;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public Date getNaixement() {
        return naixement;
    }

    public void setNaixement(Date naixement) {
        this.naixement = naixement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}