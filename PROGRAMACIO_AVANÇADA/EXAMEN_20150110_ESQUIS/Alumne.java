package EXAMEN_20150110_ESQUIS;

/**
 * Created by santi on 20/01/2017.
 */
public class Alumne {

    private String nom;
    private float alcada;
    private int numeracioPeu;

    public Alumne(String nom, float alcada, int numeracioPeu) {
        this.nom = nom;
        this.alcada = alcada;
        this.numeracioPeu = numeracioPeu;
    }

    public String getNom() {
        return nom;
    }
    public int getNumeracioPeu() {
        return numeracioPeu;
    }

    public float getAlcada() {
        return alcada;
    }

    public String toString() {
        String s = "";
        s += "  nom: "+ nom +" \n";
        s += "  alcada: "+ alcada +" \n";
        s += "  numeracioPeu: "+ numeracioPeu +" \n";
        return  "Alumne: {\n"+ s +"}";
    }

}
