package edu.upc.prop.clusterxx;

public class ExisteixID_Exception extends Exception {

    ExisteixID_Exception(String s){
        super(s);//String de excepcion para diferenciar entre varias del mismo tipo en una funcion
    }
    ExisteixID_Exception(){
        super();
    }
    public String Ja_Existeix(String ID){
        return "Error de ID: Ja Existeix la ID " + ID;
    }
    public String No_Existeix(String ID){
        return "Error de ID: No Existeix la ID " + ID;
    }
}




