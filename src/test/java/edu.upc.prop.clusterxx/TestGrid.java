package edu.upc.prop.clusterxx;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestGrid {

    ArrayList<Pair> l1; //plena
    ArrayList<Pair> l2; //mig
    ArrayList<Pair> l3; //buida
    @Before public void setUp(){
        l1 = new ArrayList<>(Arrays.asList(new Pair(0,0),new Pair(0,1),
                new Pair(0,2),new Pair(1,0),new Pair(1,1),new Pair(1,2),
                new Pair(2,0),new Pair(2,1),new Pair(2,2)));
        l2 = new ArrayList<>(Arrays.asList(new Pair(0,0),
                new Pair(0,2),new Pair(1,1),
                new Pair(2,0),new Pair(2,2)));
        l3 = new ArrayList<>();
    }
    @Test
    public void Testonstructor1(){
        Grid g2 = new Grid();//constructora buida
        g2.setGrid(l1);//setter senzill (no comprobaré si funciona)

        boolean[][] mat = new boolean[3][3];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[i].length; j++){
                mat[i][j]=true; //tota la matriu a true
            }
        }
        Grid g1 = new Grid(mat);//constructora per veure si funciona

        assertEquals(g1,g2);
    }
    @Test
    public void Testconstructor2(){
        Grid g2 = new Grid();//constructora buida
        g2.setGrid(l2);//setter senzill (no comprobaré si funciona)

        boolean[][] mat = new boolean[3][3];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[i].length; j++){
                if(j+i%2==0)mat[i][j]=true; //parell a true
                else mat[i][j]=false;
            }
        }
        Grid g1 = new Grid(mat);//constructora per veure si funciona

        assertEquals(g1,g2);
    }
    @Test
    public void Testconstructor3(){
        Grid g2 = new Grid();//constructora buida
        g2.setGrid(l3);//setter senzill (no comprobaré si funciona)

        boolean[][] mat = new boolean[3][3];
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[i].length; j++){
                mat[i][j]=false; //tota la matriu a false
            }
        }
        Grid g1 = new Grid(mat);//constructora per veure si funciona

        assertEquals(g1,g2);
    }
    public void Test2ndconstructor(){
        Grid g2 = new Grid();//constructora buida
        g2.setGrid(l3);//setter senzill (no comprobaré si funciona)

        Grid g1 = new Grid(l3);//constructora per veure si funciona

        assertEquals(g1,g2);
    }
}
