package edu.upc.prop.clusterxx;

import java.util.HashSet;
import java.util.Map;

public class LocalSearch {


    public static char[] HillClimbing(Alphabet alphabet, Frequency freq, Grid grid){
        char[] Sol_ini = new char[alphabet.size()];
        SolIni_Basic(Sol_ini,alphabet.getCharacters());


    }

    private static void SolIni_Basic(char[] Sol, HashSet<Character> car){

    }
    private static int Heuristic(char[] Sol, Frequency freq, Grid grid){
        int res=0;
        for(int i=0; i<Sol.length; i++){
            for(int l=i;l<Sol.length; l++){
                res += (int)freq.getNumberOfAppearances(Sol[i],Sol[l]) * grid.distance(i,l);
            }
        }
        return res;
    }
}
