package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para la clase Bicola")
class BicolaTest {

    private Bicolas<String> bicola;

    @BeforeEach
    void setUp() {
        bicola = new Bicolas<>();
    }

    // ========== Tests de inserción ==========

    @Test
    @DisplayName("Insertar por la izquierda en bicola vacía")
    void testInsertarIzqBicolaVacia() {
        bicola.insertarIzq("A");
        assertEquals(1, bicola.size());
        assertEquals("A", bicola.obtenerIzq());
        assertEquals("A", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Insertar múltiples elementos por la izquierda")
    void testInsertarIzqMultiplesElementos() {
        bicola.insertarIzq("A");
        bicola.insertarIzq("B");
        bicola.insertarIzq("C");

        assertEquals(3, bicola.size());
        assertEquals("C", bicola.obtenerIzq());
        assertEquals("A", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Insertar por la derecha en bicola vacía")
    void testInsertarDchBicolaVacia() {
        bicola.insertarDch("A");
        assertEquals(1, bicola.size());
        assertEquals("A", bicola.obtenerIzq());
        assertEquals("A", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Insertar múltiples elementos por la derecha")
    void testInsertarDchMultiplesElementos() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        assertEquals(3, bicola.size());
        assertEquals("A", bicola.obtenerIzq());
        assertEquals("C", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Insertar elementos alternando izquierda y derecha")
    void testInsertarAlternado() {
        bicola.insertarIzq("B");
        bicola.insertarDch("C");
        bicola.insertarIzq("A");
        bicola.insertarDch("D");

        assertEquals(4, bicola.size());
        assertEquals("A", bicola.obtenerIzq());
        assertEquals("D", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Insertar null por la izquierda lanza IllegalArgumentException")
    void testInsertarIzqNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bicola.insertarIzq(null);
        });
        assertTrue(exception.getMessage().contains("nulo"));
    }

    @Test
    @DisplayName("Insertar null por la derecha lanza IllegalArgumentException")
    void testInsertarDchNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bicola.insertarDch(null);
        });
        assertTrue(exception.getMessage().contains("nulo"));
    }

    // ========== Tests de extracción ==========

    @Test
    @DisplayName("Sacar por la izquierda devuelve y elimina el elemento")
    void testSacarIzq() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        assertEquals("A", bicola.sacarIzq());
        assertEquals(2, bicola.size());
        assertEquals("B", bicola.obtenerIzq());
    }

    @Test
    @DisplayName("Sacar por la derecha devuelve y elimina el elemento")
    void testSacarDch() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        assertEquals("C", bicola.sacarDch());
        assertEquals(2, bicola.size());
        assertEquals("B", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Sacar por la izquierda en bicola vacía lanza NoSuchElementException")
    void testSacarIzqBicolaVacia() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            bicola.sacarIzq();
        });
        assertTrue(exception.getMessage().contains("vacía"));
    }

