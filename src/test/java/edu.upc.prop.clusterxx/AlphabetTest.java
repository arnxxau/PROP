package edu.upc.prop.clusterxx;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
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
    public void testAddFrequencyWithMock() {
        // Create a mock for the Frequency class
        Frequency frequencyMock = mock(Frequency.class);

        // Set up the behavior of the mock
        when(frequencyMock.getTimestamp()).thenReturn(Instant.now());
        when(frequencyMock.getValue()).thenReturn(5);

        assertTrue(alphabet.addFrequency(frequencyMock));
        assertTrue(alphabet.getFrequencies().contains(frequencyMock));
    }

    @Test
    public void testDeleteFrequencyWithMock() {
        // Create a mock for the Frequency class
        Frequency frequencyMock = mock(Frequency.class);

        // Set up the behavior of the mock
        when(frequencyMock.getTimestamp()).thenReturn(Instant.now());
        when(frequencyMock.getValue()).thenReturn(5);

        alphabet.addFrequency(frequencyMock);

        assertTrue(alphabet.deleteFrequency(frequencyMock));
        assertFalse(alphabet.getFrequencies().contains(frequencyMock));

        // Verify that the deleteFrequency method was called with the correct parameter
        verify(alphabet).deleteFrequency(frequencyMock);
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

