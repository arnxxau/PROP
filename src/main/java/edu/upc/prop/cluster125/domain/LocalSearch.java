package edu.upc.prop.cluster125.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * La classe LocalSearch conté una implementació de l'algorisme Hill Climbing per a la resolució
 * de problemes de clustering.
 */
public class LocalSearch {

    /**
     * Aplica l'algorisme Hill Climbing per a la resolució d'un problema de clustering.
     *
     * @param alphabet L'alfabet de caràcters disponibles.
     * @param freq La matriu de freqüències de caràcters.
     * @param grid La matriu de distàncies entre caràcters.
     * @return La solució òptima trobada mitjançant Hill Climbing.
     */
    public static char[] HillClimbing(Alphabet alphabet, Frequency freq, Grid grid){
        char[] Sol_ini = SolIni_Basic(alphabet.getCharacters());
        LocalNodo Solucio = new LocalNodo(Sol_ini, Heuristic(Sol_ini, freq, grid));

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

    /**
     * Crea una llista de nodes successors a partir d'un node donat.
     *
     * @param sol_ini El node inicial.
     * @param freq La matriu de freqüències de caràcters.
     * @param grid La matriu de distàncies entre caràcters.
     * @return Una llista de nodes successors.
     */
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

    /**
     * Genera una solució inicial bàsica a partir d'un conjunt de caràcters.
     *
     * @param car El conjunt de caràcters disponibles.
     * @return Una solució inicial bàsica.
     */
    private static char[] SolIni_Basic(HashSet<Character> car){
        char[] Sol_ini = new char[car.size()];
        int idx=0;
        for (Character caracter : car) {
            Sol_ini[idx]=caracter;
            idx +=1;
        }
        return Sol_ini;
    }

    /**
     * Calcula la heurística d'una solució donada.
     *
     * @param Sol La solució de la qual es calcula la heurística.
     * @param freq La matriu de freqüències de caràcters.
     * @param grid La matriu de distàncies entre caràcters.
     * @return El valor de la heurística per la solució donada.
     */
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
