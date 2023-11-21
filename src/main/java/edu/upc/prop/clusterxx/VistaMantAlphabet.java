package edu.upc.prop.clusterxx;

public class VistaMantAlphabet extends VistaMantGen {

    VistaMantAlphabet(){
    }

    private void Visualitzaciopcions(){
        try{
        io.writeln(" Menu Funcionalitats Alfabet ");
        io.writeln(" 0 - Sortir ");
        io.writeln(" 1 - Esborrar Alfabet ");
        io.writeln(" 2 - Afegir Alfabet ");
        io.writeln(" 3 - Canviar nom Alfabet ");}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int obteniropcio() {
        NOPCIONS = 4;
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
