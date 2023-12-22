package edu.upc.prop.cluster125.exceptions;

/**
 * La classe alphNotCompatible_Exception és una excepció personalitzada que es llança quan es detecta que dos alfabets no són compatibles.
 */
public class alphNotCompatible_Exception extends Exception {
    /**
     * Constructor de la classe d'excepció.
     */
    public alphNotCompatible_Exception() {
        super("Els alfabets no són compatibles");
    }
}

