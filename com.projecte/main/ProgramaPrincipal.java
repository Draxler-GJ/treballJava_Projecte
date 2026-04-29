package main;

import Alejandro.Alejandro;
import Cesar.Cesar;
import Daniel.Daniel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProgramaPrincipal {
    static Scanner sc = new Scanner(System.in);
    static Usuari usuariActiu = null;
    private static File dirUsuaris = new File("./Usuaris");
    private static File llistes = new File("./Llistes");

    public static void main(String[] args) throws ClassNotFoundException {

        /*
            Aquestes línies de codi seran empreades mes avant per a guardat el llistat de pelicules per cada usuari
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
            int opcio = sc.nextInt();
            sc.nextLine();// buffer
            switch (opcio) {
                case 1: // TODO: deuria de comprobar si el nom ya existeix en usuaris antes de dixarte crearlo
                    System.out.println("nom");
                    String nom = sc.nextLine();

                    System.out.println("cognoms");

                    String cogn = sc.nextLine();
                    System.out.println("correu");

                    String correu = sc.nextLine();
                    System.out.println("Contrasenya");

                    String pwd = sc.nextLine();
                    System.out.println("Poblacio");

                    String pob = sc.nextLine();
                    System.out.println("Data (dd/MM/yyyy)");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(sc.nextLine(), format);
                    usuariActiu = new Usuari(nom, cogn, correu, pwd, pob, data);
                    System.out.println("usuari creat, inicia sesio");
                    break;
                case 2:
                    iniciSessio();
                    break;
                case 3:
                    eixir = true;
                    break;
                default:
                    break;
            }

        }
    }

    static void iniciSessio() {
        System.out.println("Nom de usuari");
        String nomUs = sc.nextLine();

        File[] llista = dirUsuaris.listFiles();
        for (File file : llista) { //llig tots els objectes de la carpeta Usuaris i comproba si el nom de alguno coincidix en el nom introduit, si ho fa el estableix com a usuari actiu
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                Usuari us = (Usuari) in.readObject();
                if (us.getNom().equals(nomUs)) {
                    usuariActiu = us;
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Error, classe no trobada: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error al llegir el fitxer de usuari: " + e.getMessage());
            }
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
            } else if (intents >= 3) {// no se ni si fa falta, es mes que res per a poder eixir de inici de sessio
                                      // sense inicar sessio
                System.out.println("Massa intents fallits");
                usuariActiu = null;
                fiInici = true;
            } else {
                System.out.println("Contrasenya incorrecta, torna a intentar");
                intents++;
            }
        } while (!fiInici);
    }

}