    @Test
    @DisplayName("Sacar por la derecha en bicola vacía lanza NoSuchElementException")
    void testSacarDchBicolaVacia() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            bicola.sacarDch();
        });
        assertTrue(exception.getMessage().contains("vacía"));
    }

    @Test
    @DisplayName("Sacar todos los elementos por la izquierda")
    void testSacarTodosPorIzquierda() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        assertEquals("A", bicola.sacarIzq());
        assertEquals("B", bicola.sacarIzq());
        assertEquals("C", bicola.sacarIzq());
        assertEquals(0, bicola.size());
    }

    @Test
    @DisplayName("Sacar todos los elementos por la derecha")
    void testSacarTodosPorDerecha() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        assertEquals("C", bicola.sacarDch());
        assertEquals("B", bicola.sacarDch());
        assertEquals("A", bicola.sacarDch());
        assertEquals(0, bicola.size());
    }

    // ========== Tests de obtención ==========

    @Test
    @DisplayName("Obtener por la izquierda no elimina el elemento")
    void testObtenerIzqNoElimina() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");

        assertEquals("A", bicola.obtenerIzq());
        assertEquals(2, bicola.size());
        assertEquals("A", bicola.obtenerIzq());
    }

    @Test
    @DisplayName("Obtener por la derecha no elimina el elemento")
    void testObtenerDchNoElimina() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");

        assertEquals("B", bicola.obtenerDch());
        assertEquals(2, bicola.size());
        assertEquals("B", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Obtener por la izquierda en bicola vacía lanza NoSuchElementException")
    void testObtenerIzqBicolaVacia() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            bicola.obtenerIzq();
        });
        assertTrue(exception.getMessage().contains("vacía"));
    }

    @Test
    @DisplayName("Obtener por la derecha en bicola vacía lanza NoSuchElementException")
    void testObtenerDchBicolaVacia() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            bicola.obtenerDch();
        });
        assertTrue(exception.getMessage().contains("vacía"));
    }

    // ========== Tests de tamaño ==========

    @Test
    @DisplayName("Tamaño inicial es cero")
    void testSizeInicial() {
        assertEquals(0, bicola.size());
    }

    @Test
    @DisplayName("Tamaño aumenta al insertar")
    void testSizeAumentaAlInsertar() {
        assertEquals(0, bicola.size());
        bicola.insertarIzq("A");
        assertEquals(1, bicola.size());
        bicola.insertarDch("B");
        assertEquals(2, bicola.size());
    }

    @Test
    @DisplayName("Tamaño disminuye al sacar")
    void testSizeDisminuyeAlSacar() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");
        assertEquals(3, bicola.size());

        bicola.sacarIzq();
        assertEquals(2, bicola.size());
        bicola.sacarDch();
        assertEquals(1, bicola.size());
    }

    // ========== Tests de iterador izquierda a derecha ==========

    @Test
    @DisplayName("Iterador por defecto recorre de izquierda a derecha")
    void testIteradorPorDefecto() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        List<String> resultado = new ArrayList<>();
        for (String elemento : bicola) {
            resultado.add(elemento);
        }

        assertEquals(List.of("A", "B", "C"), resultado);
    }

    @Test
    @DisplayName("Iterador explícito de izquierda a derecha")
    void testIteradorIzquierdaADerecha() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        List<String> resultado = new ArrayList<>();
        Iterator<String> it = bicola.iterator(true);
        while (it.hasNext()) {
            resultado.add(it.next());
        }

        assertEquals(List.of("A", "B", "C"), resultado);
    }

    @Test
    @DisplayName("Iterador de derecha a izquierda")
    void testIteradorDerechaAIzquierda() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        List<String> resultado = new ArrayList<>();
        Iterator<String> it = bicola.iterator(false);
        while (it.hasNext()) {
            resultado.add(it.next());
        }

        assertEquals(List.of("C", "B", "A"), resultado);
    }

    @Test
    @DisplayName("Iterador en bicola vacía no tiene elementos")
    void testIteradorBicolaVacia() {
        Iterator<String> it = bicola.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Iterador lanza NoSuchElementException al llamar next sin elementos")
    void testIteradorNextSinElementos() {
        bicola.insertarDch("A");
        Iterator<String> it = bicola.iterator();

        it.next(); // Consume el único elemento
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    @DisplayName("Iterador detecta modificación concurrente")
    void testIteradorModificacionConcurrente() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");

        Iterator<String> it = bicola.iterator();
        it.next(); // Consume un elemento

        bicola.insertarDch("C"); // Modificamos la bicola

        assertThrows(IllegalStateException.class, it::hasNext);
    }

    @Test
    @DisplayName("Iterador remove lanza UnsupportedOperationException")
    void testIteradorRemoveNoSoportado() {
        bicola.insertarDch("A");
        Iterator<String> it = bicola.iterator();

        assertThrows(UnsupportedOperationException.class, it::remove);
    }

    @Test
    @DisplayName("Múltiples iteradores independientes")
    void testMultiplesIteradores() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        Iterator<String> it1 = bicola.iterator(true);
        Iterator<String> it2 = bicola.iterator(false);

        assertEquals("A", it1.next());
        assertEquals("C", it2.next());
        assertEquals("B", it1.next());
        assertEquals("B", it2.next());
    }

    // ========== Tests con diferentes tipos ==========

    @Test
    @DisplayName("Bicola de enteros funciona correctamente")
    void testBicolaEnteros() {
        Bicolas<Integer> bicolaInt = new Bicolas<>();
        bicolaInt.insertarDch(1);
        bicolaInt.insertarDch(2);
        bicolaInt.insertarDch(3);

        assertEquals(1, bicolaInt.sacarIzq());
        assertEquals(3, bicolaInt.sacarDch());
        assertEquals(1, bicolaInt.size());
    }

    @Test
    @DisplayName("Bicola de objetos personalizados")
    void testBicolaObjetosPersonalizados() {
        record Persona(String nombre, int edad) {}

        Bicolas<Persona> bicolaPersonas = new Bicolas<>();
        Persona p1 = new Persona("Ana", 25);
        Persona p2 = new Persona("Luis", 30);

        bicolaPersonas.insertarIzq(p1);
        bicolaPersonas.insertarDch(p2);

        assertEquals(p1, bicolaPersonas.obtenerIzq());
        assertEquals(p2, bicolaPersonas.obtenerDch());
    }

    // ========== Tests de toString ==========

    @Test
    @DisplayName("toString devuelve representación correcta")
    void testToString() {
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.insertarDch("C");

        String resultado = bicola.toString();
        assertTrue(resultado.contains("A"));
        assertTrue(resultado.contains("B"));
        assertTrue(resultado.contains("C"));
    }

    // ========== Tests de escenarios complejos ==========

    @Test
    @DisplayName("Escenario complejo: insertar, sacar y obtener alternadamente")
    void testEscenarioComplejo() {
        bicola.insertarIzq("B");
        bicola.insertarDch("C");
        bicola.insertarIzq("A");
        assertEquals("A", bicola.obtenerIzq());

        bicola.insertarDch("D");
        assertEquals("A", bicola.sacarIzq());
        assertEquals("D", bicola.sacarDch());

        assertEquals(2, bicola.size());
        assertEquals("B", bicola.obtenerIzq());
        assertEquals("C", bicola.obtenerDch());
    }

    @Test
    @DisplayName("Vaciar y rellenar la bicola múltiples veces")
    void testVaciarYRellenar() {
        // Primera ronda
        bicola.insertarDch("A");
        bicola.insertarDch("B");
        bicola.sacarIzq();
        bicola.sacarIzq();
        assertEquals(0, bicola.size());

        // Segunda ronda
        bicola.insertarIzq("X");
        bicola.insertarIzq("Y");
        assertEquals(2, bicola.size());
        assertEquals("Y", bicola.obtenerIzq());
        assertEquals("X", bicola.obtenerDch());
    }
}
