package edu.upc.prop.cluster125.persistance;

import edu.upc.prop.cluster125.domain.Alphabet;
import edu.upc.prop.cluster125.domain.Frequency;
import edu.upc.prop.cluster125.domain.Grid;
import edu.upc.prop.cluster125.domain.Keyboard;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void saveAlphabets(HashMap<String,Alphabet> AP) {
        gpa.saveElements(AP, AlphPath);
    }
    public HashMap<String, Alphabet> getAlphabets() {
        HashMap<String, Alphabet> map = new HashMap<>();
        ArrayList<Alphabet> list = gpa.getElements(Alphabet.class, AlphPath);
        for (Alphabet a : list) map.put(a.getName(), a);
        return map;
    }
    public void saveFrequencies(HashMap<String,Frequency> FQ) {
        gpf.saveElements(FQ, FreqPath);
    }
    public HashMap<String, Frequency> getFrequencies() {
        HashMap<String, Frequency> map = new HashMap<>();
        ArrayList<Frequency> list = gpf.getElements(Frequency.class, FreqPath);
        for (Frequency f : list) map.put(f.getName(), f);
        return map;
    }
    public void saveGrids(HashMap<Integer,Grid> GD) {
        gpg.saveElements(GD, GridPath);
    }
    public HashMap<Integer, Grid> getGrids() {
        HashMap<Integer, Grid> map = new HashMap<>();
        ArrayList<Grid> list = gpg.getElements(Grid.class, GridPath);
        for (Grid g : list) map.put(g.getID(), g);
        return map;
    }
    public void saveKeyboards(HashMap<String,Keyboard> KB) {
        gpk.saveElements(KB, KeyboardPath);
    }
    public HashMap<String, Keyboard> getKeyboards() {
        HashMap<String, Keyboard> map = new HashMap<>();
        ArrayList<Keyboard> list = gpk.getElements(Keyboard.class, KeyboardPath);
        for (Keyboard k : list) map.put(k.getName(), k);
        return map;
    }
}
