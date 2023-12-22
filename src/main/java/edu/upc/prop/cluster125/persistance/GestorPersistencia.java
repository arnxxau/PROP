package edu.upc.prop.cluster125.persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorPersistencia<U, T> {
    public void saveElements(HashMap<U,T> map, String filePath) {
        // create folder if it does not exist
        new File("./data/").mkdirs();

        JsonArray ja = new JsonArray();
        for (T e : map.values()) {
            Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = g.toJson(e);
            System.out.println(json);
            ja.add(json);
        }
        System.out.println(ja);
        File fitxer = new File(filePath);
        try (FileWriter fw = new FileWriter(filePath)){
            if (!fitxer.exists()) fitxer.createNewFile();
            fw.write(ja.toString());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<T> getElements(Class<T> elementType, String filePath) {
        ArrayList<T> list = new ArrayList<>();
        if (!Files.exists(Paths.get(filePath))) return list;
        try (FileReader rd = new FileReader(filePath)){
            Gson g = new Gson();
            String[] elements = g.fromJson(rd, String[].class);
            for (String obj : elements) {
                T e = g.fromJson(obj, elementType);
                list.add(e);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
