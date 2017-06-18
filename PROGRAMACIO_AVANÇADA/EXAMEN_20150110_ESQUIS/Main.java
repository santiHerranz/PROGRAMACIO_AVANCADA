package EXAMEN_20150110_ESQUIS;

/**
 * Created by santi on 20/01/2017.
 */

public class Main {

    public static void main (String[] args) {
        System.out.print("Numero alumnes?");
        int n = Keyboard.readInt();
        if (n>0) {
            Solucio s = new Solucio(n);
            s.backSolucio(0, null);
            System.out.println(s.toString());
        }
    }
}
