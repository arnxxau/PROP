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
        HashSet<Integer> posicions = new HashSet<>();
        for(int i=0;i<grid.size();i++)posicions.add(i);
        char[] Sol_ini = convert(greedysol_cota(alphabet.getCharacters(),posicions,freq, grid));
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

    private static char[] convert(Map<Character, Integer> Solucio){
        char[] sol = new char[Solucio.size()];
        List<Map.Entry<Character, Integer>> listaOrdenada = new ArrayList<>(Solucio.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue());
        for (int i = 0; i < listaOrdenada.size(); i++) {
            sol[i] = listaOrdenada.get(i).getKey();
        }
        return sol;
    }

    private static Map<Character, Integer> greedysol_cota(HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid) {
        Map<Character, Integer> frecuencias = new HashMap<>();

        Iterator<Character> iterator = simbols.iterator();
        while (iterator.hasNext()) {
            char sim1 = iterator.next();
            Iterator<Character> iterator2 = simbols.iterator();
            int total=0;
            char sim2;
            while (iterator2.hasNext()) {
                sim2 = iterator2.next();

                if(sim1!=sim2){
                    total += (int) freq.getNumberOfAppearances(sim1,sim2);
                }

            }
            frecuencias.put(sim1,total);
        }
        List<Map.Entry<Character, Integer>> listaOrdenada = new ArrayList<>(frecuencias.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));


        Map<Integer, Integer> num_dist = new TreeMap<>();
        Iterator<Integer> iteratorPos = Posicions.iterator();
        while (iteratorPos.hasNext()) {
            int pos1 = iteratorPos.next();
            Iterator<Integer> iteratorPos2 = Posicions.iterator();
            int totalDist=0;
            int pos2;

            while(iteratorPos2.hasNext()) {
                pos2 = iteratorPos2.next();

                if(pos1!=pos2){
                    totalDist += grid.distance(pos1,pos2);
                }
            }
            num_dist.put(pos1,totalDist);
        }

        List<Map.Entry<Integer, Integer>> listaOrdenada1 = new ArrayList<>(num_dist.entrySet());
        listaOrdenada1.sort(Map.Entry.comparingByValue());



        Map<Character, Integer> assig_greedy = new HashMap<>();

        for(int i=0; i<listaOrdenada.size();i++){
            assig_greedy.put(listaOrdenada.get(i).getKey(),listaOrdenada1.get(i).getKey());
        }

        return assig_greedy;
    }
}
