package edu.upc.prop.cluster125.domain;

import com.google.gson.annotations.Expose;

public class Pair {
    @Expose
    private int x;
    @Expose
    private int y;

    public Pair(){}
    public Pair(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
