package main;

import Alejandro.Alejandro;
import Cesar.Cesar;
import Daniel.Daniel;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramaPrincipal {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException {
        /*
        falta fer la opcio de registre o login 
        de momen gastem soles objected i ya farem
        gestio de archius
        */
       ArrayList<Usuari> usuaris = new ArrayList<>();
       Alejandro alejandro = new Alejandro("Alejandro");
       Cesar cesar = new Cesar("Cesar");
       Daniel daniel = new Daniel("Daniel");
        Usuari usuariActiu = null;
       alejandro.mostrarAlumne();
       cesar.mostrarAlumne();
       daniel.mostrarAlumne();

       Pelicula p1 = new Pelicula("Star Wars Ep VII", "JJ. Abrams", "Mark Hamill");

       p1.inserirPelicules();
        boolean eixir = false;
       while (!eixir) {//temporal, heu organizarem cuant funcione tot be
        System.out.println("1.registre");
        System.out.println("2.inici de sesio");
        System.out.println("3.Eixir");
        int opcio = sc.nextInt();
        sc.nextLine();//buffer
        switch (opcio) {
            case 1:
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
                usuaris.add(new Usuari(nom, cogn, correu, pwd, pob, data));
                System.out.println("usuari creat, inicia sesio");
                break;
            case 2:
                System.out.println("Nom de usuari");
                String nomUs = sc.nextLine();
                for (int i = 0; i < usuaris.size() && usuariActiu == null; i++) {
                    if (usuaris.get(i).getNom().equals(nomUs)) {
                        usuariActiu = usuaris.get(i);
                    }   
                }
                if (usuariActiu == null) {
                    System.out.println("Usuari no trobat");
                    break;
                }
                System.out.println("Contrasenya");
                boolean fiInici = false;
                do {//TODO: no funciona, no ix del bucle per algun motiu que desconec
                    
                    int intents = 0;
                    String pwdUs = sc.nextLine();
                    if (pwdUs.equals(usuariActiu.getPassword())) {
                        System.out.println("Benvingut, " + usuariActiu.getNom());
                    } else if (intents >= 3) {// no se ni si fa falta, es mes que res per a poder eixir de inici de sessio sense inicar sessio
                        System.out.println("Massa intents fallits");
                        usuariActiu = null;
                        fiInici = true;
                    } else {
                        System.out.println("Contrasenya incorrecta, torna a intentar");
                        intents++;
                    }
                } while (!fiInici);
                
                break;
            case 3:
                eixir = true;
                break;
            default:
                break;
        }

       }
    }
}