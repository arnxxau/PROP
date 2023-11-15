package edu.upc.prop.clusterxx;

public class VistaMantAlphabet {
    inout io = new inout();
    private static final int NOPCIONS = 4;
    VistaMantAlphabet(){
    }

    private void Visualitzaciopcions(){
        try{
        io.writeln(" Menu Funcionalitats alfabet ");
        io.writeln(" 0 - Sortir ");
        io.writeln(" 1 - Esborrar alfabet ");
        io.writeln(" 2 - Afegir Alfabet ");
        io.writeln(" 3 - Canviar nom Alfabet ");}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int obteniropcio() {
        int opt=-1;
        while(opt<0|| opt>NOPCIONS){
            Visualitzaciopcions();
            try {
                opt = io.readint();
            }catch (Exception e){System.out.println(e.getMessage());}

        }
        return opt;
    }
}
