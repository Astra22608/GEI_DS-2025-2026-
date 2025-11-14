package e3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Bicolas<T> implements Iterable<T> {

    private final LinkedList<T> elementos;

    public Bicolas() {
        this.elementos = new LinkedList<>();
    }

    public void insertarIzq(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No podemos insertar un elemento nulo.");
        }
        elementos.addFirst(elemento);
    }

    public void insertarDch(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No podemos insertar un elemento nulo.");
        }
        elementos.addLast(elemento);
    }

    public T sacarIzq() {
        if (elementos.isEmpty()) {
            throw new NoSuchElementException("La bicola se encuentra vacía.");
        }
        return elementos.removeFirst();
    }

    public T sacarDch() {
        if (elementos.isEmpty()) {
            throw new NoSuchElementException("La bicola se encuentra vacía.");
        }
        return elementos.removeLast();
    }

    public T obtenerIzq() {
        if (elementos.isEmpty()) {
            throw new NoSuchElementException("La bicola se encuentra vacía.");
        }
        return elementos.getFirst();
    }

    public T obtenerDch() {
        if (elementos.isEmpty()) {
            throw new NoSuchElementException("La bicola se encuentra vacía.");
        }
        return elementos.getLast();
    }

    public int size() {
        return elementos.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new BicolaIterator(true);
    }

    public Iterator<T> iterator(boolean izquierdaADerecha) {
        return new BicolaIterator(izquierdaADerecha);
    }

    private class BicolaIterator implements Iterator<T> {

        private int posicionActual;
        private final boolean izquierdaADerecha;
        private final int tamanioInicial;

        public BicolaIterator(boolean izquierdaADerecha) {
            this.izquierdaADerecha = izquierdaADerecha;
            this.tamanioInicial = elementos.size();
            this.posicionActual = izquierdaADerecha ? 0 : elementos.size() - 1;
        }

        @Override
        public boolean hasNext() {
            if (tamanioInicial != elementos.size()) {
                throw new IllegalStateException("La bicola se modificó durante la iteración.");
            }

            return izquierdaADerecha
                    ? posicionActual < elementos.size()
                    : posicionActual >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No hay más elementos.");
            }

            T elemento = elementos.get(posicionActual);
            posicionActual += izquierdaADerecha ? 1 : -1;
            return elemento;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove no está soportado.");
        }
    }

    @Override
    public String toString() {
        return elementos.toString();
    }
}
