package e1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LordofRingsTest {

    @Test
    void testdadorango(){
        LordofRings.Dado d1 = new LordofRings.Dado(100);
        int tirada = d1.tirar();
        assertTrue(tirada>=0 && tirada<=100);
    }
    @Test
    void testdadotrucado(){
        LordofRings.DadoTrucado d1 = new LordofRings.DadoTrucado(100,45);
        LordofRings.DadoTrucado d2 = new LordofRings.DadoTrucado(100,45);
        assertEquals(d1.tirar(),d2.tirar());//tiene que dar siempre la misma tirada
    }
    @Test
    void testpersonaje(){

        assertThrows(IllegalArgumentException.class, () -> {new LordofRings().new Elfo("Legolas",0,50);});
        assertThrows(IllegalArgumentException.class, () -> {new LordofRings().new Elfo("Legolas",120,-4);});
        LordofRings.Elfo legolas = new LordofRings().new Elfo("Legolas", 100, 50);
        assertEquals("Legolas",legolas.getNombre());
        assertEquals(100,legolas.getVida());
        assertEquals(50,legolas.getArmadura());
        assertEquals("Elfo",legolas.getTipo());
        assertEquals("Legolas (Vida=100) ",legolas.toString());

    }
    @Test
    void testcalculardamageyvivo(){
        LordofRings.Trasgo mauhur = new LordofRings().new Trasgo("Mauhur",120,30);
        LordofRings.Humano humano = new LordofRings().new Humano("Humano", 100, 50);
        LordofRings.Dado d1 = new LordofRings.Dado(100);
        LordofRings.Dado d2 = new LordofRings.Dado(90);
        LordofRings.DadoTrucado dadotrucado = new LordofRings.DadoTrucado(100,125);
        LordofRings.Heroes.setDado(d1);
        LordofRings.Bestias.setDado(d1);

        humano.nuevodamageTurno(mauhur);
        int damage = humano.getDamage();
        assertTrue(damage >= 0 && damage <= 100);
        mauhur.recibeataque(damage);
        assertTrue(mauhur.getVida()<=120 && mauhur.getVida()>=50);//comprobacion de que se reduce la vida
        assertTrue(mauhur.EstaVivo());//comprobacion de que está vivo
        LordofRings.Heroes.setDado(dadotrucado);
        humano.nuevodamageTurno(mauhur);
        damage = humano.getDamage();
        mauhur.recibeataque(damage);
        mauhur.recibeataque(damage);
        mauhur.recibeataque(damage);
        mauhur.recibeataque(damage);
        assertFalse(mauhur.EstaVivo());//comprobacion de que está muerto
    }
    @Test
    void testcalculardamageelfo(){
        LordofRings.Elfo elfo = new LordofRings().new Elfo("Elfo",100,50);
        LordofRings.Orco orco = new LordofRings().new Orco("Orco",120,30);
        LordofRings.Dado d1 = new LordofRings.Dado(100);
        LordofRings.Dado d2 = new LordofRings.Dado(90);
        LordofRings.Heroes.setDado(d1);
        LordofRings.Bestias.setDado(d2);
        elfo.nuevodamageTurno(orco);
        int damage = elfo.getDamage();
        assertTrue(damage >= 10 && damage <= 110);//comprobacion de ese +10 de los elfos contra los orcos.
    }
    @Test
    void testcalculardamagehobbit(){
        LordofRings.Hobbit hobbit = new LordofRings().new Hobbit("Hobbit",100,50);
        LordofRings.Trasgo trasgo = new LordofRings().new Trasgo("Trasgo",120,30);
        LordofRings.Dado d1 = new LordofRings.Dado(100);
        LordofRings.Dado d2 = new LordofRings.Dado(90);
        LordofRings.Heroes.setDado(d1);
        LordofRings.Bestias.setDado(d2);
        hobbit.nuevodamageTurno(trasgo);
        int damage = hobbit.getDamage();
        assertTrue(damage >= 0 && damage <= 95);//comprobacion de ese -5 de los hobbit contra los trasgos.
    }
    @Test
    void testreduccionarmadura() {
        LordofRings.Humano humano = new LordofRings().new Humano("Humano", 100, 50);
        LordofRings.Orco orco = new LordofRings().new Orco("Orco", 120, 50);
        LordofRings.DadoTrucado dado = new LordofRings.DadoTrucado(100, 1234);
        LordofRings.Heroes.setDado(dado);
        LordofRings.Bestias.setDado(dado);
        humano.nuevodamageTurno(orco);
        orco.nuevodamageTurno(humano);
        int damageElfo = humano.getDamage();
        int damageOrco = orco.getDamage();
        assertTrue(damageOrco > damageElfo);
    }
    @Test
    void batallaheroesganan(){
        final ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaConsola));
        LordofRings juego = new LordofRings();
        LordofRings.Elfo legolas = juego.new Elfo("Legolas", 200, 50);
        LordofRings.Orco lurtz = juego.new Orco("Lurtz", 100, 10);
        LordofRings.DadoTrucado dadoHeroe = new LordofRings.DadoTrucado(100, 1234);
        LordofRings.DadoTrucado dadoBestia = new LordofRings.DadoTrucado(10, 4321);//nos aseguramos que las bestias pierdan
        LordofRings.Heroes.setDado(dadoHeroe);
        LordofRings.Bestias.setDado(dadoBestia);
        ArrayList<LordofRings.Heroes> heroes = new ArrayList<>();
        heroes.add(legolas);
        ArrayList<LordofRings.Bestias> bestias = new ArrayList<>();
        bestias.add(lurtz);
        new JuegoTurnos().batalla(heroes, bestias);
        String salida = salidaConsola.toString();
        assertTrue(salida.contains("Orco Lurtz murió!"));
        assertTrue(salida.contains("GANAN LOS HÉROES!!"));
        assertFalse(salida.contains("GANAN LAS BESTIAS!!"));
        System.setOut(new PrintStream(System.out));

    }
    @Test
    void batallabestiasganan(){
        final ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaConsola));
        LordofRings juego = new LordofRings();
        LordofRings.Humano aragorn = juego.new Humano("Aragorn", 150, 30);
        LordofRings.Orco lurtz = juego.new Orco("Lurtz", 100, 10);
        LordofRings.DadoTrucado dadoHeroe = new LordofRings.DadoTrucado(10, 1234);
        LordofRings.DadoTrucado dadoBestia = new LordofRings.DadoTrucado(100, 4321);//nos aseguramos que las bestias pierdan
        LordofRings.Heroes.setDado(dadoHeroe);
        LordofRings.Bestias.setDado(dadoBestia);
        ArrayList<LordofRings.Heroes> heroes = new ArrayList<>();
        heroes.add(aragorn);
        ArrayList<LordofRings.Bestias> bestias = new ArrayList<>();
        bestias.add(lurtz);
        new JuegoTurnos().batalla(heroes, bestias);
        String salida = salidaConsola.toString();
        assertTrue(salida.contains("Humano Aragorn murió!"));
        assertFalse(salida.contains("GANAN LOS HÉROES!!"));
        assertTrue(salida.contains("GANAN LAS BESTIAS!!"));
        System.setOut(new PrintStream(System.out));
    }
}
