package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListaCancionesTest {
    private ListaCanciones lista;
    private int indicador;

    @BeforeEach
    void setUp() {
        lista = new ListaCanciones();
        indicador = 0;
    }

    @Test
    void testagrego(){
        lista.agregar(new ListaCanciones.Cancion("Imagine", "John Lennon", "Rock", "Imagine"), 0);
        lista.reproducecancion();
        String resultado = "La canción que está sonando es Imagine - John Lennon, Imagine(Rock)";
        assertEquals(resultado, lista.cancionsonando());
    }
    @Test
    void testeliminar() {
        assertEquals("No se puede eliminar, lista vacía", lista.eliminar(indicador));//caso lista vacia
        lista.agregar(new ListaCanciones.Cancion("Imagine", "John Lennon", "Rock", "Imagine"), 0);
        lista.agregar(new ListaCanciones.Cancion("Let It Be", "The Beatles", "Rock", "Let It Be"), 1);
        lista.agregar(new ListaCanciones.Cancion("Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera"), 2);
        assertEquals("Eliminada la última posición", lista.eliminar(3));//caso posicion >/= a lista
        assertEquals("Eliminada canción de la posición 0", lista.eliminar(0));//caso general
    }
    @Test
    void testindicador(){
        assertThrows(IllegalArgumentException.class, () -> lista.setindicador(3));//caso lista sin inicializar
        lista.agregar(new ListaCanciones.Cancion("Imagine", "John Lennon", "Rock", "Imagine"), 0);
        lista.agregar(new ListaCanciones.Cancion("Let It Be", "The Beatles", "Rock", "Let It Be"), 1);
        lista.setindicador(1);
        assertEquals(1,lista.getindicador());//caso general
        lista.setindicador(2);
    }

    @Test
    void testmover(){
        lista.agregar(new ListaCanciones.Cancion("Imagine", "John Lennon", "Rock", "Imagine"), 0);
        lista.agregar(new ListaCanciones.Cancion("Let It Be", "The Beatles", "Rock", "Let It Be"), 1);
        lista.agregar(new ListaCanciones.Cancion("Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera"), 2);
        assertThrows(IllegalArgumentException.class, () -> lista.mover(-1,3));
        assertThrows(IllegalArgumentException.class, () -> lista.mover(0,-1));
        assertThrows(IllegalArgumentException.class, () -> lista.mover(0,4));
        lista.mover(0,2);
        assertEquals("Bohemian Rhapsody - Queen, A Night at the Opera(Rock)", lista.reproducecancion().toString());
    }

    @Test
    void testreproducecancionycancionsonando(){
        lista.agregar(new ListaCanciones.Cancion("Imagine", "John Lennon", "Rock", "Imagine"), 0);
        lista.agregar(new ListaCanciones.Cancion("Let It Be", "The Beatles", "Rock", "Let It Be"), 1);
        lista.agregar(new ListaCanciones.Cancion("Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera"), 2);
        lista.setindicador(indicador);
        ListaCanciones.Cancion c = lista.reproducecancion();
        assertEquals("Imagine - John Lennon, Imagine(Rock)", c.toString());//comprobar retorno de reproducecancion
        assertEquals("La canción que está sonando es Imagine - John Lennon, Imagine(Rock)", lista.cancionsonando());//comprobar retorno de cancionsonando y función reproducecancion
        lista.stop();//lo ponemos en false
        assertEquals("No se está reproduciendo ninguna canción", lista.cancionsonando());//caso de false en cancionsonando
    }

    @Test
    void testpreviousnext(){
        lista.agregar(new ListaCanciones.Cancion("Imagine", "John Lennon", "Rock", "Imagine"), 0);
        lista.agregar(new ListaCanciones.Cancion("Let It Be", "The Beatles", "Rock", "Let It Be"), 1);
        lista.setindicador(indicador);
        lista.nextsong();
        ListaCanciones.Cancion c = lista.reproducecancion();
        assertEquals("Let It Be - The Beatles, Let It Be(Rock)", c.toString());//caso general
        lista.nextsong();
        c = lista.reproducecancion();
        assertEquals("Imagine - John Lennon, Imagine(Rock)", c.toString());//caso de estar en la última posición
        lista.previoussong();
        c = lista.reproducecancion();
        assertEquals("Let It Be - The Beatles, Let It Be(Rock)", c.toString());//caso de estar en la primera posición
        lista.previoussong();
        c = lista.reproducecancion();
        assertEquals("Imagine - John Lennon, Imagine(Rock)", c.toString());//caso general
    }

    @Test
    void testorden(){
        //por ordennatural,por autor y por estilo
        lista.agregar(new ListaCanciones.Cancion("Imagine", "John Lennon", "Rock", "Imagine"), 0);
        lista.agregar(new ListaCanciones.Cancion("Let It Be", "The Beatles", "Rock", "Let It Be"), 1);
        lista.agregar(new ListaCanciones.Cancion("Bohemian Rhapsody", "Queen", "Rock", "A Night at the Opera"), 2);
        lista.agregar(new ListaCanciones.Cancion("Shape of You", "Ed Sheeran", "Pop", "Divide"), 3);
        lista.agregar(new ListaCanciones.Cancion("Havana", "Camila Cabello", "Pop", "Camila"), 4);
        lista.agregar(new ListaCanciones.Cancion("Blinding Lights", "The Weeknd", "Pop", "After Hours"), 6);
        lista.agregar(new ListaCanciones.Cancion("Bad Guy", "Billie Eilish", "Pop", "When We All Fall Asleep"), 8);
        lista.ordenNatural();
        String esperado =
                "[Bad Guy - Billie Eilish, When We All Fall Asleep(Pop)," +
                        " Blinding Lights - The Weeknd, After Hours(Pop)," +
                        " Bohemian Rhapsody - Queen, A Night at the Opera(Rock)," +
                        " Havana - Camila Cabello, Camila(Pop)," +
                        " Imagine - John Lennon, Imagine(Rock)," +
                        " Let It Be - The Beatles, Let It Be(Rock)," +
                        " Shape of You - Ed Sheeran, Divide(Pop)]";

        assertEquals(esperado, lista.getCanciones().toString());//caso orden natural
        lista.ordenAutor();
        esperado = "[Bad Guy - Billie Eilish, When We All Fall Asleep(Pop), " +
                "Havana - Camila Cabello, Camila(Pop), " +
                "Shape of You - Ed Sheeran, Divide(Pop), " +
                "Imagine - John Lennon, Imagine(Rock), " +
                "Bohemian Rhapsody - Queen, A Night at the Opera(Rock), " +
                "Let It Be - The Beatles, Let It Be(Rock), " +
                "Blinding Lights - The Weeknd, After Hours(Pop)]";
        assertEquals(esperado, lista.getCanciones().toString());//caso orden por autor
        lista.ordenEstilo();
        esperado = "[Bad Guy - Billie Eilish, When We All Fall Asleep(Pop), " +
                "Blinding Lights - The Weeknd, After Hours(Pop), " +
                "Havana - Camila Cabello, Camila(Pop), " +
                "Shape of You - Ed Sheeran, Divide(Pop), " +
                "Bohemian Rhapsody - Queen, A Night at the Opera(Rock), " +
                "Imagine - John Lennon, Imagine(Rock), " +
                "Let It Be - The Beatles, Let It Be(Rock)]";
        assertEquals(esperado, lista.getCanciones().toString());//caso orden por estilo
    }


}

