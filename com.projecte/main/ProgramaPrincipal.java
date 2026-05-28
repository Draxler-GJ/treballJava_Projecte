package main;

// import Alejandro.Alejandro;
// import Cesar.Cesar;
// import Daniel.Daniel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
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

        //Pelicula p1 = new Pelicula("Star Wars Ep VII", "JJ. Abrams", "Mark Hamill");
        int opcio;
        boolean eixir = false;
        while (!eixir) {
            if (usuariActiu == null) {
                System.out.println("1.Registrar-se");
                System.out.println("2.Iniciar sesio");
                System.out.println("3.Eixir");
                opcio = sc.nextInt();
                sc.nextLine();
                switch (opcio) {
                    case 1:
                        registrarUsuari();
                        break;
                    case 2:
                        iniciSessio();
                        break;
                    case 3:
                        eixir = true;
                        break;
                    default:
                        System.out.println("Fin del programa");
                        break;
                }
            } else {
                System.out.println("1.Afegir elements a la llista personal");
                System.out.println("2.Afegir elements a la llista general");
                System.out.println("3.Veure elements de la llista personal");
                System.out.println("4.Veure elements de la llista general");
                System.out.println("5.Cridar al alumne actiu");
                System.out.println("6.Ordenar llistat de pel·lícules");
                System.out.println("7.Eixir");
                opcio = sc.nextInt();
                sc.nextLine();
                if (opcio == 7) {
                    eixir = true;
                }else if( opcio == 6){
                    System.out.println("Selecciona la lista a ordenar:");
                    System.out.println("1. Llista personal de pel·lícules");
                    System.out.println("2. Llista general de pel·lícules");
                    int tipusLlista = sc.nextInt();
                    sc.nextLine();

                    ArrayList<String> llista;
                    if (tipusLlista == 1) {
                        llista = usuariActiu.getPelicules();
                        if (llista == null) llista = new ArrayList<>();
                    } else {
                        llista = llegirFitxer(getRutaLlistaGeneral("pelicules"));
                    }

                    if (llista.isEmpty()) {
                        System.out.println("La llista de pel·lícules està buida.");
                    } else {
                        System.out.println("Selecciona tipus d'ordenació:");
                        System.out.println("1. Nom (alfabètic)");        // Comparable
                        System.out.println("2. Longitud del nom");       // Comparator
                        System.out.println("3. Director + Nom");         // Comparator multiatributo
                        int opcioOrdre = sc.nextInt();
                        sc.nextLine();

                    switch (opcioOrdre) {
                        case 1:
                            // Comparable: ordenar por nombre
                            Collections.sort(llista, (a, b) -> a.compareToIgnoreCase(b));
                            break;
                        case 2:
                            // Comparator: longitud del nombre
                            Collections.sort(llista, (a, b) -> Integer.compare(a.length(), b.length()));
                            break;
                        case 3:
                            // Comparator: director + nombre
                            Collections.sort(llista, (a, b) -> {
                        String dirA = a.split("\\|")[1].trim();
                        String dirB = b.split("\\|")[1].trim();
                        int res = dirA.compareToIgnoreCase(dirB);
                        if (res == 0) {
                            String nomA = a.split("\\|")[0].trim();
                            String nomB = b.split("\\|")[0].trim();
                            return nomA.compareToIgnoreCase(nomB);
                        }
                        return res;
                        });
                    break;
                        }

                        System.out.println("Llista ordenada:");
                        int i = 1;
                        for (String peli : llista) {
                            System.out.println(i + ". " + peli);
                            i++;
                        }
                    }
                }else {
                    System.out.println(
                            "Elegix el tipus de llista per a " + (opcio - 2 <= 0 ? "afegir un element" : "veure"));
                    System.out.println("1.Pelicules");
                    System.out.println("2.Actors");
                    System.out.println("3.Directors");
                    int opcio2 = sc.nextInt();
                    sc.nextLine();
                    gestioLlistes(opcio, opcio2);
                    //si selecciones veure una llista, deuria de imprimir tots els elements de ixa llista,
                    //si selecciones afegir deuria de demanarte el id en la llista general si es afegir a una llista personal
                    //i dixarte crear el element si es a la general
                }
            }
        }

    }

    static void iniciSessio() {
        System.out.println("Nom d'usuari");
        String nomUs = sc.nextLine().trim();

        usuariActiu = buscarUsuari(nomUs);
        if (usuariActiu == null) {
            System.out.println("Usuari no trobat");
            return;
        }

        System.out.println("Contrasenya");
        int intents = 0;
        while (intents < 3) {
            String pwdUs = sc.nextLine().trim();
            if (pwdUs.equals(usuariActiu.getPassword())) {
                carregarLlistesPersonals(usuariActiu);
                System.out.println("Benvingut, " + usuariActiu.getNom());
                return;
            }
            intents++;
            if (intents >= 3) {
                System.out.println("Massa intents fallits");
                usuariActiu = null;
                return;
            }
            System.out.println("Contrasenya incorrecta, torna a intentar");
        }
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
            // comprova si el correu te @ i punt i si el @ va abans que el punt
            if (correu.contains("@") && correu.contains(".") && correu.indexOf("@") < correu.lastIndexOf(".")) {
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
        System.out.println("Introdueix la data de naixement (dd/MM/aaaa)");
        LocalDate data;
        while (true) {
            try {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                data = LocalDate.parse(sc.nextLine(), format);
                break;// si la data es incorrecte entraria en el catch en vega de seguir i eixir del
                      // bucle
            } catch (DateTimeParseException e) {
                System.out.println("Error, data invalida, tonra a introduir la data de naixement(dd/mm/aaaa)");
            }
        }
        usuariActiu = new Usuari(nom, cogn, correu, pwd, pob, data);
        crearFitxersPersonals(usuariActiu);
        usuariActiu = null;//el usuari actiu es posa a null per a que tinga que iniciar sessio despres de crearlo
        System.out.println("Usuari creat, inicia sesio");
    }

    static void gestioLlistes(int opcio, int tipus) {
        String tipusLlista = "";
        switch (tipus) {
            case 1:
                tipusLlista = "pelicules";
                break;
            case 2:
                tipusLlista = "actors";
                break;
            case 3:
                tipusLlista = "directors";
                break;
        }
        if (opcio % 2 == 0) { // opcions llista general
            if (opcio > 3) {
                ArrayList<String> elements = llegirFitxer(getRutaLlistaGeneral(tipusLlista));
                System.out.println("Elements de la llista general de " + tipusLlista + ":");
                int contador = 0;
                for (String element : elements) {
                    contador++;
                    System.out.println("- " + contador + ". " + element);
                }
                if (contador == 0) {
                    System.out.println("La llista general de " + tipusLlista + " està buida.");
                }
            } else {
                String nouElement = "";
                String directorPelicula = "";
                String actorPelicula = "";
                switch (tipusLlista) {

                    case "pelicules":
                        System.out.println("Introdueix el títol de la pel·lícula:");
                        String titol = sc.nextLine().trim();
                        System.out.println("Introdueix el nom del director:");
                        directorPelicula = sc.nextLine().trim();
                        System.out.println("Introdueix el nom de l'actor principal:");
                        actorPelicula = sc.nextLine().trim();
                        if (titol.isEmpty() || directorPelicula.isEmpty() || actorPelicula.isEmpty()) {
                            System.out.println("Falten dades, no s'ha afegit la pel·lícula.");
                            return;
                        }
                        nouElement = titol + " | " + directorPelicula + " | " + actorPelicula;


                        break;

                    case "actors":
                        System.out.println("Introdueix el nom de l'actor:");
                        String nomActor = sc.nextLine().trim();
                        System.out.println("Introdueix els cognoms de l'actor:");
                        String cognomsActor = sc.nextLine().trim();

                        if (nomActor.isEmpty() || cognomsActor.isEmpty()) {
                            System.out.println("Falten dades, no s'ha afegit l'actor.");
                            return;
                        }
                        nouElement = nomActor + " | " + cognomsActor + " | "  ;
                        break;

                    case "directors":
                        System.out.println("Introdueix el nom del director:");
                        String nomDirector = sc.nextLine().trim();
                        System.out.println("Introdueix els cognoms del director:");
                        String cognomsDirector = sc.nextLine().trim();
                        if (nomDirector.isEmpty() || cognomsDirector.isEmpty()) {
                            return;
                        }
                        nouElement = nomDirector + " | " + cognomsDirector + " | ";
                        break;
                }
                appendLiniaFitxer(getRutaLlistaGeneral(tipusLlista), nouElement);
                if (tipusLlista.equals("pelicules")) {
                    String[] directorParts = separarNomICognom(directorPelicula);
                    String[] actorParts = separarNomICognom(actorPelicula);
                    String liniaDirector = directorParts[0] + " | " + directorParts[1] + " | ";
                    String liniaActor = actorParts[0] + " | " + actorParts[1] + " | ";
                    ArrayList<String> directorsGeneral = llegirFitxer(getRutaLlistaGeneral("directors"));
                    if (!directorsGeneral.contains(liniaDirector)) {
                        appendLiniaFitxer(getRutaLlistaGeneral("directors"), liniaDirector);
                    }
                    ArrayList<String> actorsGeneral = llegirFitxer(getRutaLlistaGeneral("actors"));
                    if (!actorsGeneral.contains(liniaActor)) {
                        appendLiniaFitxer(getRutaLlistaGeneral("actors"), liniaActor);
                    }
                }
                System.out.println("Element afegit a la llista general de " + tipusLlista + ".");
            }
        } else { // opcions llista personal
            if (opcio > 2) {
                ArrayList<String> elements = usuariActiu.getLlista(tipusLlista);
                if (elements == null) {
                    elements = new ArrayList<>();
                }
                System.out.println("Elements de la llista personal de " + tipusLlista + ":");
                int contador = 0;
                for (String element : elements) {
                    contador++;
                    System.out.println("- " + contador + ". " + element);
                }
                if (contador == 0) {
                    System.out.println("La teua llista personal de " + tipusLlista + " està buida.");
                }
            } else {
                ArrayList<String> generalList = llegirFitxer(getRutaLlistaGeneral(tipusLlista));
                if (generalList.isEmpty()) {
                    System.out.println("La llista general de " + tipusLlista + " està buida. No es pot afegir cap element personal.");
                    return;
                }
                System.out.println("Selecciona l'element de la llista general per afegir-lo a la teua llista personal de " + tipusLlista + " (ID = posició + 1):");
                int contador = 0;
                for (String element : generalList) {
                    contador++;
                    System.out.println("- " + contador + ". " + element);
                }
                int idSeleccionat;
                try {
                    idSeleccionat = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Valor invàlid. Ha de ser un número." );
                    return;
                }
                if (idSeleccionat < 1 || idSeleccionat > generalList.size()) {
                    System.out.println("Id fora de rang. Prova una opció entre 1 i " + generalList.size() + ".");
                    return;
                }
                String elementSeleccionat = generalList.get(idSeleccionat - 1);
                ArrayList<String> personalList = usuariActiu.getLlista(tipusLlista);
                if (personalList == null) {
                    personalList = new ArrayList<>();
                }
                if (personalList.contains(elementSeleccionat)) {
                    System.out.println("Aquest element ja existeix a la teua llista personal.");
                    return;
                }
                personalList.add(elementSeleccionat);
                switch (tipusLlista) {
                    case "pelicules":
                        usuariActiu.setPelicules(personalList);
                        break;
                    case "actors":
                        usuariActiu.setActors(personalList);
                        break;
                    case "directors":
                        usuariActiu.setDirectors(personalList);
                        break;
                }
                appendLiniaFitxer(getRutaLlistaPersonal(usuariActiu, tipusLlista), elementSeleccionat);
                System.out.println("Element afegit a la teua llista personal de " + tipusLlista + ".");
            }
        }
    }

    static String[] separarNomICognom(String nomComplet) {
        String[] parts = nomComplet.trim().split("\\s+");
        if (parts.length == 0) {
            return new String[] {"", ""};
        }
        if (parts.length == 1) {
            return new String[] {parts[0], ""};
        }
        StringBuilder cognoms = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            if (i > 1) {
                cognoms.append(" ");
            }
            cognoms.append(parts[i]);
        }
        return new String[] {parts[0], cognoms.toString()};
    }
}
