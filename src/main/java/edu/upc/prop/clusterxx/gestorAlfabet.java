package edu.upc.prop.clusterxx;

import com.google.gson.*;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class gestorAlfabet {
    JsonArray ja;
    String filePath = "alphabets.json";
    public void initSaveAlphabets() {
        ja = new JsonArray();
    }
    public void saveAlphabet(Alphabet a) {
        Gson g = new Gson();
        String json = g.toJson(a);
        System.out.println(json);
        ja.add(json);
    }
    public void endSaveAlphabets() {
        System.out.println(ja);
        File fitxer = new File(filePath);
        try {
            if (!fitxer.exists()) {
                fitxer.createNewFile();
            }
            try (FileWriter fw = new FileWriter(filePath)){
                fw.write(ja.toString());
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public HashMap<String,Alphabet> getAlphabets() {
        HashMap<String, Alphabet> AP = new HashMap<>();
        try (FileReader rd = new FileReader(filePath)){
            // Llegir el contingut del fitxer JSON
            Gson g = new Gson();
            JsonArray jsonArray = g.fromJson(rd,JsonArray.class);
            // Recórrer l'array JSON i afegir cada element al HashMap
            for (JsonElement jsonElement : jsonArray) {
                Alphabet a = g.fromJson(jsonElement, Alphabet.class);
                AP.put(a.getName(), a);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return AP;
    }
}
