package edu.upc.prop.clusterxx;

public class VistaMant {
    protected inout io = new inout();

    static private final int NOPCIONES = 4;
    public VistaMant(){
    }

    private void Visualitzaciomenugeneral(){
        try{
        io.writeln(" Menu General ");
        io.writeln(" 0 - Sortir ");
        io.writeln(" 1 - Accedir a Manteniment de teclats ");
        io.writeln(" 2 - Accedir a Manteniment de alfabets ");
        io.writeln(" 3 - Accedir a Manteniment de frequencies ");}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int obteniropciomanteniment(){
        int opt=-1;
        while(opt<0 || opt>NOPCIONES){
            Visualitzaciomenugeneral();
            try {
                opt = io.readint();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        return opt;
    }

}
