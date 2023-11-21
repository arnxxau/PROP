package edu.upc.prop.clusterxx;

public class VistaMantFreq extends VistaMantGen{

    private void Visualitzaciopcions(){
        try{
            io.writeln(" Menu Funcionalitats Frequencia ");
            io.writeln(" 0 - Sortir ");
            io.writeln(" 1 - Afegir Frequencia ");
            io.writeln(" 2 - Esborrar Frequencia ");
            io.writeln(" 3 - Modificar Frequencia ");
            io.writeln(" 3 - Actualitzar Frequencia ");}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
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
