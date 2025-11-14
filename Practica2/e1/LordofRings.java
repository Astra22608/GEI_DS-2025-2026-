package e1;
import java.util.Random;

public class LordofRings {
    public static class Dado{
        private final int max;
        protected final Random random = new Random();
        public Dado(int max) {
            this.max = max;
        }

        public int tirar(){
            return random.nextInt(max+1);
        }
    }

    public static class DadoTrucado extends Dado {

        public DadoTrucado(int max, long semilla) {
            super(max);
            this.random.setSeed(semilla);
        }
    }


    public abstract class Personaje{
        private final String nombre;
        private int vida;
        private int armadura;
        private int damage;

        public Personaje(String nombre, int vida, int armadura){
            if(vida<=0){throw new IllegalArgumentException("La vida debe ser mayor a 0");}
            if(armadura<0){throw new IllegalArgumentException("La armadura no puede ser negativa");}
            this.nombre = nombre;
            this.vida = vida;
            this.armadura = armadura;
        }

        protected abstract int calculardamage(Personaje enemigo);//metodo para calcular daños, varía entre heroes y bestias.

        public void nuevodamageTurno(Personaje enemigo) {
            this.damage = calculardamage(enemigo);//en cada turno recalcula daño
        }

        public int getDamage() {
            return damage;
        }

        public boolean EstaVivo(){
            return vida>0;
        }

        public void recibeataque(int ataque){
            vida-=Math.max(ataque-armadura,0);
        }

        public String getNombre(){
            return nombre;
        }

        public int getVida(){
            return vida;
        }
        public int getArmadura(){
            return armadura;
        }
        public void setArmadura(int armadura) {
            this.armadura = armadura;
        }

        public String getTipo() {
            return getClass().getSimpleName();
        }
        @Override
        public String toString() {
            return getNombre() + " (Vida=" + getVida() + ") ";
        }
    }

    public abstract class Heroes extends Personaje{
        protected static Dado dado;

        public Heroes(String nombre, int vida, int armadura) {
            super(nombre, vida, armadura);
        }

        public static void setDado(Dado nuevoDado) {
            dado = nuevoDado;
        }

        @Override
        protected int calculardamage(Personaje enemigo) {
            int d1 = dado.tirar();
            int d2 = dado.tirar();
            return Math.max(d1,d2);
        }//tenemos que calcularlo con un metodo random, de 0-100 de damage(entero).
    }
    public abstract class Bestias extends Personaje{
        protected static Dado dado;
        public Bestias(String nombre, int vida, int armadura) {
            super(nombre, vida, armadura);
        }
        public static void setDado(Dado nuevoDado) {
            dado = nuevoDado;
        }
        @Override
        protected int calculardamage(Personaje enemigo){
            return dado.tirar();
        }
    }

    public class Elfo extends Heroes{
        public Elfo(String nombre, int vida, int armadura) {
            super(nombre, vida, armadura);
        }

        @Override
        protected int calculardamage(Personaje enemigo){
            int base = super.calculardamage(enemigo);
            if (enemigo instanceof Orco){
                base+=10;
            }
            return base;
        }

    }
    public class Hobbit extends Heroes{
        public Hobbit(String nombre, int vida, int armadura) {
            super(nombre, vida, armadura);
        }
        @Override
        protected int calculardamage(Personaje enemigo){
            int base = super.calculardamage(enemigo);
            if(enemigo instanceof Trasgo){
                base-=5;
            }
            return Math.max(base,0);
        }

    }
    public class Humano extends Heroes{
        public Humano(String nombre, int vida, int armadura) {
            super(nombre, vida, armadura);
        }

    }
    public class Orco extends Bestias{
        public Orco(String nombre, int vida, int armadura) {
            super(nombre, vida, armadura);
        }
        @Override
        protected int calculardamage(Personaje enemigo) {
            int armaduraOriginal = enemigo.getArmadura();
            int nuevaArmadura = (int) (armaduraOriginal * 0.9);
            enemigo.setArmadura(nuevaArmadura);
            int damage = super.calculardamage(enemigo);
            enemigo.setArmadura(armaduraOriginal);
            return damage;
        }

    }
    public class Trasgo extends Bestias{
        public Trasgo(String nombre, int vida, int armadura) {
            super(nombre, vida, armadura);
        }
    }

    public static void main(String[] args) {

    }
}
