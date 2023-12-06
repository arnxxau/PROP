package edu.upc.prop.clusterxx;

import java.util.ArrayList;
import java.util.HashMap;

public class CtrlPersistencia {
    private String GeneralPath = "src/data/";
    private String AlphPath = GeneralPath + "alphabets.json";
    private String FreqPath = GeneralPath + "frequencies.json";
    private String GridPath = GeneralPath + "grids.json";
    private String KeyboardPath = GeneralPath + "keyboards.json";
    private GestorPersistencia<String, Alphabet> gpa = new GestorPersistencia<>();
    private GestorPersistencia<String, Frequency> gpf = new GestorPersistencia<>();
    private GestorPersistencia<Integer, Grid> gpg = new GestorPersistencia<>();
    private GestorPersistencia<String, Keyboard> gpk = new GestorPersistencia<>();
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
