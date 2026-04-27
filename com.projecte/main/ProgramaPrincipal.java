package main;
import Alejandro.Alejandro;
import Cesar.Cesar;
import Daniel.Daniel;
//import java.util.ArrayList;

public class ProgramaPrincipal {

    public static void main(String[] args) throws ClassNotFoundException {
        /*
        falta fer la opcio de registre o login 
        de momen gastem soles objected i ya farem
        gestio de archius
        */
       //ArrayList<Usuari> usuaris = new ArrayList<>();
       Alejandro alejandro = new Alejandro("Alejandro");
       Cesar cesar = new Cesar("Cesar");
       Daniel daniel = new Daniel("Daniel");

       alejandro.mostrarAlumne();
       cesar.mostrarAlumne();
       daniel.mostrarAlumne();

       Pelicula p1 = new Pelicula("Star Wars Ep VII", "JJ. Abrams", "Mark Hamill");

       p1.inserirPelicules();
    }
}