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
        int [] rows = new int[mat.length];
        int [] occupiedCols = new int[mat.length];

        optimitzacio(rows, mat, occupiedCols);

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
        if (row == rows.length) // If all rows were assigned a cell
            return true;

        for (int col = 0; col < values.length; col++) { // Try all columns
            if (values[row][col] == 0 && occupiedCols[col] == 0) { // If the current cell at column `col` has a value of zero, and the column is not reserved by a previous row
                rows[row] = col; // Assign the current row the current column cell
                occupiedCols[col] = 1; // Mark the column as reserved
                if (optimization(row + 1,rows, values, occupiedCols)) // If the next rows were assigned successfully a cell from a unique column, return true
                    return true;
                occupiedCols[col] = 0; // If the next rows were not able to get a cell, go back and try for the previous rows another cell from another column
            }
        }
        return false; // If no cell were assigned for the current row, return false to go back one row to try to assign to it another cell from another column
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

