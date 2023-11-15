package edu.upc.prop.clusterxx;

public class CtrlPresentacio {
    CtrlDomini cd;
    CtrlDominiMantFreq cdmf;
    CtrlDominiMantAlph cdma;

    CtrlPresentacioMantFreq cpmf;
    CtrlPresentacioMantAlph cpma;
    VistaMantFreq vmf;
    VistaMantAlphabet vma;

    VistaMant vm;

    public CtrlPresentacio(){
        /*cd = new CtrlDomini();
        cdmf = cd.getCtrlDominiMantFreq();
        cdma = cd.getCtrlDominiMantAlph();*/
    }
    public void iniciarCtrlDomini(){

    }

    public void iniciarmanteniment(){
        cpmf= new CtrlPresentacioMantFreq();//passar cdmf com a paràmetre
        cpma = new CtrlPresentacioMantAlph(cdma);
        vm = new VistaMant();
        int opt = -1;
        while(opt!=0) {
            opt = vm.obteniropciomanteniment();
            switch (opt) {
                case 1:
                    break;
                case 2:
                    cpma.manteniment_alfabet();
                    break;
                /* case 3:
                    cpmf.manteniment_freq();
                    break;*/
            }
        }
    }
}
