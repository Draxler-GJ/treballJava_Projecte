package main;

import Alejandro.Alejandro;
import Cesar.Cesar;
import Daniel.Daniel;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static main.GestioFitxers.*;//per a no tindre que escriure GestioFitxers.metode al cridar a un metode de la classe

public class ProgramaPrincipal {
    static Scanner sc = new Scanner(System.in);
    static Usuari usuariActiu = null;

    public static void main(String[] args) throws ClassNotFoundException {

        /*
         * Aquestes línies de codi seran empreades mes avant per a guardat el llistat de
         * pelicules per cada usuari
         */
        // Alejandro alejandro = new Alejanalejandro.mostrarAlumne();
        // cesar.mostrarAlumne();
        // daniel.mostrarAlumne();dro("Alejandro");
        // Cesar cesar = new Cesar("Cesar");
        // Daniel daniel = new Daniel("Daniel");

        //

        Pelicula p1 = new Pelicula("Star Wars Ep VII", "JJ. Abrams", "Mark Hamill");

        p1.inserirPelicules();
        boolean eixir = false;
        while (!eixir) {// temporal, heu organizarem cuant funcione tot be
            System.out.println("1.registre");
            System.out.println("2.inici de sesio");
            System.out.println("3.Eixir");
            System.out.println("4.Crear llista de pel·lícules");
            int opcio = sc.nextInt();
            sc.nextLine();// buffer
            switch (opcio) {
                case 1: // TODO: deuria de comprobar si el nom ya existeix en usuaris antes de dixarte
                        // crearlo, tambe deuria de demanar la contraseña 2 vegades

                    break;
                case 2:
                    iniciSessio();
                    break;
                case 3:
                    eixir = true;
                    break;

                case 4:
                    boolean sessioInicada = usuariActiu.comprobarSessioUsuari(usuariActiu.getCorreu(), usuariActiu.getPassword());

                    if(sessioInicada == false){
                        System.out.println("Deus inciar sessio per crear un llistat del pelicules");
                    }else if(sessioInicada == true){
                        System.out.println("Benvingut, " + usuariActiu.getNom());
                        System.out.println("""
                                Introdueix el teu llistat de pel·licules:
                                """);
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Nom de la pel·lícula:");
                        String nomPeli = sc.nextLine();
                        System.out.println("Director:");
                        String director = sc.nextLine();
                        System.out.println("Actor:");
                        String actor = sc.nextLine();
                        Pelicula llistaPersonal = new Pelicula(nomPeli, director, actor);

                        try {
                            //problema a la hora de cridar el metodo inserirPelicules, no se si es por el metodo o por la forma en la que lo estoy llamando, pero el caso es que no me inserta nada en el fitxer de pelicules
                            llistaPersonal.inserirPelicules();
                        } catch (ClassNotFoundException e) {
                            System.out.println("Error al inserir un llista de peĺ·licules: " + e.getMessage());
                        }
                    }
                    break;
                default:
                    System.out.println("Fin del programa");
                    break;
            }

        }
    }

    static void iniciSessio() {
        System.out.println("Nom de usuari");
        String nomUs = sc.nextLine();

        if (buscarFitxer(nomUs) != null) {
            usuariActiu = buscarUsuari(nomUs);
        }

        if (usuariActiu == null) {
            System.out.println("Usuari no trobat");
            return;
        }
        System.out.println("Contrasenya");
        boolean fiInici = false;
        int intents = 0;
        do {
            String pwdUs = sc.nextLine();
            if (pwdUs.equals(usuariActiu.getPassword())) {
                System.out.println("Benvingut, " + usuariActiu.getNom());
                fiInici = true;
            } else if (intents >= 3) {// si falla massa intents torna al principi i elimina el usuari introduit de
                                      // actiu
                System.out.println("Massa intents fallits");
                usuariActiu = null;
                fiInici = true;
            } else {
                System.out.println("Contrasenya incorrecta, torna a intentar");
                intents++;
            }
        } while (!fiInici);
    }

    static void registrarUsuari() {
        System.out.println("Introdueix el nom");
        String nom = sc.nextLine();

        System.out.println("Introdueix els cognoms");

        String cogn = sc.nextLine();
        String correu;
        while (true) {
            System.out.println("Introdueix el correu");
            correu = sc.nextLine();
            //comprova si el correu te @ i punt i si el @ va abans que el .
            if (correu.contains("@") && correu.contains(".")&& correu.indexOf("@") < correu.lastIndexOf(".")) {
                break;
            }
            System.out.println("Correu no valid");
        }

        

        String pwd;
        while (true) {
            System.out.println("Introdueix la contrasenya");
            pwd = sc.nextLine();
            System.out.println("Torna a introduir la contrasenya");
            String pwd2 = sc.nextLine();
            if (pwd.equals(pwd2)) {
                break;
            }
            System.out.println("No coincideixen les contrasenyes, torna a intentar");
        }
        System.out.println("Introdueix la poblacio");
        String pob = sc.nextLine();
        System.out.println("Introdueix la data de naixement (dd/MM/yyyy)");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(sc.nextLine(), format);
        usuariActiu = new Usuari(nom, cogn, correu, pwd, pob, data);
        System.out.println("usuari creat, inicia sesio");
    }

}