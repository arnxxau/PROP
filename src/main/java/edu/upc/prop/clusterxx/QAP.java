/**
 * La classe QAP representa un solucionador del Problema d'Assignació Quadràtica enfocat a un teclat (QAP, per les seves sigles en anglès).
 * Proporciona un mètode, {@link #QAPAlgorithm(Alphabet, Frequency, Grid)}, per resoldre el problema QAP.
 *
 * <p>El mètode QAPAlgorithm utilitza una cua de prioritat i un enfocament de cerca en l'espai de solucions per trobar una solució
 * òptima per al Problema d'Assignació Quadràtica basat en l'Alfabet, la Freqüència i la Matriu de Distàncies donats (grid).
 *
 * <p>Aquesta classe també conté mètodes d'utilitat per a diversos càlculs i algoritmes utilitzats
 * en el mètode QAPAlgorithm.
 *
 * @author Nil Allué
 * @version entrega 1.0
 * @see Alphabet
 * @see Frequency
 * @see Grid
 * @see Nodo
 */
package edu.upc.prop.clusterxx;

import java.util.*;


public class QAP {


    /**
     * Resol el Problema d'Assignació Quadràtica utilitzant una cua de prioritat i un enfocament voraç.
     *
     * @param alphabet L'Alfabet que conté els caràcters a assignar.
     * @param freq La Freqüència que conté les freqüències d'aparició dels caràcters.
     * @param grid La Matriu de Distàncies que representa la matriu de distàncies.
     * @return Un array de caràcters que representa la solució d'assignació òptima.
     */
    public static char[] QAPAlgorithm(Alphabet alphabet, Frequency freq, Grid grid) {
        Map<Character, Integer> Solucio = new HashMap<>();
        HashSet<Integer> posicions = new HashSet<>();//posicions no assignades
        HashSet<Character> simbols = alphabet.getCharacters();
        for(int i=0;i<grid.size();i++)posicions.add(i);
        PriorityQueue<Nodo> priorityQueue = new PriorityQueue<Nodo>();


        Map<Character, Integer> cota_assig = greedysol_cota(simbols, posicions, freq, grid);

        int cota = termino1(cota_assig,freq,grid);

        int c = calcCota(Solucio,simbols,posicions,freq,grid);

        Nodo inp = new Nodo(Solucio,posicions,simbols,c);
        priorityQueue.add(inp);

        Nodo actual;

        int cont = 0;

        while(!priorityQueue.isEmpty()){
            cont += 1;
            actual = priorityQueue.poll();

            if(actual.getPosicions().isEmpty() & calcCota(actual.getSolucio(),actual.getSimbols(),actual.getPosicions(),freq,grid) <= cota){
                return convert(actual.getSolucio());
            }
            for (Character simb : actual.getSimbols()) {
                for(Integer pos: actual.getPosicions()){
                    Map<Character, Integer> novaSol = new HashMap<>(actual.getSolucio());
                    novaSol.put(simb,pos);

                    HashSet<Character> novasimbols = new HashSet<>(actual.getSimbols());
                    novasimbols.remove(simb);

                    HashSet<Integer> novaposicions = new HashSet<>(actual.getPosicions());
                    novaposicions.remove(pos);
                    int cot = calcCota(novaSol,novasimbols,novaposicions,freq,grid);
                    if(cot<=cota){
                        Nodo nou = new Nodo(novaSol,novaposicions, novasimbols,cot);
                        priorityQueue.add(nou);
                    }
                }
            }
        }
        return convert(cota_assig);
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

    /**
     * Calcula una solució d'assignació voraç i el seu cost.
     *
     * @param simbols El conjunt de caràcters a assignar.
     * @param Posicions El conjunt de posicions encara no assignades.
     * @param freq La Freqüència que conté les freqüències d'aparició dels caràcters.
     * @param grid La Matriu de Distàncies que representa la matriu de distàncies.
     * @return Un mapa que representa la solució d'assignació voraç.
     */
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

    /**
     * Calcula la cota per a una solució donada, considerant els termes 1, 2 i 3 del Problema d'Assignació Quadràtica.
     *
     * @param Solucio    Mapa que conté la solució amb els caràcters i les seves assignacions a les posicions.
     * @param simbols    Conjunt de caràcters disponibles.
     * @param Posicions  Conjunt de posicions disponibles.
     * @param freq       Objecte que representa les freqüències entre els caràcters.
     * @param grid       Objecte que representa la matriu de distàncies entre les posicions.
     * @return La cota calculada per a la solució proporcionada.
     */
    private static int calcCota(Map<Character, Integer> Solucio, HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid){
        int termino1_ = termino1(Solucio,freq,grid);
        int termino2i3_ = termino2i3(Solucio, simbols, Posicions, freq, grid);
        return termino1_ + termino2i3_;
    }

    private static int termino1(Map<Character, Integer> Solucio, Frequency freq, Grid grid){
        int res=0;
        int cont=1;
        for (Map.Entry<Character, Integer> entry : Solucio.entrySet()) {
            Character clave = entry.getKey();
            Integer valor = entry.getValue();
            int ind=0;
            for (Map.Entry<Character, Integer> entry2 : Solucio.entrySet()) {
                Character clave2 = entry2.getKey();
                Integer valor2 = entry2.getValue();
                if(ind>=cont){
                    res += (int) (freq.getNumberOfAppearances(clave, clave2) * grid.distance(valor, valor2));
                }
                ind += 1;
            }
            cont += 1;
        }
        return res;
    }

    private static int termino2i3(Map<Character, Integer> Solucio, HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid) {

        int[][] Mat = new int[simbols.size()][Posicions.size()];

        Iterator<Character> iterator_simb = simbols.iterator();
        for (int i = 0; i < simbols.size(); i++) {
            char sim_act = iterator_simb.next();

            Iterator<Integer> iterator_pos = Posicions.iterator();
            for (int k = 0; k < Posicions.size(); k++) {
                int pos_act = iterator_pos.next();


                int total = 0;
                //calculo C1
                for (Map.Entry<Character, Integer> entry : Solucio.entrySet()) {
                    Character clave = entry.getKey();
                    Integer valor = entry.getValue();
                    total += (int) (freq.getNumberOfAppearances(clave, sim_act) * grid.distance(valor, pos_act));
                }
                Mat[i][k] = total;

                //calculo C2
                int[] T = new int[simbols.size() - 1];
                int[] D = new int[Posicions.size() - 1];



                //calculo vecto T y vector D
                //T
                int indx = 0;
                Iterator<Character> iter_sim_ext = simbols.iterator();
                char ext;
                while (iter_sim_ext.hasNext()) {
                    ext = iter_sim_ext.next();
                    if (ext != sim_act) {
                        T[indx] = (int) freq.getNumberOfAppearances(ext, sim_act);
                        indx += 1;
                    }
                }
                //D
                indx = 0;
                Iterator<Integer> iter_pos_ext = Posicions.iterator();
                int pos;
                while (iter_pos_ext.hasNext()) {
                    pos = iter_pos_ext.next();
                    if (pos != pos_act) {
                        D[indx] = grid.distance(pos, pos_act);
                        indx += 1;
                    }
                }

                Arrays.sort(T);

                Arrays.sort(D);
                invertirArray(D);


                total = 0;
                for (int h = 0; h < (T.length); h++) {
                    total += T[h] * D[h];
                }
                Mat[i][k] += total;
            }

        }




        int[][] test = new int[Mat.length][Mat.length];
        for(int i=0; i< Mat.length; i++){
            for(int l=0; l< Mat.length; l++){
                test[i][l]=Mat[i][l];
            }
        }
        return hungarianAlgorithm(Mat);
    }

    private static void invertirArray(int[] array) {
        int inicio = 0;
        int fin = array.length - 1;

        while (inicio < fin) {
            // Intercambiar los elementos en las posiciones inicio y fin
            int temp = array[inicio];
            array[inicio] = array[fin];
            array[fin] = temp;

            // Mover los índices hacia adentro
            inicio++;
            fin--;
        }
    }


    ////////////////////////////////////////////

    /**
     * Implementa l'algorisme "Hungarian" per resoldre el Problema d'Assignació Quadràtica.
     *
     * @param ini Matriu d'entrada que representa el pes entre els elements a assignar.
     * @return El total òptim després de l'assignació.
     */
    private static int hungarianAlgorithm(int[][] ini) {
        int[][] mat = new int[ini.length][ini.length];
        for(int i=0; i< ini.length; i++){
            for(int l=0; l< ini.length; l++){
                mat[i][l]=ini[i][l];
            }
        }

        int[] row = findMinElementsInRows(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int l = 0; l < mat.length; l++) {
                mat[i][l] -= row[i];
            }
        }
        int[] column = findMinElementsInColumns(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int l = 0; l < mat.length; l++) {
                mat[i][l] -= column[l];
            }
        }
        boolean[] zerorow = new boolean[mat.length];
        boolean[] zerocolumn = new boolean[mat.length];
        findrowcolumzero(mat, zerorow, zerocolumn);

