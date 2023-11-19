package edu.upc.prop.clusterxx;

import java.util.Arrays;

public class hungarian {
    public static double hungarianAlgorithm(double[][] ini) {
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
        //corretigir assignació <------------------------
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
}

