/**
 * La classe Keyboard representa un teclat amb una distribució de lletres en un patró definides per
 * un alfabet, una freqüència i una graella.
 *
 * @author Nil Allué i Andreu Bravo
 * @version entrega 1.0
 */

package edu.upc.prop.cluster125.domain;

import com.google.gson.annotations.Expose;

import java.time.Instant;
import java.util.ArrayList;

public class Keyboard {
    @Expose
    private String nom;
    @Expose
    private String dataCreacio;
    @Expose
    private String ultimaModificacio;
    @Expose
    private char[] distribucio;
    @Expose
    private Alphabet Alph;
    @Expose
    private Frequency Freq;
    @Expose
    private Grid Grid;

    private int modeA;

    public final static int QAPAlgorithm = 0;
    public final static int LocalSearchAlgorithm = 1;


    /**
     * Constructor de la classe Keyboard.
     *
     * @param nom       El nom del teclat.
     * @param alphabet  L'alfabet associat al teclat.
     * @param frequency La freqüència associada al teclat.
     * @param grid      La graella que defineix les posicions vàlides per al teclat.
     * @throws IllegalArgumentException Si la freqüència no pertany a l'alfabet o si el grid no té prou posicions vàlides.
     */
    public Keyboard(String nom, Alphabet alphabet, Frequency frequency, Grid grid, int mode){
        this.nom = nom;
        this.dataCreacio = Instant.now().toString();
        this.ultimaModificacio = Instant.now().toString();
        this.Alph = alphabet;
        this.Freq = frequency;
        this.Grid = grid;
        if(mode == QAPAlgorithm){this.distribucio = QAP.QAPAlgorithm(alphabet,frequency,grid);} // CALCULAR DISTRIBUCIÓ AMB QAP
        else{this.distribucio = LocalSearch.HillClimbing(alphabet,frequency,grid);}
        this.modeA = mode;
    }

    /**
     * Actualitza la distribució de lletres del teclat.
     */
    public void update() {
        if(modeA == QAPAlgorithm){this.distribucio = QAP.QAPAlgorithm(Alph,Freq,Grid);} // CALCULAR DISTRIBUCIÓ AMB QAP
        else{this.distribucio = LocalSearch.HillClimbing(Alph,Freq,Grid);}
        this.ultimaModificacio = Instant.now().toString();
    }


    /**
     * Obté una representació de la distribució de lletres en el teclat basada en la graella.
     *
     * @return Una cadena que representa la distribució de lletres en el teclat.
     */


    public String getLayout() {
        char[][] mat = new char[Grid.getMaxSize().getX()][Grid.getMaxSize().getY()];
        ArrayList<Pair> al = Grid.getPositions();
        int i = 0;
        for (Pair p : al) {
            mat[p.getX()][p.getY()] = distribucio[i];
            ++i;
        }
        StringBuilder res = new StringBuilder();
        for (i = 0; i < Grid.getMaxSize().getX(); ++i) {
            for (int j = 0; j < Grid.getMaxSize().getY(); ++j) {
                if (mat[i][j] == '\0') res.append("  ");
                else res.append(mat[i][j]);
            }
            res.append('\n');
        }
        return res.toString();
    }

    // Getters

    /**
     * Obté el nom del teclat.
     *
     * @return El nom del teclat.
     */
    public String getName() {
        return nom;
    }

    /**
     * Obté la data de creació del teclat.
     *
     * @return La data de creació.
     */
    public String getCrDate() {
        return dataCreacio;
    }

    /**
     * Obté la data de l'última modificació del teclat.
     *
     * @return La data de l'última modificació.
     */
    public String getLastMod() {
        return ultimaModificacio;
    }

    /**
     * Obté la distribució de lletres actual del teclat.
     *
     * @return La distribució de lletres.
     */
    public char[] getDistribucio() {
        return distribucio;
    }

    /**
     * Obté l'alfabet associat al teclat.
     *
     * @return L'alfabet associat al teclat.
     */
    public Alphabet getAlphabet() {
        return Alph;
    }

    /**
     * Obté la freqüència associada al teclat.
     *
     * @return La freqüència associada al teclat.
     */
    public Frequency getFrequency() {
        return Freq;
    }

    /**
     * Obté la graella associada al teclat.
     *
     * @return La graella associada al teclat.
     */
    public Grid getGrid() {
        return Grid;
    }

    // Setters

    /**
     * Estableix un nou nom per al teclat.
     *
     * @param nom El nou nom del teclat.
     */
    public void setNom(String nom) {

        this.nom = nom;
        this.ultimaModificacio = Instant.now().toString();
    }

    /**
     * Estableix una nova distribució de lletres per al teclat.
     *
     * @param distribucio La nova distribució de lletres.
     */
    public void setDistribucio(char[] distribucio) {

        this.distribucio = distribucio;
        this.ultimaModificacio = Instant.now().toString();
    }

    /**
     * Estableix un nou alfabet per al teclat.
     *
     * @param alph El nou alfabet del teclat.
     */
    public void setAlph(Alphabet alph) {

        this.Alph = alph;
        this.ultimaModificacio = Instant.now().toString();
    }

    /**
     * Estableix una nova freqüència per al teclat.
     *
     * @param freq La nova freqüència del teclat.
     */
    public void setFreq(Frequency freq) {

        this.Freq = freq;
        this.ultimaModificacio = Instant.now().toString();
    }

    /**
     * Estableix una nova graella per al teclat.
     *
     * @param grid La nova graella del teclat.
     */
    public void setGrid(Grid grid) {

        this.Grid = grid;
        this.ultimaModificacio = Instant.now().toString();
    }
}
