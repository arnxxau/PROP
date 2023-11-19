package edu.upc.prop.clusterxx;

import java.util.*;


public class Algorithm2 {


    public Map<Character, Integer> QAPAlgorithm(Alphabet alphabet, Frequency freq, Grid grid) {


        Map<Character, Integer> Solucio = new HashMap<>();
        HashSet<Integer> posicions = new HashSet<>();//posicions no assignades
        HashSet<Character> simbols = alphabet.getCharacters();
        for(int i=0;i<grid.size();i++)posicions.add(i);

        PriorityQueue<nodo> priorityQueue = new PriorityQueue<nodo>();
        boolean end=false;

        double cota = greedysol_cota(simbols, posicions, freq, grid);

        priorityQueue.add(new nodo(Solucio,posicions,simbols,calcCota(Solucio,simbols,posicions,freq,grid)));


        while(!priorityQueue.isEmpty() & !end){
            nodo actual = priorityQueue.poll();
            if(actual.posicions.isEmpty()){
                return actual.Solucio;
            }
            for (Character simb : actual.simbols) {
                for(Integer pos: actual.posicions){
                    Map<Character, Integer> novaSol = new HashMap<>(actual.Solucio);
                    novaSol.put(simb,pos);

                    HashSet<Character> novasimbols = new HashSet<>(actual.simbols);
                    novasimbols.remove(simb);

                    HashSet<Integer> novaposicions = new HashSet<>(actual.posicions);
                    novaposicions.remove(pos);

                    double cot = calcCota(novaSol,novasimbols,novaposicions,freq,grid);

                    if(cot<=cota){
                        nodo nou = new nodo(novaSol,novaposicions, novasimbols,cot);
                    }
                }
            }

        }
        return new HashMap<>();
    }

    private double greedysol_cota(HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid) {

        Map<Integer, Character> num_freq = new TreeMap<>(Comparator.reverseOrder());

        Iterator<Character> iterator = simbols.iterator();
        while (iterator.hasNext()) {
            char sim1 = iterator.next();
            Iterator<Character> iterator2 = simbols.iterator();
            int total=0;
            char sim2;
            while (iterator2.hasNext()) {
                sim2 = iterator2.next();

                if(sim1!=sim2){
                    total += freq.getNumberOfAppearances(sim1,sim2);
                }

            }
            num_freq.put(total,sim1);
        }

        Map<Double, Integer> num_dist = new TreeMap<>();
        Iterator<Integer> iteratorPos = Posicions.iterator();
        while (iteratorPos.hasNext()) {
            int pos1 = iteratorPos.next();
            Iterator<Integer> iteratorPos2 = Posicions.iterator();
            double totalDist=0;
            int pos2;

            while(iteratorPos2.hasNext()) {
                pos2 = iteratorPos2.next();

                if(pos1!=pos2){
                    totalDist += grid.distance(pos1,pos2);
                }
            }
            num_dist.put(totalDist,pos1);
        }

        Map<Character, Integer> assig_greedy = new HashMap<>();

        Iterator<Map.Entry<Integer, Character>> iterator1 = num_freq.entrySet().iterator();
        Iterator<Map.Entry<Double, Integer>> iterator2 = num_dist.entrySet().iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            Map.Entry<Integer, Character> entry1 = iterator1.next();
            Map.Entry<Double, Integer> entry2 = iterator2.next();

            assig_greedy.put(entry1.getValue(),entry2.getValue());
        }

