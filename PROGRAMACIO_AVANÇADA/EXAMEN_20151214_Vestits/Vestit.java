package EXAMEN_20151214_Vestits;

import java.lang.String;

/**
 * Created by santi on 09/11/2016.
 */
public class Vestit {

    private int identificacio;
    private String model;
    private float cost;

    public Vestit(int identificacio, String model) {
        this.identificacio = identificacio;
        this.model = model;
        this.cost = -1;
    }
    public float getCost() { return cost; }
    public String getModel() { return model; }
    public void setCost(float cost) { this.cost = cost; }

    @Override
    public String toString() {
        return model +" ("+ cost +")";
    }

}
