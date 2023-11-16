package edu.upc.prop.clusterxx;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.time.Instant;
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
    public void testExistsCharacter() {
        assertTrue(alphabet.existsCharacter('a'));
        assertFalse(alphabet.existsCharacter('c'));
    }

    @Test
    public void testAddCharacter() {
        assertTrue(alphabet.addCharacter('c'));
        assertTrue(alphabet.existsCharacter('c'));
        assertFalse(alphabet.addCharacter('a')); // Duplicate character should not be added
    }

    @Test
    public void testDeleteCharacter() {
        assertTrue(alphabet.deleteCharacter('a'));
        assertFalse(alphabet.existsCharacter('a'));
        assertFalse(alphabet.deleteCharacter('c')); // Non-existing character should not be deleted
    }

    @Test
    public void testAddFrequency() {
        Frequency frequency = new Frequency();
        assertTrue(alphabet.addFrequency(frequency));
        assertTrue(alphabet.getFrequencies().contains(frequency));
    }

    @Test
    public void testDeleteFrequency() {
        Frequency frequency = new Frequency();
        alphabet.addFrequency(frequency);
        assertTrue(alphabet.deleteFrequency(frequency));
        assertFalse(alphabet.getFrequencies().contains(frequency));
        assertFalse(alphabet.deleteFrequency(new Frequency())); // Non-existing frequency should not be deleted
    }

    @Test
    public void testGetters() {
        assertEquals("TestAlphabet", alphabet.getName());
        assertNotNull(alphabet.getCrDate());
        assertNotNull(alphabet.getUltMod());
        assertEquals(2, alphabet.getCharacters().size());
        assertEquals(0, alphabet.getFrequencies().size());
    }

    @Test
    public void testSetName() {
        alphabet.setName("NewName");
        assertEquals("NewName", alphabet.getName());
    }
}

