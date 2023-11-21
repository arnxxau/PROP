package edu.upc.prop.clusterxx;

public class CtrlPresentacio {
    CtrlDomini cd;
    CtrlDominiMantGen cdma;//domini manteniment alfabet
    CtrlDominiMantGen cdmf;//domini manteniment freqüencies

    CtrlDominiMantGen cdmt;//domini manteniment freqüencies
    CtrlPresentacioMantGen cpma;//manteniment alfabet
    CtrlPresentacioMantGen cpmf;//manteniment freqüencies

    CtrlPresentacioMantGen cpmt;//manteniment freqüencies
    VistaMantFreq vmf;
    VistaMantAlphabet vma;

    VistaMantKeyB vmt;

    VistaMant vm;

    public CtrlPresentacio(){
        /*cd = new CtrlDomini();
        cdmf = cd.getCtrlDominiMantFreq();
        cdma = cd.getCtrlDominiMantAlph();*/
        vma = new VistaMantAlphabet();
        vmf = new VistaMantFreq();
        vmt = new VistaMantKeyB();
        /*cdma =
        cdmf=*/
    }
    public void iniciarCtrlDomini(){
    }
    private void iniciarmantenimentAlph(){
        cpma = new CtrlPresentacioMantAlph("Alfabet",vma,cdma);
        cpma.manteniment();
    }
    private void iniciarmantenimentFreq(){
        cpmf = new CtrlPresentacioMantFreq("Frequencia",vmf,cdmf);
        cpmf.manteniment();
    }
    private void iniciarmantenimentKeyB(){
        cpmt = new CtrlPresentacioMantKeyB("Teclat",vmt,cdmt);
        cpmt.manteniment();
    }

    public void iniciarmanteniment(){
        vm = new VistaMant();
        int opt = -1;
        while(opt!=0) {
            opt = vm.obteniropciomanteniment();
            switch (opt) {
                case 1:
                    iniciarmantenimentKeyB();
                    break;
                case 2:
                    iniciarmantenimentAlph();
                    break;
                 case 3:
                    iniciarmantenimentFreq();
                    break;
            }
        }
    }
}
