package EXAMEN_20150110_ESQUIS;

/**
 * Created by santi on 20/01/2017.
 */
public class ParellEsqui {

    private int identificador;
    private float llargaria;
    private int numeracioPeuMinim;
    private int numeracioPeuMaxim;

    public ParellEsqui(int identificador, float llargaria, int numeracioPeuMinim, int numeracioPeuMaxim) {
        this.identificador = identificador;
        this.llargaria = llargaria;
        this.numeracioPeuMinim = numeracioPeuMinim;
        this.numeracioPeuMaxim = numeracioPeuMaxim;
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getNumeracioPeuMaxim() {
        return numeracioPeuMaxim;
    }
    public int getNumeracioPeuMinim() {
        return numeracioPeuMinim;
    }

    public float getLlargaria() {
        return llargaria;
    }

    public String toString() {
        String s = "";
        s += "  identificador: "+ identificador +" \n";
        s += "  llargaria: "+ llargaria +" \n";
        s += "  numeracioPeuMaxim: "+ numeracioPeuMaxim +" \n";
        s += "  numeracioPeuMinim: "+ numeracioPeuMinim +" \n";
        return  "ParellEsqui: {\n"+ s +"}";
    }


}
