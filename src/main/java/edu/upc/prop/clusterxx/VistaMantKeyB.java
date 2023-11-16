package edu.upc.prop.clusterxx;

public class VistaMantKeyB extends VistaMantGen {
    private void Visualitzaciopcions(){
        try{
            io.writeln(" Menu Funcionalitats Teclat ");
            io.writeln(" 0 - Sortir ");
            io.writeln(" 1 - Nou Teclat ");
            io.writeln(" 2 - Esborrar Teclat ");
            io.writeln(" 3 - Canviar Nom Teclat ");
            io.writeln(" 4 - Actualitzar Teclat ");}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public int obteniropcio() {
        NOPCIONS = 5;
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
