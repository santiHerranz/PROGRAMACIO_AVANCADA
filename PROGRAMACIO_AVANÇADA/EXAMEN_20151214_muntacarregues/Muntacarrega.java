package EXAMEN_20151214_muntacarregues;

/**
 * Created by santi on 09/11/2016.
 */
public class Muntacarrega implements Cloneable {
    private int identificacio;
    private Caixa caixes[];
    private int quantes;

    public Muntacarrega(int identificacio, int quantes) {
        this.identificacio = identificacio;
        this.quantes = 0;
        this.caixes = new Caixa[quantes];
    }

    public int getIdentificacio() {
        return identificacio;
    }

    public int getQuantes() {
        return quantes;
    }

    public Caixa getDarrera() { //retorna null si no te caixa
        if (quantes>0)
            return caixes[quantes-1];
        return null;
    }

    public void addCaixa(Caixa c) {
        this.caixes[this.quantes] = c;
        quantes++;
    }

    public void remCaixa(Caixa c) {
        quantes--;
    }

    public Caixa donaCaixa(int j) {
        if (    quantes>=j)
            return caixes[j];
        return null;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
            Muntacarrega m = (Muntacarrega) o;
            m.caixes = new Caixa[this.caixes.length];
            for (int i=0; i<caixes.length; i++) {
                m.caixes[i] = this.caixes[i];
            }
        } catch (Exception j) {
        }
        return o;
    }

    public String toString() {
        // Exercici 5
        String r = "El muntacarregues id: " + (this.identificacio+1)
                + "\n porta de baix a dalt: \n=====\n";
        float kilos = 0;
        for(int i=0; i < this.quantes ; i++) {
            int index = this.quantes-1-i;
            r += caixes[index].toString() +"\n";
            kilos += caixes[index].getPes();
        }
        r += "           --------\n";
        return r + String.format("Total:  \t%4.1f Kg \n", kilos);
    }

}
