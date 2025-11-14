package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaCanciones {
    public record Cancion(String titulo, String autor, String estilo, String album) implements Comparable<Cancion>{

        @Override
        public String toString() {
            return titulo + " - " + autor + ", " + album + "(" + estilo + ")";
        }

        @Override
        public int compareTo(Cancion otro){
            int igualdad = this.titulo.compareToIgnoreCase(otro.titulo);
            if(igualdad == 0){
                return this.album.compareToIgnoreCase(otro.album);
            }
            else {
                return igualdad;
            }
        }
    }
    private final ArrayList<Cancion> canciones = new ArrayList<>();//el final protege la referencia, no sus modificaciones(el objeto en sí)
    private int indicador = 0;
    private boolean reproduciendo = false;

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void agregar(Cancion agrego, int posicion){//metodo añadir cancion
        if(posicion>=canciones.size()){
            canciones.add(agrego);
        }
        else{
            canciones.add(posicion, agrego);
        }
    }
    public String eliminar(int posicion){//metodo eliminar cancion
        if(canciones.isEmpty()){
            return "No se puede eliminar, lista vacía";
        }
        else if(posicion >= canciones.size()) {
            canciones.removeLast();//eliminara el ultimo si el usuario se pasa de posicion
            return "Eliminada la última posición";
        }
        else{
            canciones.remove(posicion);//elimina cancion
            return "Eliminada canción de la posición " + posicion;
        }
    }
    public void mover(int posicion1, int posicion2){
        if(posicion1<0||posicion2<0||posicion1>=canciones.size()||posicion2>=canciones.size()){
            throw new IllegalArgumentException("Introduce una posición válida");
        }
        Cancion c1 = canciones.get(posicion1);
        Cancion c2 = canciones.get(posicion2);
        canciones.set(posicion1, c2);
        canciones.set(posicion2, c1);
    }

    public int getindicador(){//getter de indicador
        return this.indicador;
    }

    public void setindicador(int indicador){//setter de indicador
        if(canciones.isEmpty()){
            throw new IllegalArgumentException("Lista sin inicializar");
        }
        else if(indicador>=canciones.size()){
            this.indicador = canciones.size()-1;
        }
        else this.indicador = Math.max(indicador, 0);
    }


    public Cancion reproducecancion(){
        reproduciendo = true;
        return canciones.get(indicador);
    }

    public String cancionsonando(){
        if(reproduciendo) {
            return "La canción que está sonando es " + canciones.get(indicador);
        }
        else {
            return "No se está reproduciendo ninguna canción";
        }
    }

    public void nextsong(){
        if(indicador==canciones.size()-1){
            indicador=0;
        }
        else {
            indicador++;
        }
        reproduciendo = true;
    }

    public void previoussong(){
        if(indicador==0){
            indicador=canciones.size()-1;
        }
        else {
            indicador--;
        }
        reproduciendo = true;
    }

    public void stop(){
        reproduciendo = false;
    }

    public void ordenNatural(){//metodo con el comparable
        Collections.sort(canciones);
        reproduciendo = false;
        indicador = 0;
    }

    Comparator<Cancion> porAutor = new Comparator<>() {

        @Override
        public int compare(Cancion c1, Cancion c2){
            int comparacion1 = c1.autor.compareToIgnoreCase(c2.autor);
            if(comparacion1 == 0){
                int comparacion2 = c1.album.compareToIgnoreCase(c2.album);
                if(comparacion2 == 0){
                    return c1.titulo.compareToIgnoreCase(c2.titulo);
                }
                return comparacion2;
            }
            return comparacion1;
        }

    };

    Comparator<Cancion> porEstilo = new Comparator<>() {
        @Override
        public int compare(Cancion c1, Cancion c2){
            int comparacion1 = c1.estilo.compareToIgnoreCase(c2.estilo);
            if(comparacion1 == 0){
                return c1.titulo.compareToIgnoreCase(c2.titulo);
            }
            return comparacion1;
        }
    };

    public void ordenAutor(){
        Collections.sort(canciones, porAutor);
        reproduciendo = false;
        indicador = 0;
    }

    public void ordenEstilo(){
        Collections.sort(canciones, porEstilo);
        reproduciendo = false;
        indicador = 0;
    }

}
