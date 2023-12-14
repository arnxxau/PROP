package edu.upc.prop.clusterxx;

public class LocalNodo {
    private char[] solucio;
    private int cota;

    public LocalNodo(char[] Sol, int Cot){
        solucio=Sol;
        cota=Cot;
    }
    public LocalNodo(LocalNodo a){
        solucio= new char[a.getSolucio().length];
        for(int i=0; i<a.getSolucio().length; i++){
            solucio[i]=a.getSolucio()[i];
        }
        cota = a.getCota();
    }

    public void setCota(int cota) {
        this.cota = cota;
    }

    public void changePosition(int a, int b){
        char temp = solucio[a];
        solucio[a] = solucio[b];
        solucio[b]=temp;
    }

    public int getCota() {
        return cota;
    }

    public char[] getSolucio() {
        return solucio;
    }

}
