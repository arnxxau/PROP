package edu.upc.prop.clusterxx;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Nodo implements Comparable<Nodo> {
    private Map<Character, Integer> solucio;
    private HashSet<Integer> posicions;
    private HashSet<Character> simbols;
    private final int cota;

    public Nodo( Map<Character, Integer> sol, HashSet<Integer> pos, HashSet<Character> sim, int c){
        solucio = sol;

        posicions = pos;

        simbols = sim;

        cota = c;
    }
    public Map<Character, Integer> getSolucio() {
        return solucio;
    }

    public HashSet<Character> getSimbols() {
        return simbols;
    }

    public HashSet<Integer> getPosicions() {
        return posicions;
    }

    @Override
    public int compareTo(Nodo o) {
        return Double.compare(this.cota, o.cota);
    }
}



