package EXAMEN_20151214_Vestits;

import java.lang.String;
import java.util.Arrays;

public class Main {

    public static void main(String args[]) {

        System.out.println("Examen2 - Exercici 1");


        Vestit[] vestits = new Vestit[8];

        vestits[0] = new Vestit(1,"model 1");
        vestits[1] = new Vestit(2,"model 2");
        vestits[2] = new Vestit(3,"model 3");
        vestits[3] = new Vestit(4,"model 4");
        vestits[4] = new Vestit(5,"model 5");
        vestits[5] = new Vestit(6,"model 6");
        vestits[6] = new Vestit(7,"model 7");
        vestits[7] = new Vestit(8,"model 8");

        vestits[0].setCost(16);
        vestits[1].setCost(54);
        vestits[2].setCost(7);
        vestits[3].setCost(98);
        vestits[4].setCost(2);
        vestits[5].setCost(66);
        vestits[6].setCost(30);
        vestits[7].setCost(14);

        String[] resultat =  cercarMesMenys(vestits);

        System.out.println(Arrays.toString(vestits));

        System.out.println(Arrays.toString(resultat));


    }

// prototim del mitode a implementa
 public static String[] cercarMesMenys(Vestit vestits[]) {
     Vestit resultat[] = cercarMesMenys(vestits, 0, vestits.length - 1);
     String[] r = {resultat[0].getModel(), resultat[1].getModel()};
     return r;
 }
 public static Vestit[] cercarMesMenys(Vestit vestits[], int ini, int fi) {
     System.out.print(".");
     Vestit[] resultat = new Vestit[2];
     if (ini == fi) {
         // Nomes un element
         resultat[0] = vestits[ini];
         resultat[1] = vestits[fi];
     } else {
         int mig = (ini + fi) / 2;
         Vestit[] r1 = cercarMesMenys(vestits, ini, mig);
         Vestit[] r2 = cercarMesMenys(vestits, mig + 1, fi);
         if (r1[0].getCost() > r2[0].getCost())
             resultat[0] = r1[0];
         else
             resultat[0] = r2[0];
         if (r1[1].getCost() > r2[1].getCost()) resultat[1] = r2[1];
         else resultat[1] = r1[1];
     }
     System.out.println(Arrays.toString(resultat));
     return resultat;
 }



}
