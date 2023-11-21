package edu.upc.prop.clusterxx;

public class CtrlPresentacioMantKeyB extends CtrlPresentacioMantGen {
    public CtrlPresentacioMantKeyB(String teclat, VistaMantGen vmt, CtrlDominiMantGen cdmt) {
        super(teclat,vmt,cdmt);
    }
    @Override
    public void manteniment() {
        int opt = -1;
        while (opt != 0) {
            opt = vmg.obteniropcio();
            switch (opt) {
                case 1:
                    System.out.println("hola2");
                    break;
                case 2:
                    System.out.println("adeu2");
                    break;
                case 3:
                    System.out.println("siuuu2");
                    break;
            }
        }
    }

    @Override
    public void consulta() {

    }

    @Override
    public void afegir() {

    }

    @Override
    public void esborrar() {

    }
}
