package edu.upc.prop.cluster125.persistance;

import edu.upc.prop.cluster125.domain.Alphabet;
import edu.upc.prop.cluster125.domain.Frequency;
import edu.upc.prop.cluster125.domain.Grid;
import edu.upc.prop.cluster125.domain.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * La classe CtrlPersistencia és responsable de gestionar la persistència de dades relacionades amb l'alfabet, les freqüències,
 * les matrius (grids) i els teclats.
 */
public class CtrlPersistencia {
    private final static String GeneralPath = "data/";

    private final static String AlphPath = GeneralPath + "alphabets.json";
    private final static String FreqPath = GeneralPath + "frequencies.json";
    private final static String GridPath = GeneralPath + "grids.json";
    private final static String KeyboardPath = GeneralPath + "keyboards.json";
    private final GestorPersistencia<String, Alphabet> gpa = new GestorPersistencia<>();
    private final GestorPersistencia<String, Frequency> gpf = new GestorPersistencia<>();
    private final GestorPersistencia<Integer, Grid> gpg = new GestorPersistencia<>();
    private final GestorPersistencia<String, Keyboard> gpk = new GestorPersistencia<>();

    /**
     * Guarda un conjunt d'alfabets en un arxiu de dades.
     *
     * @param AP El conjunt d'alfabets a desar.
     */
    public void saveAlphabets(HashMap<String, Alphabet> AP) {
        gpa.saveElements(AP, AlphPath);
    }

    /**
     * Obté un conjunt d'alfabets des de l'arxiu de dades.
     *
     * @return El conjunt d'alfabets recuperat.
     */
    public HashMap<String, Alphabet> getAlphabets() {
        HashMap<String, Alphabet> map = new HashMap<>();
        ArrayList<Alphabet> list = gpa.getElements(Alphabet.class, AlphPath);
        for (Alphabet a : list) map.put(a.getName(), a);
        return map;
    }

    /**
     * Guarda un conjunt de freqüències en un arxiu de dades.
     *
     * @param FQ El conjunt de freqüències a desar.
     */
    public void saveFrequencies(HashMap<String, Frequency> FQ) {
        gpf.saveElements(FQ, FreqPath);
    }

    /**
     * Obté un conjunt de freqüències des de l'arxiu de dades.
     *
     * @return El conjunt de freqüències recuperat.
     */
    public HashMap<String, Frequency> getFrequencies() {
        HashMap<String, Frequency> map = new HashMap<>();
        ArrayList<Frequency> list = gpf.getElements(Frequency.class, FreqPath);
        for (Frequency f : list) map.put(f.getName(), f);
        return map;
    }

    /**
     * Guarda un conjunt de matrius (grids) en un arxiu de dades.
     *
     * @param GD El conjunt de matrius (grids) a desar.
     */
    public void saveGrids(HashMap<Integer, Grid> GD) {
        gpg.saveElements(GD, GridPath);
    }

    /**
     * Obté un conjunt de matrius (grids) des de l'arxiu de dades.
     *
     * @return El conjunt de matrius (grids) recuperat.
     */
    public HashMap<Integer, Grid> getGrids() {
        HashMap<Integer, Grid> map = new HashMap<>();
        ArrayList<Grid> list = gpg.getElements(Grid.class, GridPath);
        for (Grid g : list) map.put(g.getID(), g);
        return map;
    }

    /**
     * Guarda un conjunt de teclats en un arxiu de dades.
     *
     * @param KB El conjunt de teclats a desar.
     */
    public void saveKeyboards(HashMap<String, Keyboard> KB) {
        gpk.saveElements(KB, KeyboardPath);
    }

    /**
     * Obté un conjunt de teclats des de l'arxiu de dades.
     *
     * @return El conjunt de teclats recuperat.
     */
    public HashMap<String, Keyboard> getKeyboards() {
        HashMap<String, Keyboard> map = new HashMap<>();
        ArrayList<Keyboard> list = gpk.getElements(Keyboard.class, KeyboardPath);
        for (Keyboard k : list) map.put(k.getName(), k);
        return map;
    }
}
