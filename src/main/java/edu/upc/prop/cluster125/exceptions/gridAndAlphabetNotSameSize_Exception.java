package edu.upc.prop.cluster125.exceptions;

/**
 * La classe gridAndAlphabetNotSameSize_Exception és una excepció personalitzada que es llança quan es detecta que l'alfabet i la grid no tenen la mateixa mida, la qual cosa no està permesa pel programa.
 */
public class gridAndAlphabetNotSameSize_Exception extends Exception {
    /**
     * Constructor de la classe d'excepció.
     */
    public gridAndAlphabetNotSameSize_Exception() {
        super("L'alfabet i la grid han de tenir la mateixa mida");
    }
}
