package EXAMEN_20151214_muntacarregues;

/**
 * Created by santi on 09/11/2016.
 */
public class Repartiment {
    private Caixa caixes[];

    // Exercici 1 - Afegeix atributs
    private Muntacarrega[] sol;
    private int qSol; // grues ocupades
    private Muntacarrega[] millor;
    private int qMillor;


    public static void main(String args[]) {
        // demanem nUmero de caixes a distribuir
        //Keyboard.readInt();
        Repartiment m = new Repartiment(5);

        // Exercici 2
        m.backMillorSolucio(0);
        // Exercici 3
        // Visualitzaci6 a pantalla de la millor distribuci6 1
        System.out.println(m);
    }


    public static void carregaCaixes(Caixa[] caixes) {
        // omplena elparamatre caixes amb tots els castes caixa a camps

        caixes[0] = new Caixa(0,5, false);
        caixes[1] = new Caixa(1,4, false);
        caixes[2] = new Caixa(2,3, false);
        caixes[3] = new Caixa(3,2, false);
        caixes[4] = new Caixa(4,2, false);

    }

    public Repartiment(int quantes) {
        caixes = new Caixa[quantes];
        carregaCaixes(this.caixes);

        // Completar Exercici 2
        sol = new Muntacarrega[quantes];
        for(int i=0; i<sol.length; i++)
            sol[i] = new Muntacarrega(i,quantes);
        qSol = 0;
        millor = new Muntacarrega[quantes];
        for(int i=0; i<millor.length; i++)
            millor[i] = new Muntacarrega(i,quantes);
        qMillor = quantes +1; // per força ha d'haver una millor
    }


    public void backMillorSolucio(int k) { // numero caixa a ubicar
        // Exercici 4
        int i = 0;
        while (i < sol.length) { // acceptable caixa k al muntacarregues i
            if (acceptable(i, k)) {
                if (sol[i].getQuantes() == 0)
                    qSol++;
                sol[i].addCaixa(caixes[k]);
                if (k == caixes.length - 1) { // es soluci6
                    if (millorSolucio()) {
                        for (int m = 0; m < sol.length; m++)
                            millor[m] = (Muntacarrega) (sol[m].clone());//s'ha de redefini el clone()
                        qMillor = qSol;
                    }
                } else
                    backMillorSolucio(k + 1);
                // si haviem ocupat per primer cop decrementem
                if (sol[i].getQuantes() == 1)
                    qSol--;
                sol[i].remCaixa(caixes[k]);
            }
            i++;
        }
    }

    private boolean acceptable(int i, int k) { // Exercici 4 1--> muntacarregues / k--> caixa
        float acc = 0;
        boolean trobat = false;
        for (int j = sol[i].getQuantes() - 1; j >= 0 && !trobat; j--) {
            if (acc + caixes[k].getPes() > sol[i].donaCaixa(j).getPes())
                trobat = true; else acc += sol[i].donaCaixa(j).getPes();
    } return !trobat;
    }

    private boolean millorSolucio() {
        return qSol < qMillor || (qSol == qMillor && fragils());
    }

    private boolean fragils() {
        int cont1=0, cont2 =0;
        for (int i=0; i<sol.length;i++){
            if (sol[i].getQuantes()>0 && sol[i].getDarrera().getFragil())
                cont1++;
            if (millor[i].getQuantes()>0 && millor[i].getDarrera().getFragil())
                cont2++;
        }
        return  cont1>cont2;
    }

    public String toString() {
        // Exercici 5
        String r;
        r = "La millor solució usa: " + this.qMillor + " montacarregues";
                r=r+ "\nLa distribució següent:\n";
        for(int i=0; i < millor.length; i++) {
            if (millor[i].getQuantes()>0) r=r+ millor[i].toString()+"\n";
        }
        return r;
    }

}

