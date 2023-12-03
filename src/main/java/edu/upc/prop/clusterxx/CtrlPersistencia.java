package edu.upc.prop.clusterxx;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class CtrlPersistencia {
    gestorAlfabet gA = new gestorAlfabet();
    public void saveAlphabets(HashMap<String,Alphabet> AP) {
        //si existeix fitxer, esborrar-lo i crear un de nou
        gA.initSaveAlphabets();
        for (Alphabet a : AP.values()) {
            gA.saveAlphabet(a);
        }
        gA.endSaveAlphabets();
    }
    public HashMap<String, Alphabet> getAlphabets() {
        return gA.getAlphabets();
    }
    public void saveFrequencies(HashMap<String,Frequency> FQ) {

    }
    public void saveGrids(HashMap<Integer,Grid> GD) {

    }
    public void saveKeyboards(HashMap<String,Keyboard> KB) {

    }
}
