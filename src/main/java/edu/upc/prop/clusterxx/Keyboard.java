/**
 * La classe Keyboard representa un teclat amb una distribució de lletres en un patró definides per
 * un alfabet, una freqüència i una graella.
 *
 * @author unknown
 * @version entrega 1.0
 */

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


    /**
     * Constructor de la classe Keyboard.
     *
     * @param nom       El nom del teclat.
     * @param alphabet  L'alfabet associat al teclat.
     * @param frequency La freqüència associada al teclat.
     * @param grid      La graella que defineix les posicions vàlides per al teclat.
     * @throws IllegalArgumentException Si la freqüència no pertany a l'alfabet o si el grid no té prou posicions vàlides.
     */
    public Keyboard(String nom, Alphabet alphabet, Frequency frequency, Grid grid){
        this.nom = nom;
        this.dataCreacio = Instant.now();
        this.ultimaModificacio = Instant.now();
        this.Alph = alphabet;
        this.Freq = frequency;
        this.Grid = grid;
        this.distribucio = QAP.QAPAlgorithm(alphabet,frequency,grid); // CALCULAR DISTRIBUCIÓ AMB QAP
    }

    /**
     * Actualitza la distribució de lletres del teclat.
     */
    public void update() {
        this.distribucio = QAP.QAPAlgorithm(Alph,Freq,Grid);
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
    public Instant getCrDate() {
        return dataCreacio;
    }

    /**
     * Obté la data de l'última modificació del teclat.
     *
     * @return La data de l'última modificació.
     */
    public Instant getLastMod() {
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
    }

    /**
     * Estableix una nova distribució de lletres per al teclat.
     *
     * @param distribucio La nova distribució de lletres.
     */
    public void setDistribucio(char[] distribucio) {
        this.distribucio = distribucio;
    }

    /**
     * Estableix un nou alfabet per al teclat.
     *
     * @param alph El nou alfabet del teclat.
     */
    public void setAlph(Alphabet alph) {
        this.Alph = alph;
    }

    /**
     * Estableix una nova freqüència per al teclat.
     *
     * @param freq La nova freqüència del teclat.
     */
    public void setFreq(Frequency freq) {
        this.Freq = freq;
    }

    /**
     * Estableix una nova graella per al teclat.
     *
     * @param grid La nova graella del teclat.
     */
    public void setGrid(Grid grid) {
        this.Grid = grid;
    }
}

