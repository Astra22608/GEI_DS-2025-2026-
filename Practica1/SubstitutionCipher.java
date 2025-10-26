public class SubstitutionCipher {
    public static String encode (int key, String text){

        StringBuilder frase = new StringBuilder();

        for (char letra: text.toCharArray()){
            if (letra >= 'A' && letra <= 'Z') {
                char cambio = (char) ('A' + (letra - 'A' + key) % 26);
                frase.append(cambio);
            }
            else if (letra >= 'a' && letra <= 'z') {
                char cambio = (char) ('a' + (letra - 'a' + key) % 26);
                frase.append(cambio);
            }
            else{
                frase.append(letra);
            }
        }
        return frase.toString();
    }

    public static String decode (int key, String text){

        StringBuilder frase = new StringBuilder();

        for (char letra: text.toCharArray()){
            if (letra >= 'A' && letra <= 'Z') {
                char cambio = (char) ('A' + (letra - 'A' - key + 26) % 26);
                frase.append(cambio);
            }
            else if (letra >= 'a' && letra <= 'z') {
                char cambio = (char) ('a' + (letra - 'a' - key + 26) % 26);
                frase.append(cambio);
            }
            else{
                frase.append(letra);
            }
        }
        return frase.toString();
    }

    public static void main (String [] args){
        String original = "Hola mundo";
        int key = 10;
        String encoded = SubstitutionCipher.encode(key, original);
        String decoded = SubstitutionCipher.decode(key, encoded);

        System.out.println("Texto original : " + original);
        System.out.println("Codificado     : " + encoded);
        System.out.println("Decodificado   : " + decoded);
    }
}