package edu.upc.prop.clusterxx;


import java.time.Instant;

public class Keyboard {
    private String nom;
    private Instant dataCreacio;

    private Instant ultimaModificacio;

    private char[] distribucio;

    private Alphabet Alph;

    private Frequency Freq;

    private Grid Grid;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAlph(Alphabet alph) {
        Alph = alph;
    }

    public Keyboard(){}
    public Keyboard(String nom, Alphabet alphabet, Frequency frequency, Grid grid){
        //S'ha de comprobar que la freq. es d'alfabet?

        this.nom=nom;

        dataCreacio=Instant.now();

        Alph=alphabet;

        Freq = frequency;

        Grid = grid;

    }

}

