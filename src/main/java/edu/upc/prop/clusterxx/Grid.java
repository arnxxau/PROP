package edu.upc.prop.clusterxx;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;


public class Grid {
    private int id;
    private Pair size;
    private ArrayList<Pair> grid = new ArrayList<Pair>();

    //pre: mat not null
    //post: Construiex l'arraylist de Posicions on l'usuari ha escollit que es poden posar lletres de l'alfabet.
    public Grid(int id, boolean[][] mat) { //crea un grid buit
        this.id = id;
        this.size = new Pair(mat.length,mat[0].length);
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
        Pair max = new Pair(-1,-1);
        for (Pair pair : p) {
            if (pair.x > max.x) max.x = pair.x;
            if (pair.y > max.y) max.y = pair.y;
            grid.add(pair);
        }

    }

    public Grid(){}

    public double distance(int i1,int i2)throws IndexOutOfBoundsException{
        return Math.sqrt(Math.pow(grid.get(i1).x-grid.get(i2).x,2)+Math.pow(grid.get(i1).y-grid.get(i2).y,2));
    }

    public ArrayList<Pair> getPositions() {
        return grid;
    }
    public Pair getMaxSize() { return size; };
    public int getSize() { return grid.size(); }
    public int getID() { return id; }
    public String toString() {
        String res = "";
        Integer[][] mat = new Integer[size.x][size.y];
        for (Pair p : grid) {
            mat[p.x][p.y] = 1;
        }
        for (int i = 0; i < size.x; ++i) {
            for (int j = 0; j < size.y; ++j) {
                if (mat[i][j] == null) {
                    res += "0 ";
                }
                else res += "1 ";
            }
            res += "\n";
        }
        return res;
    }

    //el puntero cambia
    public void setGrid(ArrayList<Pair> pares) {
        this.grid = pares;
    }
    public int size() { return grid.size(); }

}
