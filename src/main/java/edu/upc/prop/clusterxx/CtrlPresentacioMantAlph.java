package edu.upc.prop.clusterxx;

public class CtrlPresentacioMantAlph extends CtrlPresentacioMantGen {


    CtrlPresentacioMantAlph(String nc, VistaMantGen vm, CtrlDominiMantGen cdg) {
        super(nc, vm, cdg);
    }

    @Override
    public void manteniment() {
        int opt = -1;
        while (opt != 0) {
            opt = vmg.obteniropcio();
            switch (opt) {
                case 1:
                    System.out.println("hola");
                    break;
                case 2:
                    System.out.println("adeu");
                    break;
                case 3:
                    System.out.println("siuuu");
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
