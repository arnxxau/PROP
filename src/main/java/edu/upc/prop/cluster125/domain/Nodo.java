package edu.upc.prop.cluster125.domain;

import java.util.HashSet;
import java.util.Map;

/**
 * La classe Nodo representa un node utilitzat en un algorisme relacionat amb la resolució de problemes d'optimització.
 * Cada node conté una solució representada com un mapa de caràcters i enters, un conjunt d'índexs de posicions,
 * un conjunt de caràcters i una cota associada que ajuda a determinar la qualitat de la solució.
 */
public class Nodo implements Comparable<Nodo> {
    private final Map<Character, Integer> solucio;
    private final HashSet<Integer> posicions;
    private final HashSet<Character> simbols;
    private final int cota;

    /**
     * Constructor de la classe Nodo.
     *
     * @param sol La solució representada com un mapa de caràcters i enters.
     * @param pos Les posicions de la solució representades com un conjunt d'índexs.
     * @param sim Els caràcters de la solució representats com un conjunt de caràcters.
     * @param c La cota associada a aquest node.
     */
    public Nodo(Map<Character, Integer> sol, HashSet<Integer> pos, HashSet<Character> sim, int c){
        solucio = sol;
        posicions = pos;
        simbols = sim;
        cota = c;
    }

    /**
     * Obté la solució d'aquest node com un mapa de caràcters i enters.
     *
     * @return La solució del node.
     */
    public Map<Character, Integer> getSolucio() {
        return solucio;
    }

    /**
     * Obté els caràcters de la solució com un conjunt de caràcters.
     *
     * @return Els caràcters de la solució.
     */
    public HashSet<Character> getSimbols() {
        return simbols;
    }

    /**
     * Obté les posicions de la solució com un conjunt d'índexs.
     *
     * @return Les posicions de la solució.
     */
    public HashSet<Integer> getPosicions() {
        return posicions;
    }

    /**
     * Implementació de l'interfície Comparable per comparar nodes basats en la seva cota.
     *
     * @param o L'altre node a comparar.
     * @return Un valor negatiu si aquest node té una cota més petita, zero si les cotes són iguals, o un valor positiu
     * si aquest node té una cota més gran que l'altre node.
     */
    @Override
    public int compareTo(Nodo o) {
        return Integer.compare(this.cota, o.cota);
    }
}
