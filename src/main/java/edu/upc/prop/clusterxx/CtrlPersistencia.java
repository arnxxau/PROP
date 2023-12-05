package edu.upc.prop.clusterxx;

import java.util.ArrayList;
import java.util.HashMap;

public class CtrlPersistencia {
    String AlphPath = "alphabets.json";
    String FreqPath = "frequencies.json";
    String GridPath = "grids.json";
    String KeyboardPath = "keyboards.json";
    GestorPersistencia<Alphabet> gpa = new GestorPersistencia<>();
    GestorPersistencia<Frequency> gpf = new GestorPersistencia<>();
    GestorPersistencia<Grid> gpg = new GestorPersistencia<>();
    GestorPersistencia<Keyboard> gpk = new GestorPersistencia<>();
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

    }
    public void saveKeyboards(HashMap<String,Keyboard> KB) {

    }
}
