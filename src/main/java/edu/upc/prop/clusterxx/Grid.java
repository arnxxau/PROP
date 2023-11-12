package edu.upc.prop.clusterxx;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Grid {
    private ArrayList<Pair> pares = new ArrayList<Pair>();

    //post: Construiex l'arraylist de Posicions on l'usuari ha escollit que es poden posar lletres de l'alfabet.
    public Grid(boolean[][] mat) { //crea un grid buit
        for(int i=0;i< mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j]){
                    Pair par = new Pair(i,j);
                    pares.add(par);
                }
            }
        }
    }
    public Grid(){}
    public double distance(Pair p1,Pair p2){
        return Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2));
    }

    public ArrayList<Pair> getPares() {
        return pares;
    }

    public void setPares(ArrayList<Pair> pares) {
        this.pares = pares;
    }

}
