import static org.junit.Assert.*;

import edu.upc.prop.clusterxx.Grid;
import edu.upc.prop.clusterxx.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
public class GridTest {

    @Test
    public void testGridConstructorWithMatrix() {
        boolean[][] matrix = {
                {true, true, false},
                {false, true, true}
        };
        Grid grid = new Grid(1, matrix);

        assertEquals(1, grid.getID());
        assertEquals(4, grid.getSize());

        assertEquals(2, grid.getMaxSize().getX());
        assertEquals(3, grid.getMaxSize().getY());
        assertEquals(matrix.length, grid.getMaxSize().getX());
        assertEquals(matrix[0].length, grid.getMaxSize().getY());
    }

    @Test
    public void testGridConstructorWithArrayList() {
        ArrayList<Pair> positions = new ArrayList<>();
        positions.add(new Pair(1, 2));
        positions.add(new Pair(3, 4));
        Grid grid = new Grid(positions);

        assertEquals(4, grid.getMaxSize().getX());
        assertEquals(5, grid.getMaxSize().getY());
    }

    @Test
    public void testDistance() {
        ArrayList<Pair> positions = new ArrayList<>();
        positions.add(new Pair(1, 2));
        positions.add(new Pair(3, 4));
        Grid grid = new Grid(positions);

        double distance = grid.distance(0, 1);
        // (distància manhattan)
        assertEquals(4, distance, 0.0001);
    }
    @Test
    public void testToString() {
        ArrayList<Pair> positions = new ArrayList<>();
        positions.add(new Pair(1, 2));
        positions.add(new Pair(3, 4));
        Grid grid = new Grid(positions);

        String expected = "0 0 0 0 0 \n" +
                            "0 0 1 0 0 \n" +
                                "0 0 0 0 0 \n" +
                                    "0 0 0 0 1 \n";
        assertEquals(expected, grid.toString());
    }

    @Test
    public void testSetGrid() {
        ArrayList<Pair> positions = new ArrayList<>();
        positions.add(new Pair(1, 2));
        positions.add(new Pair(3, 4));
        Grid grid = new Grid();
        grid.setGrid(positions);

        assertEquals(positions, grid.getPositions());
        assertEquals(2, grid.size());
    }

}