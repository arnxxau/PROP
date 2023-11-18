package edu.upc.prop.clusterxx;

import java.time.Instant;
import java.util.ArrayList;

public class Keyboard {
    private String nom;
    private Instant dataCreacio;
    private Instant ultimaModificacio;
    private char[] distribucio;
    private Alphabet Alph;
    private Frequency Freq;
    private Grid Grid;

    //pre: la frequència pertany a l'alfabet
    //pre: el grid té prous posicions vàlides per posar totes les lletres de l'alfabet
    public Keyboard(String nom, Alphabet alphabet, Frequency frequency, Grid grid){
        this.nom = nom;
        this.dataCreacio = Instant.now();
        this.ultimaModificacio = Instant.now();
        this.Alph = alphabet;
        this.Freq = frequency;
        this.Grid = grid;
        this.distribucio = new char[]{'a', 'b', 'c', 'd'}; // CALCULAR DISTRIBUCIÓ AMB QAP
    }
    public void update() {
        this.distribucio = null; // RECALCULAR DISTRIBUCIÓ
    }
    public String getLayout() {
        char[][] mat = new char[Grid.getMaxSize().x][Grid.getMaxSize().y];
        ArrayList<Pair> al = Grid.getPositions();
        int i = 0;
        for (Pair p : al) {
            mat[p.x][p.y] = distribucio[i];
            ++i;
        }
        String res = "";
        for (i = 0; i < Grid.getMaxSize().x; ++i) {
            for (int j = 0; j < Grid.getMaxSize().y; ++j) {
                if (mat[i][j] == '\0') res += ' ' + ' ';
                else res += mat[i][j];
            }
            res += '\n';
        }
        return res;
    }

    // Getters
    public String getName() {
        return nom;
    }
    public Instant getCrDate() {
        return dataCreacio;
    }
    public Instant getLastMod() {
        return ultimaModificacio;
    }
    public char[] getDistribucio() {
        return distribucio;
    }
    public Alphabet getAlphabet() {
        return Alph;
    }
    public Frequency getFrequency() {
        return Freq;
    }
    public Grid getGrid() {
        return Grid;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setDistribucio(char[] distribucio) {
        this.distribucio = distribucio;
    }
    public void setAlph(Alphabet alph) {
        this.Alph = alph;
    }
    public void setFreq(Frequency freq) {
        this.Freq = freq;
    }
    public void setGrid(Grid grid) {
        this.Grid = grid;
    }
}

