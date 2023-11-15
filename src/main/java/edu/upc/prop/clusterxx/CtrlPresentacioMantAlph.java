package edu.upc.prop.clusterxx;

public class CtrlPresentacioMantAlph {
    private VistaMantAlphabet vma;
    private CtrlDominiMantAlph cdma;

    CtrlPresentacioMantAlph(CtrlDominiMantAlph cdma1) {
        vma = new VistaMantAlphabet();
        cdma = cdma1;
    }

    public void manteniment_alfabet() {
        int opt = -1;
        while (opt != 0) {
            opt = vma.obteniropcio();
            switch (opt) {
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
            }
        }
    }
}
