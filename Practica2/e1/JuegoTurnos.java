package e1;

import java.util.ArrayList;package e1;

import java.util.ArrayList;

public class JuegoTurnos
{
    public JuegoTurnos(){}

    public void batalla(ArrayList<LordofRings.Heroes> heroes, ArrayList<LordofRings.Bestias> bestias){
        int turno=1;
        int enfrentamientos = Math.min(heroes.size(), bestias.size());
        while(!bestias.isEmpty() && !heroes.isEmpty()){
            System.out.println("Turno: " + turno + "\n");
            for(int i=0;i<enfrentamientos;i++) {

                LordofRings.Heroes heroe = heroes.get(i);
                LordofRings.Bestias bestia = bestias.get(i);

                heroe.nuevodamageTurno(bestia);
                bestia.nuevodamageTurno(heroe);//calculamos daño

                System.out.println("Lucha entre " + heroe + "y " +  bestia + "\n");

                bestia.recibeataque(heroe.getDamage());
                if(!bestia.EstaVivo()){
                    System.out.println(bestia.getTipo() + " " + bestia.getNombre()+" murió!\n");
                    bestias.remove(i);
                    i--;
                    enfrentamientos = Math.min(heroes.size(), bestias.size());
                    continue;
                }
                heroe.recibeataque(bestia.getDamage());
                if(!heroe.EstaVivo()){
                    System.out.println(heroe.getTipo() + " " + heroe.getNombre()+" murió!\n");
                    heroes.remove(i);
                    i--;
                    enfrentamientos = Math.min(heroes.size(), bestias.size());
                }

            }
            turno++;
        }
        if (heroes.isEmpty()) {
            System.out.println("GANAN LAS BESTIAS!!");
        } else {
            System.out.println("GANAN LOS HÉROES!!");
        }
    }
}
