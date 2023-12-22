package edu.upc.prop.cluster125.exceptions;

/**
 * La classe badExtraction_Exception és una excepció personalitzada que es llança quan es produeix un error en l'extracció de dades.
 */
public class badExtraction_Exception extends Exception {
    /**
     * Constructor de la classe d'excepció.
     */
    public badExtraction_Exception() {
        super("Error en l'extracció");
    }
}
