package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class GestioFitxers {

    private static final File dirUsuaris = obtenirDirectori("Usuaris");
    private static final File dirLlistes = obtenirDirectori("Llistes");
    private static final File dirDades = obtenirDirectori("Dades");

    private static File obtenirDirectori(String nomCarpeta) {
        File fitxerLocal = new File(nomCarpeta);
        if (fitxerLocal.exists() && fitxerLocal.isDirectory()) {
            return fitxerLocal;
        }
        File fitxerAlternatiu = new File("com.projecte", nomCarpeta);
        if (fitxerAlternatiu.exists() && fitxerAlternatiu.isDirectory()) {
            return fitxerAlternatiu;
        }
        return fitxerLocal;
    }

    // llig tots els objectes de la carpeta Usuaris i comproba si el nom de alguno
    // coincidix en el nom introduit, si ho fa el estableix com a usuari actiu
    // probablement es pot fer de alguna manera millor, de moment funciona
    public static File buscarFitxer(String nom) {
        if (!dirUsuaris.exists() || !dirUsuaris.isDirectory()) {
            return null;
        }
        File[] llista = dirUsuaris.listFiles();
        if (llista == null) {
            return null;
        }
        for (File file : llista) {
            String nomFitxer = file.getName();
            if (nomFitxer.equalsIgnoreCase(nom + ".dat") || nomFitxer.split("\\.")[0].equalsIgnoreCase(nom)) {
                return file;
            }
        }
        return null;
    }

    // trau el usuari de un arxiu buscat i el retorna
    public static Usuari buscarUsuari(String nom) {
        File fitxer = buscarFitxer(nom);
        if (fitxer == null || !fitxer.exists()) {
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fitxer))) {
            return (Usuari) in.readObject();
        } catch (IOException e) {
            System.out.println("Error llegint l'usuari: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error classe no trobada: " + e.getMessage());
        }
        return null;

    }

    public static void crearFitxersPersonals(Usuari u) {
        dirUsuaris.mkdirs();
        dirLlistes.mkdirs();
        File usuari = new File(dirUsuaris, u.getNom() + ".dat");
        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream(usuari))) {
            ou.writeObject(u);
            File carpetaPersonal = new File(dirLlistes, u.getId() + u.getCorreu().split("@")[0]);
            carpetaPersonal.mkdirs();
            new File(carpetaPersonal, "pelicules.llista").createNewFile();
            new File(carpetaPersonal, "actors.llista").createNewFile();
            new File(carpetaPersonal, "directors.llista").createNewFile();
        } catch (IOException e) {
            System.out.println("Error creant el usuari: " + e.getMessage());
        }
    }

    public static String getRutaLlistaGeneral(String tipusLlista) {
        return new File(dirDades, tipusLlista + ".dades").toString();
    }

    public static String getRutaLlistaPersonal(Usuari u, String tipusLlista) {
        return new File(new File(dirLlistes, u.getId() + u.getCorreu().split("@")[0]), tipusLlista + ".llista").toString();
    }

    public static void appendLiniaFitxer(String ruta, String linia) {
        try {
            File fitxer = new File(ruta);
            fitxer.getParentFile().mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxer, true))) {
                writer.write(linia);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escriure al fitxer: " + e.getMessage());
        }
    }

    public static void carregarLlistesPersonals(Usuari u) {
        u.setPelicules(llegirFitxer(getRutaLlistaPersonal(u, "pelicules")));
        u.setActors(llegirFitxer(getRutaLlistaPersonal(u, "actors")));
        u.setDirectors(llegirFitxer(getRutaLlistaPersonal(u, "directors")));
    }

    static ArrayList<String> llegirFitxer(String ruta) {
        // llig un fitxer i retorna un arraylist amb les linies del fitxer
        ArrayList<String> linies = new ArrayList<>();
        File fitxer = new File(ruta);
        if (!fitxer.exists()) {
            return linies;
        }
        try (Scanner scFitxer = new Scanner(fitxer)) {
            while (scFitxer.hasNextLine()) {
                linies.add(scFitxer.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error al llegir el fitxer: " + e.getMessage());
        }
        return linies;
    }
}

