package edu.upc.prop.clusterxx;

public class CtrlPresentacioMantFreq extends CtrlPresentacioMantGen {
    CtrlPresentacioMantFreq(String nc, VistaMantGen vm, CtrlDominiMantGen cdg) {
        super(nc, vm, cdg);
    }

    @Override
    public void manteniment() {
        int opt = -1;
        while (opt != 0) {
            opt = vmg.obteniropcio();
            switch (opt) {
                case 1:
                    System.out.println("hola1");
                    break;
                case 2:
                    System.out.println("adeu1");
                    break;
                case 3:
                    System.out.println("siuuu1");
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
