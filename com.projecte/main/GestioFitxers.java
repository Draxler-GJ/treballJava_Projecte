package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestioFitxers {

    private static File dirUsuaris = new File("./Usuaris");
    private static File dirLlistes = new File("./Llistes");
    // llig tots els objectes de la carpeta Usuaris i comproba si el nom de alguno
    // coincidix en el nom introduit, si ho fa el estableix com a usuari actiu
    // probablement es pot fer de alguna manera millor, de moment funciona
    public static File buscarFitxer(String nom) {
        File[] llista = dirUsuaris.listFiles();
        for (File file : llista) {
            if (file.getName().split("\\.")[0].equals(nom)) {
                return file;
            }
        }
        return null;
    }

    // trau el usuari de un arxiu buscat i el retorna
    public static Usuari buscarUsuari(String nom) {
        if (GestioFitxers.buscarFitxer(nom) == null) {
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(buscarFitxer(nom)))) {
            return (Usuari) in.readObject();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error classe no trobada " + e.getMessage());
        }
        return null;

    }

    public static void crearFitxersPersonals(Usuari u) {
        File usuari = new File(dirUsuaris + "/" + u.getNom() + ".dat");
        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream(usuari))) {//crear arxiu personal, no me agrada clavar-ho en el constructor caldria menejar-ho al registre
            ou.writeObject(u);
            File carpetaPersonal = new File(dirLlistes + "/" + u.getId() + u.getCorreu().split("@")[0]);
            carpetaPersonal.mkdir();
            //TODO: crear directori llistes
            
        } catch (IOException e) {
            System.out.println("Error creant el usuari: " + e.getMessage());
        }
    }

}
