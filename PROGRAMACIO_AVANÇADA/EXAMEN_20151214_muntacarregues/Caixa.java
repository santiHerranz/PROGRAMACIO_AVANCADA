package EXAMEN_20151214_muntacarregues;

/**
 * Created by santi on 09/11/2016.
 */
public class Caixa {
    private int identificacio;
    private float pes;
    private boolean fragil;

    public Caixa(int identificacio, float pes, boolean fragil) {
        this.identificacio = identificacio;
        this.pes = pes;
        this.fragil = fragil;
    }

    public float getPes() {
        return pes;
    }

    public int getIdentificacio() {
        return identificacio;
    }

    public boolean getFragil() {
        return fragil;
    }

    public String toString() {
        return String.format("Caixa %d:  \t%4.1f Kg %s", this.identificacio , this.pes, this.fragil);
    }
}

