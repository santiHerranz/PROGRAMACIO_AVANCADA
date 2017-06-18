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

    public Caixa getDarrera() { //retorna null si no to caixa
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
        String r = "\nEl muntacarregues amb identificació" + this.identificacio
                + " porta de baix a dalt: \n=========================================";
        float kilos = 0;
        for(int i=0; i < this.quantes; i++) {
            r += caixes[i].toString();
            kilos += caixes[i].getPes();
        }
        return r + "\namb un pes total de: "+ kilos ;
    }

}
