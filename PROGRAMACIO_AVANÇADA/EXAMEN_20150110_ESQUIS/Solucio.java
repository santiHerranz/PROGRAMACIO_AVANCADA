package EXAMEN_20150110_ESQUIS;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by santi on 20/01/2017.
 */
public class Solucio {

/*
Backtracking?: SI
és un problema d’optimització, s’ha de trobar el millor repartiment minimitzant les diferencies
entre els esquis i cada alumne, cal aplicar l’esquema de trobar la millor solució

Decisió:
Per cada alumne quin parell d'esquis li assignem

Arbre de decisions:
L'alcada es exacte i correspon al numero d'alumnes
L'amplada es exacte i correspon al numero de parells d'esquis, cada alumne amb un parell d'esquis

Acceptable:
Hem de comprobar l'acada i el numero de peu de l'alumne amb l'equi assignat

Solució:
Quan tots els alumnes tinguin un parell assignat
No sempre trobarem solució, si a l'alumne  no li va bé cap parell d'esquis per l'alcada o pel numero de peu.

Marcatge:
Farem servir el marcatge per indicar les parelles d'esquis que ja estan assignades

     */


    private int N;
    private Alumne totsAlumnes[];
    private ParellEsqui totsEsquis[];
    private Parell solucio[];
    private Parell millor[];


    private class Parell {
        private Alumne a;
        private ParellEsqui p;

        public Parell() { this(null,null);        }
        public Parell(Alumne a, ParellEsqui p) {
            this.a = a;
            this.p = p;
        }

        public String toString() {
            String s = "";
            s += ""+ this.a.getNom();
            s += " : "+ this.p.getIdentificador() +" ("+ p.getLlargaria() +" cm)" ;
            return "["+ s +"]";
        }
    }


    public Solucio(int n) {
        N = n;
        solucio = new Parell[n];
        millor = new Parell[n];
        totsAlumnes = new Alumne[n];
        totsEsquis = new ParellEsqui[n];
        TotesDades(totsAlumnes, n, totsEsquis);
    }


    public static void TotesDades(Alumne totsAlumnes[], int quants, ParellEsqui totsEsquis[]){

        for (int i =0; i< quants; i++) {
            totsAlumnes[i] = new Alumne("A"+(i+1) , 125 + aleatori()  , 35 + aleatori());
            totsEsquis[i] = new ParellEsqui(i, 125 + aleatori(), 30,40);
        }
    }
    private static int aleatori() {
        Random rnd = new Random();
        return rnd.nextInt(5);
    }

    public void backSolucio(int k, boolean[] marcats) {

        // iniciem marcatge d'esquis si es la primera crida
        if ( marcats == null  ) {
            marcats = new boolean[totsEsquis.length];
            for ( int i = 0; i < marcats.length; i++  )
                marcats[i] = false;
        }

        // Serà solucio si arriba a la fulla
        if ( k == N ) {

            System.out.println("Solució: "+ Arrays.toString(solucio) + " => "+ calculDiferencies(solucio) +" cm");

            // guardem millor solució o si es la primera trobada
            if (calculDiferencies(solucio) <  calculDiferencies(millor) || millor[0] == null)
                for (int m = 0; m < solucio.length; m++)
                    millor[m] = new Parell(solucio[m].a, solucio[m].p);

        } else {
            // repartim els esquies
            for (int i = 0; i < marcats.length; i++ ) {
                // assignem un parell d'esquis que no estiguin assignats
                if ( !marcats[i] ) {
                    // marquem el parell assignat
                    marcats[i] = true;

                    Parell p = new Parell(totsAlumnes[k],totsEsquis[i]);

                    // comprobem si el parell d'esqui va bé a l'alumne
                    if (acceptable(p)) {
                        solucio[k] = p;

                        // mentre sigui completable cridem recursivament
                        backSolucio(k+1, marcats);
                    }
                    // desmarquem el parell assignat
                    marcats[i] = false;
                }
            }
        }
    }

    private boolean acceptable(Parell p) {
        boolean peuCorrecte = p.a.getNumeracioPeu() >= p.p.getNumeracioPeuMinim() && p.a.getNumeracioPeu() <= p.p.getNumeracioPeuMaxim();
        boolean alcadaCorrecte = 5 >= Math.abs(p.a.getAlcada()-p.p.getLlargaria());
        return peuCorrecte && alcadaCorrecte;
    }

    private int calculDiferencies(Parell[] parells) {
        int result = 0;
        for (Parell p: parells) {
            if (p != null)
                result += Math.abs(p.a.getAlcada() - p.p.getLlargaria());
        }
        return result;
    }

    public String toString() {

        String s = "";
        if ( millor[0] != null ) {
            s += "Millor: ";
            for (int i=0; i < millor.length; i++){
                s += "("+ millor[i].a.getNom();
                s += ": "+ millor[i].p.getLlargaria() +" cm )\t" ;
            }
            s += " => "+ calculDiferencies(millor) + " cm";
        } else
            s += "No hi ha cap solució";
        return  s;
    }

}
