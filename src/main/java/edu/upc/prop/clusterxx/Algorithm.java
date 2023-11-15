package edu.upc.prop.clusterxx;

import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Algorithm{
    private static int hungarianAlgorithm(int[][] costMatrix) {
            int n = costMatrix.length;
            int[] assignment = new int[n];
            Arrays.fill(assignment, -1);

            for (int row = 0; row < n; row++) {
                boolean[] rowUsed = new boolean[n];
                boolean[] colUsed = new boolean[n];
                int pathLength = 0;
                int[] path = new int[n * 2];

                while (true) {
                    int minUncoveredValue = Integer.MAX_VALUE;
                    int minRow = -1, minCol = -1;

                    for (int i = 0; i < n; i++) {
                        if (!rowUsed[i]) {
                            for (int j = 0; j < n; j++) {
                                if (!colUsed[j]) {
                                    int value = costMatrix[i][j];
                                    if (value < minUncoveredValue) {
                                        minUncoveredValue = value;
                                        minRow = i;
                                        minCol = j;
                                    }
                                }
                            }
                        }
                    }

                    path[pathLength++] = minRow;
                    path[pathLength++] = minCol;
                    rowUsed[minRow] = true;
                    colUsed[minCol] = true;

                    boolean alternating = false;

                    for (int i = 0; i < pathLength && !alternating; i += 2) {
                        int row_ = path[i];
                        int col_ = path[i + 1];

                        for (int j = 0; j < n && !alternating; j++) {
                            if (!rowUsed[j] && colUsed[j] && costMatrix[row_][j] == 0) {
                                path[pathLength++] = j;
                                rowUsed[j] = true;
                                alternating = true;
                            }
                        }
                    }

                    if (!alternating) {
                        int minDelta = Integer.MAX_VALUE;

                        for (int i = 0; i < n; i++) {
                            if (rowUsed[i]) {
                                for (int j = 0; j < n; j++) {
                                    if (!colUsed[j]) {
                                        int delta = costMatrix[i][j];
                                        if (delta < minDelta) {
                                            minDelta = delta;
                                        }
                                    }
                                }
                            }
                        }

                        for (int i = 0; i < n; i++) {
                            if (rowUsed[i]) {
                                assignment[i] += minDelta;
                            }
                            if (colUsed[i]) {
                                assignment[i] -= minDelta;
                            }
                        }
                    }
                }

                int totalCost = 0;
                for (int i = 0; i < assignment.length; i++) {
                    int assignedTask = assignment[i];
                    if (assignedTask != -1) {
                        totalCost += costMatrix[i][assignedTask];
                    }
                }
                return totalCost;
            }
        }




    public Algorithm(){}

    //Pre: El grid i l'alphabet han de tenir el mateix numero de simbols/posicons
    //Pre: Les freq son del alphabet
    public ArrayList<pair<char,int>> Gilmore(Alphabet alphabet, Frequency frequency, Grid grid) {

        ArrayList<pair<char, int>> Solucio = new ArrayList<pair<char, int>>(); //Es guardaran els simbols amb posicio ja assignada (simbols,identificador de posicio)

        HashSet<char> Simbols = alphabet.getSimbols();

        HashSet<int> Posicions = grid.getPosicions();

        while (!Simbols.isEmpty()) {

            int size = Simbols.size();
            double[][] Mat = double[size][size];
            int cont = 0;
            char sim_act;
            int pos_act;

            Iterator<char> iterator_simb = Simbols.iteratior();
            for (int i = 0; i < size; i++) {
                sim_act = iterator_simb.next();

                Iterator<int> iterator_pos = Posicions.iteratior();
                for (int k = 0; k < size; k++) {
                    pos_act = iterator_pos.next();


                    double total = 0;
                    //calculo C1
                    for (pair<char, int> element : Solucio) {
                        total += (frequency.getFrequency(element.getKey(), sim_act) * grid.getDistance(element.getValue(), pos_act));
                    }
                    Mat[i][k] = total;

                    //calculo C2
                    double[] T = double[size - 1];
                    double[] D = double[size - 1];

                    //calculo vecto T y vector D
                    //T
                    int indx = 0;
                    Iterator<char> iter_sim_ext = Simbols.iteratior();
                    char ext;
                    while (iter_sim_ext.hasNext()) {
                        ext = iter_sim_ext.next();
                        if (ext != sim_act) {
                            T[indx] = frequency.getFrequency(ext, sim_act);
                            indx += 1;
                        }
                    }
                    //D
                    indx = 0;
                    Iterator<int> iter_pos_ext = Posicions.iteratior();
                    int pos;
                    while (iter_pos_ext.hasNext()) {
                        pos = iter_pos_ext.next();
                        if (pos != pos_act) {
                            D[indx] = grid.getDistance(pos, pos_act);
                        }
                    }

                    //Ordenar T Creixent
                    Arrays.sort(T);

                    //Ordenar D Decreixent
                    Arrays.sort(D, Comparator.reverseOrder());


                    //Producte escalar T*D
                    total = 0;
                    for (int i = 0; i < (size - 1); i++) {
                        total = T[i] * D[i];
                    }


                    Mat[i][k] += total;

                }
            }
            //buscar el Minim de Mat
            int i_max;
            int k_max;
            double max = Mat[0][0];
            for (int i = 0; i < Mat.length; i++) {
                for (int k = 0; k < Mat[0].length; k++) {
                    if (Mat[i][k] > max) {
                        i_max = i;
                        k_max = k;
                        max = Mat[i][k];
                    }
                }
            }

            //afegir minim a Solucio i eliminar minim
        }
    }


}