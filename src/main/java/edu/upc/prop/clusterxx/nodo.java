package edu.upc.prop.clusterxx;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class nodo implements Comparable<nodo> {
    public Map<Character, Integer> Solucio = new HashMap<>();
    public HashSet<Integer> posicions = new HashSet<>();
    public HashSet<Character> simbols = new HashSet<>();

    double cota;

    public nodo( Map<Character, Integer> Sol, HashSet<Integer> pos, HashSet<Character> sim, double c){
        Solucio = Sol;

        posicions = pos;

        simbols = sim;

        cota = c;
    }

    @Override
    public int compareTo(nodo o) {
        return Double.compare(this.cota, o.cota);
    }
}



