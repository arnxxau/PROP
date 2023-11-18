package edu.upc.prop.clusterxx;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;

public class AlphabetTest {
    private Alphabet alphabet;

    @Before
    public void setUp() {
        HashSet<Character> characters = new HashSet<>();
        characters.add('a');
        characters.add('b');
        alphabet = new Alphabet("TestAlphabet", characters);
    }

    @Test
    public void existsCharacter() {
        assertTrue(alphabet.existsCharacter('a'));
        assertFalse(alphabet.existsCharacter('c'));
    }

    @Test
    public void addCharacter() {
        assertTrue(alphabet.addCharacter('c'));
        assertTrue(alphabet.existsCharacter('c'));
        assertFalse(alphabet.addCharacter('a')); // Duplicate character should not be added
    }

    @Test
    public void deleteCharacter() {
        assertTrue(alphabet.deleteCharacter('a'));
        assertFalse(alphabet.existsCharacter('a'));
        assertFalse(alphabet.deleteCharacter('c')); // Non-existing character should not be deleted
    }

    @Test
    public void addFrequency() {
        Frequency frequencyStub = Mockito.mock(Frequency.class);

        Mockito.when(frequencyStub.getName()).thenReturn("name0");

        assertTrue(alphabet.addFrequency(frequencyStub));
        assertTrue(alphabet.getFrequencies().containsValue(frequencyStub));
    }

    @Test
    public void deleteFrequency() {
        Frequency frequencyStub = Mockito.mock(Frequency.class);

        Mockito.when(frequencyStub.getName()).thenReturn("name0");

        assertTrue(alphabet.addFrequency(frequencyStub));
        assertTrue(alphabet.deleteFrequency(frequencyStub));
        assertFalse(alphabet.getFrequencies().containsValue(frequencyStub));

    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("TestAlphabet", alphabet.getName());
        assertNotNull(alphabet.getCrDate());
        assertNotNull(alphabet.getLastMod());
        assertEquals(2, alphabet.getCharacters().size());
        assertEquals(0, alphabet.getFrequencies().size());

        alphabet.setName("NewName");
        assertEquals("NewName", alphabet.getName());
    }
}