        while (totalcover(zerorow, zerocolumn) < mat.length) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < mat.length; i++) {
                for (int l = 0; l < mat.length; l++) {
                    if (!zerorow[i] & !zerocolumn[l] & mat[i][l] < min) min = mat[i][l];
                }
            }
            for (int i = 0; i < mat.length; i++) {
                for (int l = 0; l < mat.length; l++) {
                    if (zerorow[i] & zerocolumn[l]) mat[i][l] = mat[i][l] + min;
                    else if (!zerorow[i] & !zerocolumn[l]) mat[i][l] = mat[i][l] - min;
                }
            }

            findrowcolumzero(mat, zerorow, zerocolumn);

        }
        //corretigir assignació <------------------------
        int [] rows = new int[mat.length];
        int [] occupiedCols = new int[mat.length];

        optimitzacio(rows, mat, occupiedCols);
        return getTotal(mat,rows,ini);

    }

    private static int getTotal(int[][] values, int[] rows, int[][] originalValues) {
        int total = 0;
        for (int row = 0; row < values.length; row++)
            total += originalValues[row][rows[row]];
        return total;
    }
    private static void optimitzacio(int[] rows, int[][] values, int [] occupiedCols){
        optimization(0, rows, values, occupiedCols);
    }
    private static boolean optimization(int row, int[] rows, int[][] values, int [] occupiedCols) {
        if (row == rows.length)
            return true;

        for (int col = 0; col < values.length; col++) {
            if (values[row][col] == 0 && occupiedCols[col] == 0) {
                rows[row] = col;
                occupiedCols[col] = 1;
                if (optimization(row + 1,rows, values, occupiedCols))
                    return true;
                occupiedCols[col] = 0;
            }
        }
        return false;
    }


    private static int totalcover(boolean[] zerorow, boolean[] zerocolumn) {
        int total = 0;
        for (int i = 0; i < zerorow.length; i++) if (zerorow[i]) total += 1;
        for (int i = 0; i < zerorow.length; i++) if (zerocolumn[i]) total += 1;
        return total;
    }

    private static void findrowcolumzero(int[][] mat, boolean[] row, boolean[] column) {
        int x;
        int y;
        //inicialitzar a zero
        for (int i = 0; i < row.length; i++) {
            row[i] = false;
            column[i] = false;
        }
        for (int i = 0; i < mat.length; i++) {
            for (int l = 0; l < mat.length; l++) {
                if (mat[i][l] == 0 & !row[i] & !column[l]) {
                    x = 0;
                    for (int v = 0; v < mat.length; v++) if (mat[i][v] == 0 ) x += 1; //&!column[v]
                    y = 0;
                    for (int v = 0; v < mat.length; v++) if (mat[v][l] == 0) y += 1; // & !row[v]

                    if (x >= y) row[i] = true;
                    else column[l] = true;
                }
            }
        }
    }


    private static int[] findMinElementsInRows(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;

        int[] minElements = new int[rows];
        for (int i = 0; i < rows; i++) {
            // Inicializar el mínimo con el primer elemento de la fila
            int min = matrix[i][0];

            // Buscar el mínimo en la fila
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }

            // Almacenar el mínimo de la fila en el arreglo
            minElements[i] = min;
        }
        return minElements;
    }

    private static int[] findMinElementsInColumns(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;

        int[] minElements = new int[cols];

        for (int j = 0; j < cols; j++) {
            // Inicializar el mínimo con el primer elemento de la columna
            int min = matrix[0][j];

            // Buscar el mínimo en la columna
            for (int i = 1; i < rows; i++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }

            //Almacenar el mínimo de la columna en el arreglo
            minElements[j] = min;
        }

        return minElements;
    }
    ////////////////////////////////////////////

}