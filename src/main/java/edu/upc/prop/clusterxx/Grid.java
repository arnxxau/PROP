/**
 * La classe Grid representa una graella que indica les posicions on es poden posar els caràcters
 * dins d'una distribució.
 *
 * @author Iván Cobos
 * @version entrega 1.0
 */

package edu.upc.prop.clusterxx;

import java.util.ArrayList;


public class Grid {
    private int id;
    private Pair size;
    private ArrayList<Pair> grid = new ArrayList<Pair>();

    /**
     * Constructor de la classe Grid que crea una graella buida basada en una matriu booleana.
     *
     * @param id  Identificador de la graella.
     * @param mat Matriu booleana que indica les posicions on es poden posar lletres.
     */
    public Grid(int id, boolean[][] mat) { //crea un grid buit
        this.id = id;
        this.size = new Pair(mat.length,mat[0].length);
        for(int i=0;i< mat.length;i++){
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j]){
                    Pair par = new Pair(i,j);
                    grid.add(par);
                }
            }
        }
    }


    /**
     * Constructor de la classe Grid que crea una graella a partir d'una llista de parells.
     *
     * @param p Llista de parells que defineix les posicions de la graella.
     */
    public Grid(ArrayList<Pair> p){
        Pair max = new Pair(-1,-1);
        for (Pair pair : p) {
            if (pair.x > max.x) max.x = pair.x;
            if (pair.y > max.y) max.y = pair.y;
            grid.add(pair);
        }

    }

    /**
     * Constructor sense paràmetres de la classe Grid.
     */
    public Grid() {
    }

    /**
     * Calcula la distància entre dues posicions a la graella.
     *
     * @param i1 Índex de la primera posició.
     * @param i2 Índex de la segona posició.
     * @return La distància entre les dues posicions.
     * @throws IndexOutOfBoundsException Si un dels índexs està fora dels límits de la graella.
     */
    public double distance(int i1,int i2)throws IndexOutOfBoundsException{
        return Math.sqrt(Math.pow(grid.get(i1).x-grid.get(i2).x,2)+Math.pow(grid.get(i1).y-grid.get(i2).y,2));
    }

    /**
     * Obté la llista de posicions de la graella.
     *
     * @return La llista de posicions.
     */
    public ArrayList<Pair> getPositions() {
        return grid;
    }

    /**
     * Obté la mida màxima de la graella com un objecte Pair.
     *
     * @return L'objecte Pair que representa les dimensions màximes de la graella.
     */
    public Pair getMaxSize() {
        return size;
    }

    ;

    /**
     * Obté la mida de la graella, és a dir, el nombre de posicions.
     *
     * @return La mida de la graella.
     */
    public int getSize() {
        return grid.size();
    }

    /**
     * Obté l'identificador de la graella.
     *
     * @return L'identificador de la graella.
     */
    public int getID() {
        return id;
    }


    /**
     * Retorna una representació en cadena de la graella.
     *
     * @return Una cadena que representa la graella amb 0 i 1, indicant si una posició és ocupada o no.
     */
    public String toString() {
        String res = "";
        Integer[][] mat = new Integer[size.x][size.y];
        for (Pair p : grid) {
            mat[p.x][p.y] = 1;
        }
        for (int i = 0; i < size.x; ++i) {
            for (int j = 0; j < size.y; ++j) {
                if (mat[i][j] == null) {
                    res += "0 ";
                }
                else res += "1 ";
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Estableix la llista de posicions de la graella.
     *
     * @param pares La nova llista de posicions.
     */
    public void setGrid(ArrayList<Pair> pares) {
        this.grid = pares;
    }

    /**
     * Obté la mida de la graella, és a dir, el nombre de posicions.
     *
     * @return La mida de la graella.
     */
    public int size() { return grid.size(); }

}
