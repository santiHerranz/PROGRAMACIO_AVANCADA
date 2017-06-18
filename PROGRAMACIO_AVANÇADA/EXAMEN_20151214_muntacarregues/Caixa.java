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
        return "\n   la caixa identificacada amb: " + this.identificacio + " de " + this.pes +" kilos";
    }
}

