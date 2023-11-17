package edu.upc.prop.clusterxx;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;


public class Grid {
    private ArrayList<Pair> grid = new ArrayList<Pair>();

    //pre: mat not null
    //post: Construiex l'arraylist de Posicions on l'usuari ha escollit que es poden posar lletres de l'alfabet.
    public Grid(boolean[][] mat) { //crea un grid buit
        for(int i=0;i< mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j]){
                    Pair par = new Pair(i,j);
                    grid.add(par);
                }
            }
        }
    }
    //pre: p not null
    //post: construeix l'objecte Grid copiant p en grid.
    public Grid(ArrayList<Pair> p){
        for (Pair pair : p) grid.add(pair);
    }

    public Grid(){}

    public double distance(int i1,int i2)throws IndexOutOfBoundsException{
        return Math.sqrt(Math.pow(grid.get(i1).x-grid.get(i2).x,2)+Math.pow(grid.get(i1).y-grid.get(i2).y,2));
    }

    public ArrayList<Pair> getGrid() {
        return grid;
    }

    //el puntero cambia
    public void setGrid(ArrayList<Pair> pares) {
        this.grid = pares;
    }

}