        return termino1(assig_greedy,freq,grid);
    }

    private double calcCota(Map<Character, Integer> Solucio, HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid){
        return (termino1(Solucio,freq,grid) + termino2i3(Solucio, simbols, Posicions, freq, grid));
    }

    private double termino1(Map<Character, Integer> Solucio, Frequency freq, Grid grid){
        double res=0;
        int cont=1;
        for (Map.Entry<Character, Integer> entry : Solucio.entrySet()) {
            Character clave = entry.getKey();
            Integer valor = entry.getValue();
            int ind=0;
            for (Map.Entry<Character, Integer> entry2 : Solucio.entrySet()) {
                Character clave2 = entry.getKey();
                Integer valor2 = entry.getValue();
                if(ind>=cont){
                    res += freq.getNumberOfAppearances(clave, clave2) * grid.distance(valor, valor2);
                }
                ind += 1;
            }
            cont += 1;
        }
        return res;
    }

    private double termino2i3(Map<Character, Integer> Solucio, HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid) {
        double[][] Mat = new double[simbols.size()][Posicions.size()];

        Iterator<Character> iterator_simb = simbols.iterator();
        for (int i = 0; i < simbols.size(); i++) {
            char sim_act = iterator_simb.next();

            Iterator<Integer> iterator_pos = Posicions.iterator();
            for (int k = 0; k < Posicions.size(); k++) {
                int pos_act = iterator_pos.next();


                double total = 0;
                //calculo C1
                for (Map.Entry<Character, Integer> entry : Solucio.entrySet()) {
                    Character clave = entry.getKey();
                    Integer valor = entry.getValue();
                    total += (freq.getNumberOfAppearances(clave, sim_act) * grid.distance(valor, pos_act));
                }
                Mat[i][k] = total;

                //calculo C2
                double[] T = new double[simbols.size() - 1];
                double[] D = new double[Posicions.size() - 1];


                //calculo vecto T y vector D
                //T
                int indx = 0;
                Iterator<Character> iter_sim_ext = simbols.iterator();
                char ext;
                while (iter_sim_ext.hasNext()) {
                    ext = iter_sim_ext.next();
                    if (ext != sim_act) {
                        T[indx] = freq.getNumberOfAppearances(ext, sim_act);
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
                    }
                }

                //Ordenar T Creixent
                Arrays.sort(T);

                //Ordenar D Decreixent
                Arrays.sort(D);
                invertirArray(D);


                //Producte escalar T*D
                total = 0;
                for (int h = 0; h < (T.length); i++) {
                    total += T[i] * D[i];
                }

                Mat[i][k] += total;
            }
        }
        return hungarianAlgorithm(Mat);
    }

    private void invertirArray(double[] array) {
        int inicio = 0;
        int fin = array.length - 1;

        while (inicio < fin) {
            // Intercambiar los elementos en las posiciones inicio y fin
            double temp = array[inicio];
            array[inicio] = array[fin];
            array[fin] = temp;

            // Mover los índices hacia adentro
            inicio++;
            fin--;
        }
    }


    ////////////////////////////////////////////
    private static double hungarianAlgorithm(double[][] ini) {
        double[][] mat = new double[ini.length][ini.length];

        for(int i=0; i< ini.length; i++){
            for(int l=0; l< ini.length; l++){
                mat[i][l]=ini[i][l];
            }
        }


        double[] row = findMinElementsInRows(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int l = 0; l < mat.length; l++) {
                mat[i][l] -= row[i];
            }
        }
        double[] column = findMinElementsInColumns(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int l = 0; l < mat.length; l++) {
                mat[i][l] -= column[l];
            }
        }
        boolean[] zerorow = new boolean[mat.length];
        boolean[] zerocolumn = new boolean[mat.length];
        findrowcolumzero(mat, zerorow, zerocolumn);

        while (totalcover(zerorow, zerocolumn) < mat.length) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < mat.length; i++) {
                for (int l = 0; l < mat.length; l++) {
                    if (!zerorow[i] & !zerocolumn[l] & mat[i][l] < min) min = mat[i][l];
                }
            }
            for (int i = 0; i < mat.length; i++) {
                for (int l = 0; l < mat.length; l++) {
                    if (zerorow[i] & zerocolumn[l]) mat[i][l] += min;
                    else if (!zerorow[i] & !zerocolumn[l]) mat[i][l] -= min;
                }
            }
            findrowcolumzero(mat, zerorow, zerocolumn);

        }

        int[] assigment = new int[mat.length];
        Arrays.fill(assigment, -1);
        int cont;
        int pos=0;
        while(checknegative1(assigment)) {
            for (int i = 0; i < mat.length; i++) {
                cont = 0;

                for (int l = 0; l < mat.length; l++) {
                    if (mat[i][l] == 0 & assigment[l] == -1) {
                        cont += 1;
                        pos = l;
                    }
                }
                if (cont == 1) assigment[pos] = i;
            }
        }

        double resultado=0;

        for(int i=0; i<assigment.length; i++){
            resultado += ini[assigment[i]][i];
        }

        return resultado;

    }

    private static boolean checknegative1(int[] assigment){
        for (int j : assigment) if (j == -1) return true;
        return false;
    }


    private static int totalcover(boolean[] zerorow, boolean[] zerocolumn) {
        int total = 0;
        for (int i = 0; i < zerorow.length; i++) if (zerorow[i]) total += 1;
        for (int i = 0; i < zerorow.length; i++) if (zerocolumn[i]) total += 1;
        return total;
    }

    private static void findrowcolumzero(double[][] mat, boolean[] row, boolean[] column) {
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
                    for (int v = 0; v < mat.length; v++) if (mat[i][v] == 0) x += 1;
                    y = 0;
                    for (int v = 0; v < mat.length; v++) if (mat[v][l] == 0) y += 1;

                    if (x >= y) row[i] = true;
                    else column[l] = true;
                }
            }
        }
    }


    private static double[] findMinElementsInRows(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[] minElements = new double[rows];

        for (int i = 0; i < rows; i++) {
            // Inicializar el mínimo con el primer elemento de la fila
            double min = matrix[i][0];

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

    private static double[] findMinElementsInColumns(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[] minElements = new double[cols];

        for (int j = 0; j < cols; j++) {
            // Inicializar el mínimo con el primer elemento de la columna
            double min = matrix[0][j];

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
