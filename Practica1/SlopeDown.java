public class SlopeDown {
    public static void main(String[] args) {
        char[][] mapa = {
                {'.', '.', '#', '#', '.', '.', '.', '.', '.', '.', '.'},
                {'#', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.'},
                {'.', '#', '.', '.', '.', '.', '#', '.', '.', '#', '.'},
                {'.', '.', '#', '.', '#', '.', '.', '.', '#', '.', '#'},
                {'.', '#', '.', '.', '.', '#', '#', '.', '.', '#', '.'},
                {'.', '.', '#', '.', '#', '#', '.', '.', '.', '.', '.'},
                {'.', '#', '.', '#', '.', '#', '.', '.', '.', '.', '#'},
                {'.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '#', '#', '.', '.', '.', '#', '.', '.', '.'},
                {'#', '.', '.', '.', '#', '#', '.', '.', '.', '.', '#'},
                {'.', '#', '.', '.', '#', '.', '.', '.', '#', '.', '#'}
        };
        int right=3;
        int down=1;
        int arboles1 = downTheSlope(mapa,right,down);
        int arboles2 = jumpTheSlope(mapa,right,down);
        System.out.println(arboles1+" árboles encontrados con la primera estrategia");
        System.out.println(arboles2+" árboles encontrados con la segunda estrategia");
    }
    public static int downTheSlope(char[][] slopeMap, int right, int down) {
        int filas=slopeMap.length;
        int columnas=slopeMap[0].length;

        int i=0;
        int j=0;
        int arbolescruzados=0;

        if(slopeMap[0][0]=='#') {arbolescruzados++;}
        while (i<filas) {
            for (int r=0;r<right;r++) {
                j=(j + 1)%columnas;
                if (slopeMap[i][j] == '#') {
                    arbolescruzados++;
                }
            }

            for (int d=0;d<down;d++) {
                i+=1;
                if (i<filas && slopeMap[i][j] == '#') {
                    arbolescruzados++;
                }
            }
        }
        return arbolescruzados;
    }
    public static int jumpTheSlope(char[][] slopeMap, int right, int down) {
        int arbolescruzados=0;
        int i=0;
        int j=0;
        int filas=slopeMap.length;
        int columnas=slopeMap[0].length;
        if(slopeMap[0][0]=='#') {arbolescruzados++;}
        while(i<filas){
            j=(j+right)%columnas;
            i++;
            if (i<filas && slopeMap[i][j] == '#') {
                arbolescruzados++;
            }
        }
        return arbolescruzados;
    }

}