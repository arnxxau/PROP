package edu.upc.prop.clusterxx;

import java.util.*;


public class Algorithm2 {

    public Algorithm2(){}

    public static char[] QAPAlgorithm(Alphabet alphabet, Frequency freq, Grid grid) {
        System.out.println("pito1\n");
        Map<Character, Integer> Solucio = new HashMap<>();
        HashSet<Integer> posicions = new HashSet<>();//posicions no assignades
        HashSet<Character> simbols = alphabet.getCharacters();
        for(int i=0;i<grid.size();i++)posicions.add(i);
        System.out.println("pito2\n");
        ////System.out.println("holi1\n");
        PriorityQueue<nodo> priorityQueue = new PriorityQueue<nodo>();
        boolean end=false;
        System.out.println("pito3\n");
        ////System.out.println("holi2\n");
        double cota = greedysol_cota(simbols, posicions, freq, grid);
        System.out.println("pito4\n");
        ////System.out.println("holi3\n");
        double c = calcCota(Solucio,simbols,posicions,freq,grid);
        System.out.println("pito5\n");

        ////System.out.println("holi4\n");
        nodo inp = new nodo(Solucio,posicions,simbols,c);
        ////System.out.println("holi5\n");
        priorityQueue.add(inp);

        ////System.out.println("holi6\n");
        nodo actual;
        ////System.out.println("adeu\n");

        while(!priorityQueue.isEmpty() & !end){
            actual = priorityQueue.poll();
            if(actual.posicions.isEmpty()){
                System.out.println(actual.Solucio.size()+"<----");
                return convert(actual.Solucio);
            }
            for (Character simb : actual.simbols) {
                for(Integer pos: actual.posicions){
                    Map<Character, Integer> novaSol = new HashMap<>(actual.Solucio);
                    novaSol.put(simb,pos);

                    HashSet<Character> novasimbols = new HashSet<>(actual.simbols);
                    novasimbols.remove(simb);

                    HashSet<Integer> novaposicions = new HashSet<>(actual.posicions);
                    novaposicions.remove(pos);
                    ////System.out.println("jj\n");
                    double cot = calcCota(novaSol,novasimbols,novaposicions,freq,grid);
                    ////System.out.println("jj1\n");

                    if(true){
                        nodo nou = new nodo(novaSol,novaposicions, novasimbols,cot);
                        priorityQueue.add(nou);
                    }
                }
            }

        }
        System.out.println("arriba anull\n");
        return new char[0];
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
    private static double greedysol_cota(HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid) {
        //System.out.println("-----\n");
        //System.out.println(simbols);
        //System.out.println(Posicions);

        System.out.println("albet\n");
        Map<Character, Integer> frecuencias = new HashMap<>();

        Iterator<Character> iterator = simbols.iterator();
        while (iterator.hasNext()) {
            System.out.println("albet1\n");
            char sim1 = iterator.next();
            System.out.println("albet2\n");
            Iterator<Character> iterator2 = simbols.iterator();
            System.out.println("albet3\n");
            int total=0;
            System.out.println("albet4\n");
            char sim2;
            System.out.println("albet5\n");
            while (iterator2.hasNext()) {
                System.out.println("albet6\n");
                sim2 = iterator2.next();
                System.out.println("albet7\n");

                if(sim1!=sim2){
                    System.out.println("albet8\n");
                    System.out.println("------------------\n");
                    System.out.println(sim1 + " " + sim2+ "\n");
                    System.out.println(freq.getAlphabet().getCharacters());
                    total += (int) freq.getNumberOfAppearances(sim1,sim2);
                    System.out.println("------------------\n");
                    System.out.println("albet9\n");
                }

            }
            frecuencias.put(sim1,total);
            System.out.println("albet10\n");
        }
        List<Map.Entry<Character, Integer>> listaOrdenada = new ArrayList<>(frecuencias.entrySet());
        listaOrdenada.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        //System.out.println(listaOrdenada);


        System.out.println("alb1\n");
        Map<Integer,Double> num_dist = new TreeMap<>();
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
            num_dist.put(pos1,totalDist);
        }
        System.out.println("alb2\n");

        List<Map.Entry<Integer, Double>> listaOrdenada1 = new ArrayList<>(num_dist.entrySet());
        listaOrdenada1.sort(Map.Entry.comparingByValue());

        //System.out.println(listaOrdenada1);


        Map<Character, Integer> assig_greedy = new HashMap<>();

        for(int i=0; i<listaOrdenada.size();i++){
            assig_greedy.put(listaOrdenada.get(i).getKey(),listaOrdenada1.get(i).getKey());
        }
        System.out.println("alb3\n");

        //System.out.println(convert(assig_greedy));



        return termino1(assig_greedy,freq,grid);
    }

