package Daniel;

public class Daniel {
    
    
    private String nom;

    public Daniel(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }  

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void mostrarAlumne(){
        System.out.println("Hola soc " + nom + " i estic fent el treball de Java");
    }
}
