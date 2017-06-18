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
        Repartiment m = new Repartiment(8);

        m.printCaixes();

        // Exercici 2
        m.backMillorSolucio(0);
        // Exercici 3
        // Visualitzaci6 a pantalla de la millor distribuci6 1
        System.out.println(m);
    }

    private void printCaixes() {
        for (int i = 0; i < caixes.length; i++) {
            System.out.println(caixes[i]);
        }
    }


    public static void carregaCaixes(Caixa[] caixes) {
        // omplena elparamatre caixes amb tots els castes caixa a camps

        caixes[0] = new Caixa(1, 5.5f, false);
        if (caixes.length < 2) return;
        caixes[1] = new Caixa(2,4.5f, false);
        if (caixes.length < 3) return;
        caixes[2] = new Caixa(3,3.5f, false);
        if (caixes.length < 4) return;
        caixes[3] = new Caixa(4,1.5f, true);
        if (caixes.length < 5) return;
        caixes[4] = new Caixa(5,3.8f, false);
        if (caixes.length < 6) return;
        caixes[5] = new Caixa(6,4.5f, false);
        if (caixes.length < 7) return;
        caixes[6] = new Caixa(7,2.2f, false);
        if (caixes.length < 8) return;
        caixes[7] = new Caixa(8,1.1f, true);

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
                trobat = true;
            else
                acc += sol[i].donaCaixa(j).getPes();
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
        r = "\nMillor solució : " + this.qMillor + " muntacarregues";
                r=r+ "\nLa distribució següent:\n";
        for(int i=0; i < millor.length; i++) {
            if (millor[i].getQuantes()>0) r=r+ millor[i].toString()+"\n";
        }
        return r;
    }

}