    private static double calcCota(Map<Character, Integer> Solucio, HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid){
        ////System.out.println("oli\n");
        double termino1_ = termino1(Solucio,freq,grid);
        ////System.out.println("oli1\n");
        double termino2i3_ = termino2i3(Solucio, simbols, Posicions, freq, grid);
        ////System.out.println("oli2\n");
        return termino1_ + termino2i3_;
    }

    private static double termino1(Map<Character, Integer> Solucio, Frequency freq, Grid grid){
        double res=0;
        int cont=1;
        for (Map.Entry<Character, Integer> entry : Solucio.entrySet()) {
            Character clave = entry.getKey();
            Integer valor = entry.getValue();
            int ind=0;
            for (Map.Entry<Character, Integer> entry2 : Solucio.entrySet()) {
                Character clave2 = entry2.getKey();
                Integer valor2 = entry2.getValue();
                if(ind>=cont){
                    res += freq.getNumberOfAppearances(clave, clave2) * grid.distance(valor, valor2);
                }
                ind += 1;
            }
            cont += 1;
        }
        return res;
    }

    private static double termino2i3(Map<Character, Integer> Solucio, HashSet<Character> simbols, HashSet<Integer> Posicions, Frequency freq, Grid grid) {
        double[][] Mat = new double[simbols.size()][Posicions.size()];

        ////System.out.println("li\n");

        Iterator<Character> iterator_simb = simbols.iterator();
        for (int i = 0; i < simbols.size(); i++) {
            ////System.out.println("li1\n");
            char sim_act = iterator_simb.next();

            Iterator<Integer> iterator_pos = Posicions.iterator();
            for (int k = 0; k < Posicions.size(); k++) {
                ////System.out.println("li2\n");
                int pos_act = iterator_pos.next();


                double total = 0;
                //calculo C1
                for (Map.Entry<Character, Integer> entry : Solucio.entrySet()) {
                    ////System.out.println("li3\n");
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
                    ////System.out.println("li4\n");
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
                    ////System.out.println("li5\n");
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
                for (int h = 0; h < (T.length); h++) {
                    ////System.out.println("li6\n");
                    total += T[h] * D[h];
                    ////System.out.println("li6finish\n");
                }
                ////System.out.println("li6.6\n");
                Mat[i][k] += total;
                ////System.out.println("li6.7\n");
            }
            ////System.out.println("li7\n");

        }
        ////System.out.println("li8\n");

        return hungarianAlgorithm(Mat);
    }

    private static void invertirArray(double[] array) {
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
    public static double hungarianAlgorithm(double[][] ini) {
        double[][] mat = new double[ini.length][ini.length];

        for(int i=0; i< ini.length; i++){
            for(int l=0; l< ini.length; l++){
                mat[i][l]=ini[i][l];
            }
        }
        ////System.out.println("hah\n");

        double[] row = findMinElementsInRows(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int l = 0; l < mat.length; l++) {
                mat[i][l] -= row[i];
            }
        }
        ////System.out.println("hah1\n");
        double[] column = findMinElementsInColumns(mat);
        for (int i = 0; i < mat.length; i++) {
            for (int l = 0; l < mat.length; l++) {
                mat[i][l] -= column[l];
            }
        }
        ////System.out.println("hah2\n");
        boolean[] zerorow = new boolean[mat.length];
        boolean[] zerocolumn = new boolean[mat.length];
        findrowcolumzero(mat, zerorow, zerocolumn);
        ////System.out.println("hah3\n");

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
        ////System.out.println("hah4\n");
        //corretigir assignació <------------------------
        int [] rows = new int[mat.length];
        int [] occupiedCols = new int[mat.length];

        optimitzacio(rows, mat, occupiedCols);
        ////System.out.println("hah5\n");


        return getTotal(mat,rows,ini);

    }

    private static double getTotal(double[][] values, int[] rows, double[][] originalValues) {
        double total = 0;
        for (int row = 0; row < values.length; row++)
            total += originalValues[row][rows[row]];
        return total;
    }
    private static boolean optimitzacio(int[] rows, double[][] values, int [] occupiedCols){
        return optimization(0, rows, values, occupiedCols);
    }

    private static boolean optimization(int row, int[] rows, double[][] values, int [] occupiedCols) {
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
        int cols = matrix.length;

        double[] minElements = new double[rows];
        ////System.out.println("fmeR finished\n");
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
        ////System.out.println("fmeR finished\n");
        return minElements;
    }

    private static double[] findMinElementsInColumns(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix.length;

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
