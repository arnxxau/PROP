package edu.upc.prop.cluster125.domain;

import com.google.gson.annotations.Expose;

/**
 * La classe Pair representa una parella d'enters (x, y).
 */
public class Pair {
    @Expose
    private int x;
    @Expose
    private int y;

    /**
     * Constructor de la classe Pair que inicialitza les coordenades (x, y) amb valors donats.
     *
     * @param x El valor de la coordenada x.
     * @param y El valor de la coordenada y.
     */
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Estableix el valor de la coordenada x.
     *
     * @param x El nou valor de la coordenada x.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Estableix el valor de la coordenada y.
     *
     * @param y El nou valor de la coordenada y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Obté el valor de la coordenada x.
     *
     * @return El valor de la coordenada x.
     */
    public int getX() {
        return x;
    }

    /**
     * Obté el valor de la coordenada y.
     *
     * @return El valor de la coordenada y.
     */
    public int getY() {
        return y;
    }
}
