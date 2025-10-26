public class TrafficJunction {
    private int contador;
    private final Semaforo norte;
    private final Semaforo sur;
    private final Semaforo este;
    private final Semaforo oeste;
    private Semaforo actual;
    public TrafficJunction() {
        contador = 0;
        norte = new Semaforo(Semaforo.PuntosCardinales.NORTE, Semaforo.ColoresSemaforo.VERDE);
        sur   = new Semaforo(Semaforo.PuntosCardinales.SUR, Semaforo.ColoresSemaforo.ROJO);
        este  = new Semaforo(Semaforo.PuntosCardinales.ESTE, Semaforo.ColoresSemaforo.ROJO);
        oeste = new Semaforo(Semaforo.PuntosCardinales.OESTE, Semaforo.ColoresSemaforo.ROJO);
        actual = norte;
    }


    static class Semaforo {
        private final PuntosCardinales ubicacion;
        private ColoresSemaforo color;

        public Semaforo(PuntosCardinales ubicacion, ColoresSemaforo color) {
            this.ubicacion = ubicacion;
            this.color = color;
        }

        enum PuntosCardinales {
            NORTE,
            SUR,
            ESTE,
            OESTE
        }

        public int obtenerDuracion() {
            return color.duracion();
        }

        public PuntosCardinales obtenerUbicacion() {
            return ubicacion;
        }

        public ColoresSemaforo obtenerColor() {
            return color;
        }

        public void setColor(ColoresSemaforo nuevo) {
            this.color = nuevo;
        }

        enum ColoresSemaforo {  //actua como metodo.

            VERDE(15),
            AMBAR(5),
            AMBAR_PARPADEO(-1),
            ROJO(-1);

            private final int duracion;

            ColoresSemaforo(int duracion) {
                this.duracion = duracion;
            }

            public int duracion() {
                return duracion;
            }


            public ColoresSemaforo next() {
                return switch (this) {
                    case VERDE -> AMBAR;
                    case AMBAR -> ROJO;
                    case ROJO -> VERDE;
                    case AMBAR_PARPADEO -> AMBAR_PARPADEO;
                };
            }
        }
    }

    Semaforo siguienteSemaforo(Semaforo s) {
        return switch (s.ubicacion) {
            case NORTE -> this.sur;
            case SUR -> this.este;
            case ESTE -> this.oeste;
            case OESTE -> this.norte;
        };
    }

    void changecoloramber(boolean active) {
        if (active) {
            norte.setColor(Semaforo.ColoresSemaforo.AMBAR_PARPADEO);
            sur.setColor(Semaforo.ColoresSemaforo.AMBAR_PARPADEO);
            este.setColor(Semaforo.ColoresSemaforo.AMBAR_PARPADEO);
            oeste.setColor(Semaforo.ColoresSemaforo.AMBAR_PARPADEO);
        } else {
            norte.setColor(Semaforo.ColoresSemaforo.VERDE);
            sur.setColor(Semaforo.ColoresSemaforo.ROJO);
            este.setColor(Semaforo.ColoresSemaforo.ROJO);
            oeste.setColor(Semaforo.ColoresSemaforo.ROJO);
            actual=norte;
            contador=0;
        }
    }

    public void timegoesby() {
        contador++;
        if (contador >= actual.obtenerDuracion()&&actual.obtenerDuracion()!=-1) {
            actual.setColor(actual.obtenerColor().next());
            contador = 0;
            if (actual.obtenerColor() == Semaforo.ColoresSemaforo.ROJO) {
                actual = siguienteSemaforo(actual);
                actual.setColor( Semaforo.ColoresSemaforo.VERDE);
            }
        }
    }

    @Override
    public String toString(){
        String estado="";
        estado+=imprimir(norte);
        estado+=imprimir(sur);
        estado+=imprimir(este);
        estado+=imprimir(oeste);

        return estado;
        /*[NORTH: GREEN 2][SOUTH: RED][EAST: RED][WEST: RED]
         * [NORTH: AMBER OFF 5][SOUTH: RED][EAST: RED][WEST: RED]
         * [NORTH: AMBER ON][SOUTH: AMBER ON][EAST: AMBER ON][WEST: AMBER ON]*/

    }
    public String imprimir(Semaforo s){
        if(s.obtenerColor()==Semaforo.ColoresSemaforo.ROJO||s.obtenerColor()==Semaforo.ColoresSemaforo.AMBAR_PARPADEO){
            return "["+s.obtenerUbicacion()+": "+s.obtenerColor()+" ]";
        }
        else {
            return "[" + s.obtenerUbicacion() + ": " + s.obtenerColor() + " " + contador + "]";
        }
    }

    static void main() {
        TrafficJunction tj = new TrafficJunction();
        System.out.println(tj.toString());
        int i=0;
        while(i<22) {
            tj.timegoesby();
            i++;
        }
        System.out.println(tj.toString());
    }
}