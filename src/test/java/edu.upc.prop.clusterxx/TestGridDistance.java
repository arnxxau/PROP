package edu.upc.prop.clusterxx;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
public class TestGridDistance {

    Pair p1;
    Pair p2;
    Pair p3;
    Grid g;
    @Before public void setUp(){
        p1 = new Pair(2,2);
        p2 = new Pair(1,0);
        p3 = new Pair (1,1);
         g = new Grid();
        g.setGrid(new ArrayList<Pair>(Arrays.asList(p1,p2,p3)));
    }
    @Test
    public void dist1(){
        double d1=distance(p1,p2);
        double d2=0;
        try{
            d2 = g.distance(1,2);
        } catch (Exception e){
            System.out.println("Error: Els indexos del array van de 1 a size()");
            e.printStackTrace();
        }
        assertEquals(d1,d2,0.01);
    }
    @Test
    public void dist2(){

        double d1=distance(p2,p3);
        double d2=0;
        try {
            d2 = g.distance(2, 3);
        }catch (Exception e){
            System.out.println("Error: Els indexos del array van de 1 a size()");
            e.printStackTrace();
        }
        assertEquals(d1,d2,0.01);
    }
    @Test
    public void dist3(){
        double d1=distance(p2,p2);
        double d2=0;
        try {
            d2 = g.distance(2, 2);
        }catch (Exception e) {
            System.out.println("Error: Els indexos del array van de 1 a size()");
            e.printStackTrace();
        }
        assertEquals(d1,d2,0.01);
    }
    private double distance(Pair p1,Pair p2){
        return Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2)); //això dona resultat correcte 100%
    }
    @Test (expected=IndexOutOfBoundsException.class)
    public void OutOfBonds() {
        g.distance(4,3);
    }
}
