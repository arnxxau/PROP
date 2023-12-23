package edu.upc.prop.cluster125.domain;

/**
 * La classe LocalNodo representa un node utilitzat per a l'algorisme de clustering.
 * Cada node té una solució (una disposició de dades) representada com un array de caràcters
 * i una cota associada que ajuda a determinar la qualitat de la solució.
 */
public class LocalNodo {
    private final char[] solucio;
    private int cota;

    /**
     * Constructor de la classe LocalNodo.
     *
     * @param Sol Una solució representada com un array de caràcters.
     * @param Cot La cota associada a aquesta solució.
     */
    public LocalNodo(char[] Sol, int Cot){
        solucio=Sol;
        cota=Cot;
    }

    /**
     * Constructor de còpia de la classe LocalNodo.
     *
     * @param a L'objecte LocalNodo a partir del qual es crea una còpia.
     */
    public LocalNodo(LocalNodo a){
        solucio= new char[a.getSolucio().length];
        for(int i=0; i<a.getSolucio().length; i++){
            solucio[i]=a.getSolucio()[i];
        }
        cota = a.getCota();
    }

    /**
     * Estableix la cota d'aquest node a un valor determinat.
     *
     * @param cota La nova cota per a aquest node.
     */
    public void setCota(int cota) {
        this.cota = cota;
    }

    /**
     * Intercanvia dues posicions a l'array de solució.
     *
     * @param a La primera posició a intercanviar.
     * @param b La segona posició a intercanviar.
     */
    public void changePosition(int a, int b){
        char temp = solucio[a];
        solucio[a] = solucio[b];
        solucio[b]=temp;
    }

    /**
     * Obté la cota actual d'aquest node.
     *
     * @return La cota actual.
     */
    public int getCota() {
        return cota;
    }

    /**
     * Obté l'array de caràcters que representa la solució d'aquest node.
     *
     * @return L'array de caràcters que representa la solució.
     */
    public char[] getSolucio() {
        return solucio;
    }
}
