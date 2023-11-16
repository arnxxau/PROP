package edu.upc.prop.clusterxx;

public abstract class CtrlPresentacioMantGen {

    protected String nomclase;
    protected VistaMantGen vmg;
    protected CtrlDominiMantGen cdmg;

    CtrlPresentacioMantGen(String nc,VistaMantGen vm, CtrlDominiMantGen cdg){
        nomclase = nc;
        vmg = vm;
        cdmg = cdg;
    }

    public abstract void manteniment();

    public abstract void consulta();

    public abstract void afegir();

    public abstract void esborrar();

}
