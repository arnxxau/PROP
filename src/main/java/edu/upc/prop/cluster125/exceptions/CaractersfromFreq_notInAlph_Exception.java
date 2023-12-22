package edu.upc.prop.cluster125.exceptions;

/**
 * La classe CaractersfromFreq_notInAlph_Exception és una excepció personalitzada que es llança quan es detecta que hi ha caràcters a la freqüència que es vol afegir que no es troben a l'alfabet.
 */
public class CaractersfromFreq_notInAlph_Exception extends Exception {
    /**
     * Constructor de la classe d'excepció.
     */
    public CaractersfromFreq_notInAlph_Exception() {
        super("Hi ha caràcters a la freqüència que es vol afegir que no es troben a l'alfabet");
    }
}
