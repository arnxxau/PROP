package edu.upc.prop.clusterxx;

import com.google.gson.*;

import java.io.*;
import java.util.HashMap;

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
            Gson g = new Gson();
            String[] Alphabets = g.fromJson(rd, String[].class);
            for (String s : Alphabets) {
                Alphabet a = g.fromJson(s, Alphabet.class);
                AP.put(a.getName(), a);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return AP;
    }
}
