package main;

import java.util.*;

public class CatalogoPeliculas implements Iterable<Pelicula> {

    private List<Pelicula> lista = new ArrayList<>();

    public void añadir(Pelicula p) {
        lista.add(p);
    }

    public List<Pelicula> getLista() {
        return lista;
    }

    // Iterador filtrado: mínimo de letras en el nombre
    public Iterator<Pelicula> iteratorFiltrado(int minLetras) {
        return new Iterator<Pelicula>() {
            private int index = 0;

            private void avanzar() {
                while (index < lista.size()
                        && lista.get(index).getNomPelicula().length() < minLetras) {
                    index++;
                }
            }

            @Override
            public boolean hasNext() {
                avanzar();
                return index < lista.size();
            }

            @Override
            public Pelicula next() {
                avanzar();
                return lista.get(index++);
            }
        };
    }

    @Override
    public Iterator<Pelicula> iterator() {
        return lista.iterator();
    }
}