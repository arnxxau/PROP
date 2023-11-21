package edu.upc.prop.clusterxx;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KeyboardTest {
    private Keyboard keyboard;
    private Alphabet alphabetMock;
    private Frequency frequencyMock;
    private Grid gridMock;

    @Before
    public void setUp() {
        // Create mocks for dependencies
        alphabetMock = Mockito.mock(Alphabet.class);
        frequencyMock = Mockito.mock(Frequency.class);
        gridMock = Mockito.mock(Grid.class);

        // Create the object under test with stubs
        keyboard = new Keyboard("TestKeyboard", alphabetMock, frequencyMock, gridMock);
    }

    @Test
    public void testUpdate() {
        // Perform the update
        keyboard.update();

        // Verify that the distribucio is null after the update
        assertNull(keyboard.getDistribucio());
    }

    @Test
    public void testGetLayout() {
        // Stub the necessary methods for the test
        Mockito.when(gridMock.getMaxSize()).thenReturn(new Pair(3, 3));
        Mockito.when(gridMock.getPositions()).thenReturn(new ArrayList<Pair>(List.of(new Pair(0, 0))));

        // Perform the test
        String layout = keyboard.getLayout();

        // Verify the result (you need to adjust this based on your implementation)
        assertEquals("a    \n      \n      \n", layout);
    }

    @Test
    public void testGetters() {
        // Verify that getters return the expected values
        assertEquals("TestKeyboard", keyboard.getName());
        assertNotNull(keyboard.getCrDate());
        assertNotNull(keyboard.getLastMod());
        assertArrayEquals(new char[]{'a', 'b', 'e'}, keyboard.getDistribucio());
        assertEquals(alphabetMock, keyboard.getAlphabet());
        assertEquals(frequencyMock, keyboard.getFrequency());
        assertEquals(gridMock, keyboard.getGrid());
    }

    @Test
    public void testSetters() {
        // Perform the setters
        keyboard.setNom("NewName");
        keyboard.setDistribucio(new char[]{'c', 'd', 'f'});
        Alphabet newAlphabetMock = Mockito.mock(Alphabet.class);
        keyboard.setAlph(newAlphabetMock);
        Frequency newFrequencyMock = Mockito.mock(Frequency.class);
        keyboard.setFreq(newFrequencyMock);
        Grid newGridMock = Mockito.mock(Grid.class);
        keyboard.setGrid(newGridMock);

        // Verify that the setters worked
        assertEquals("NewName", keyboard.getName());
        assertArrayEquals(new char[]{'c', 'd', 'f'}, keyboard.getDistribucio());
        assertEquals(newAlphabetMock, keyboard.getAlphabet());
        assertEquals(newFrequencyMock, keyboard.getFrequency());
        assertEquals(newGridMock, keyboard.getGrid());
    }
}