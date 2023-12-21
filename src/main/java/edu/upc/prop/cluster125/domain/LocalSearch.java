package edu.upc.prop.cluster125.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LocalSearch {
    public static char[] HillClimbing(Alphabet alphabet, Frequency freq, Grid grid){
        //char[] Sol_ini = new char[alphabet.size()];
        char[] Sol_ini = SolIni_Basic(alphabet.getCharacters());
        LocalNodo Solucio = new LocalNodo(Sol_ini,Heuristic(Sol_ini,freq,grid));

        boolean end = true;
        while(end){
            List<LocalNodo> hijos = crearHijos(Solucio, freq, grid);
            end = false;
            for (int i = 0; i < hijos.size(); i++) {
                LocalNodo hijo = hijos.get(i);
                if(hijo.getCota() < Solucio.getCota()) {
                    Solucio = hijo;
                    end=true;
                }
            }
        }

        return Solucio.getSolucio();
    }

    private static List<LocalNodo> crearHijos(LocalNodo sol_ini, Frequency freq, Grid grid){
        List<LocalNodo> ret = new ArrayList<>();
        for (int i = 0; i<sol_ini.getSolucio().length; i++){
            for(int l = i + 1; l<sol_ini.getSolucio().length; l++){
                LocalNodo a = new LocalNodo(sol_ini);
                a.changePosition(i,l);
                a.setCota(Heuristic(a.getSolucio(), freq, grid));
                ret.add(a);
            }
        }
        return ret;
    }

    private static char[] SolIni_Basic(HashSet<Character> car){
        char[] Sol_ini = new char[car.size()];
        // Agrega elementos al HashSet
        int idx=0;
        for (Character caracter : car) {
            Sol_ini[idx]=caracter;
            idx +=1;
        }

        return Sol_ini;
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
