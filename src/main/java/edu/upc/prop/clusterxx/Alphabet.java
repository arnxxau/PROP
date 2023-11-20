/**
 * La classe Alphabet representa un alfabet amb un conjunt de caràcters i
 * freqüències associades a aquest alfabet.
 *
 * @author Andreu Bravo
 * @version entrega 1.0
 */

package edu.upc.prop.clusterxx;

import java.time.Instant;
import java.util.HashSet;
import java.util.TreeMap;

public class Alphabet {
    private String nom;
    private Instant crDate;
    private Instant ultMod;
    private HashSet<Character> caracters;
    private TreeMap<String, Frequency> frequencies;

    // Constructor

    /**
     * Constructor de la classe Alphabet.
     *
     * @param nom  El nom de l'alfabet.
     * @param cars El conjunt de caràcters associat a l'alfabet.
     */
    public Alphabet(String nom, HashSet<Character> cars) {
        this.nom = nom;
        this.caracters = cars;
        this.crDate = Instant.now();
        this.ultMod = Instant.now();
        this.frequencies = new TreeMap<String,Frequency>();
    }

    /**
     * Obté la grandària de l'alfabet, és a dir, el nombre de caràcters que conté.
     *
     * @return La grandària de l'alfabet.
     */
    public int size() { return caracters.size(); }

    /**
     * Comprova si un caràcter existeix a l'alfabet.
     *
     * @param caracter El caràcter a comprovar.
     * @return Cert si el caràcter existeix, fals altrament.
     */
    public boolean existsCharacter(char caracter) {
        return caracters.contains(caracter);
    }

    /**
     * Afegeix un caràcter a l'alfabet.
     *
     * @param caracter El caràcter a afegir.
     * @return Cert si el caràcter s'ha afegit amb èxit, fals si ja existeix.
     */
    public boolean addCharacter(char caracter) {
        if (caracters.add(caracter)) {
            ultMod = Instant.now();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un caràcter de l'alfabet.
     *
     * @param caracter El caràcter a eliminar.
     * @return Cert si el caràcter s'ha eliminat amb èxit, fals si no existeix.
     */
    public boolean deleteCharacter(char caracter) {
        if (caracters.remove(caracter)) {
            ultMod = Instant.now();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Comprova si una freqüència existeix a l'alfabet.
     *
     * @param nomF El nom de la freqüència a comprovar.
     * @return Cert si la freqüència existeix, fals altrament.
     */
    public boolean hasFrequency(String nomF) { return frequencies.containsKey(nomF);}

    /**
     * Afegeix una freqüència a l'alfabet.
     *
     * @param frequencia La freqüència a afegir.
     * @return Cert si la freqüència s'ha afegit amb èxit, fals si ja existeix.
     */
    public boolean addFrequency(Frequency frequencia) {
        if(frequencies.containsKey(frequencia.getName())) return false;
        frequencies.put(frequencia.getName(), frequencia);
        return true;
    }

    /**
     * Elimina una freqüència de l'alfabet.
     *
     * @param frequencia La freqüència a eliminar.
     * @return Cert si la freqüència s'ha eliminat amb èxit, fals si no existeix.
     */
    public boolean deleteFrequency(Frequency frequencia) {
        if (!frequencies.containsKey(frequencia.getName())) return false;
        frequencies.remove(frequencia.getName());
        return true;
    }

    // Getters

    /**
     * Obté el nom de l'alfabet.
     *
     * @return El nom de l'alfabet.
     */
    public String getName() {
        return nom;
    }

    /**
     * Obté la data de creació de l'alfabet.
     *
     * @return La data de creació.
     */
    public Instant getCrDate() {
        return crDate;
    }

    /**
     * Obté la data de l'última modificació de l'alfabet.
     *
     * @return La data de l'última modificació.
     */
    public Instant getLastMod() {
        return ultMod;
    }

    /**
     * Obté una còpia del conjunt de caràcters de l'alfabet.
     *
     * @return Una còpia del conjunt de caràcters.
     */
    public HashSet<Character> getCharacters() {
        return new HashSet<>(caracters);
    }

    /**
     * Obté les freqüències associades a l'alfabet.
     *
     * @return Les freqüències associades a l'alfabet.
     */
    public TreeMap<String, Frequency> getFrequencies() {
        return frequencies;
    }

    /**
     * Estableix el nom de l'alfabet.
     *
     * @param nom El nou nom de l'alfabet.
     */
    // Setters
    public void setName(String nom) {
        this.nom = nom;
    }
}

