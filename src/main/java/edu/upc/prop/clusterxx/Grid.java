package edu.upc.prop.clusterxx;

import java.io.IOException;

public class Grid {
    private boolean[][] grid;//diu si hi pot haver o no un caracter.
    // Mostrar el teclado

    //pre: col es numero de columnes per cada fila, i sep la separacio desde l'inici en columnes de cada fila.
    //post: es crea un Grid buit demanant pasant les dades per paràmetre.
    public Grid(boolean[][] mat) { //crea un grid buit
        grid = new boolean[mat.length][mat[0].length];
        for(int i=0;i< mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                grid[i][j]=mat[i][j];
            }
        }
    }

}
