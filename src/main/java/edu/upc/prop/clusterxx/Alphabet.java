package edu.upc.prop.clusterxx;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;

public class Alphabet {
    private String nom;
    private Instant crDate;
    private Instant ultMod;
    private HashSet<Character> caracters;
    private ArrayList<Frequency> frequencies;

    // Constructor
    public Alphabet(String nom, HashSet<Character> cars) {
        this.nom = nom;
        this.caracters = cars;
        this.crDate = Instant.now();
        this.ultMod = Instant.now();
        this.frequencies = new ArrayList<Frequency>();
    }

    public boolean existsCharacter(char caracter) {
        return caracters.contains(caracter);
    }
    public boolean addCharacter(char caracter) {
        if (caracters.add(caracter)) {
            ultMod = Instant.now();
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteCharacter(char caracter) {
        if (caracters.remove(caracter)) {
            ultMod = Instant.now();
            return true;
        } else {
            return false;
        }
    }

    public boolean addFrequency(Frequency frequencia) {
        return frequencies.add(frequencia);
    }

    public boolean deleteFrequency(Frequency frequencia) {
        return frequencies.remove(frequencia);
    }

    // Getters
    public String getName() {
        return nom;
    }

    public Instant getCrDate() {
        return crDate;
    }

    public Instant getUltMod() {
        return ultMod;
    }

    public HashSet<Character> getCharacters() {
        return new HashSet<>(caracters);
    }

    public ArrayList<Frequency> getFrequencies() {
        return frequencies;
    }

    // Setters
    public void setName(String nom) {
        this.nom = nom;
    }
}

