package edu.upc.prop.clusterxx;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.HashSet;

public class FrequencyTest {

    @Test
    public void testGettersAndSetters() throws Exception {
        // Create a stub for the Alphabet class
        Alphabet alphabetStub = Mockito.mock(Alphabet.class);
        Mockito.when(alphabetStub.existsCharacter(Mockito.any(Character.class))).thenReturn(true);

        // Create a stub for the Frequency class
        Frequency frequencyStub = new Frequency("TestFreq", new String[]{"100","a b 5", "c d 10"}, Frequency.FREQ_MODE, alphabetStub);

        // Getter assertions
        assertEquals("TestFreq", frequencyStub.getName());
        assertEquals(Frequency.FREQ_MODE, frequencyStub.getMode());
        assertEquals(alphabetStub, frequencyStub.getAlphabet());
        assertEquals(Instant.class, frequencyStub.getCreationDate().getClass());
        assertEquals(Instant.class, frequencyStub.getLastModifiedTime().getClass());

        // Setter assertions
        frequencyStub.setName("NewName");
        assertEquals("NewName", frequencyStub.getName());

        frequencyStub.setMode(Frequency.TEXT_MODE);
        assertEquals(Frequency.TEXT_MODE, frequencyStub.getMode());

        Instant newTime = Instant.now();
        frequencyStub.setLastModifiedTime(newTime);
        assertEquals(newTime, frequencyStub.getLastModifiedTime());

    }

    @Test(expected = Exception.class)
    public void testNonCompatibleFreq() throws Exception {
        // Create a stub for the Alphabet class
        Alphabet alphabetStub = Mockito.mock(Alphabet.class);
        Mockito.when(alphabetStub.existsCharacter(Mockito.any(Character.class))).thenReturn(true);

        // Create a stub for the Frequency class
        Frequency frequencyStub = new Frequency("TestFreq", new String[]{"100","a b 5", "c d 10"}, Frequency.FREQ_MODE, alphabetStub);

        assertEquals(5.0, frequencyStub.getNumberOfAppearances('a', 'x'), 0.001);
    }

    @Test
    public void extractFreqRaw() throws Exception {
        // Create a stub for the Alphabet class
        Alphabet alphabetStub = Mockito.mock(Alphabet.class);
        Mockito.when(alphabetStub.existsCharacter(Mockito.any(Character.class))).thenReturn(true);

        // Create a stub for the Frequency class
        Frequency frequencyStub = new Frequency("TestFreq", new String[]{"100","a b 5", "c d 10"}, Frequency.FREQ_MODE, alphabetStub);

        assertEquals(5.0, frequencyStub.getNumberOfAppearances('a', 'b'), 0.001);
        assertEquals(0.05, frequencyStub.getFrequency('a', 'b'), 0.001);
    }


    @Test
    public void extractFreqText() throws Exception {
        // Create a stub for the Alphabet class
        Alphabet alphabetStub = Mockito.mock(Alphabet.class);
        Mockito.when(alphabetStub.existsCharacter(Mockito.any(Character.class))).thenReturn(true);

        // Create a stub for the Frequency class
        Frequency frequencyStub = new Frequency("TestFreq", new String[]{"ab ab ab ab ab", "cd cd cd cd cd cd"}, Frequency.TEXT_MODE, alphabetStub);

        assertEquals(5.0, frequencyStub.getNumberOfAppearances('a', 'b'), 0.001);
        assertEquals(0.227, frequencyStub.getFrequency('a', 'b'), 0.001);
    }


    @Test
    public void updateFrequency() throws Exception {
        // Create a stub for the Alphabet class
        Alphabet alphabetStub = Mockito.mock(Alphabet.class);
        Mockito.when(alphabetStub.existsCharacter(Mockito.any(Character.class))).thenReturn(true);

        // Create a stub for the Frequency class
        Frequency frequencyStub = new Frequency("TestFreq", new String[]{"ab ab ab ab ab", "cd cd cd cd cd cd"}, Frequency.TEXT_MODE, alphabetStub);

        assertEquals(5.0, frequencyStub.getNumberOfAppearances('a', 'b'), 0.001);
        assertEquals(0.05, frequencyStub.getFrequency('a', 'b'), 0.001);

        frequencyStub.updateFrequency(new String[]{"ab ab ab ab ab ab", "cd cd cd cd cd cd"});
        assertEquals(5.0, frequencyStub.getNumberOfAppearances('a', 'b'), 0.001);
    }

    @Test
    public void fusion() throws Exception {
        // Create a stub for the Alphabet class
        Alphabet alphabetStub = Mockito.mock(Alphabet.class);
        Mockito.when(alphabetStub.existsCharacter(Mockito.any(Character.class))).thenReturn(true);

        HashSet<Character> expectedCharacters = new HashSet<>();
        expectedCharacters.add('a');
        expectedCharacters.add('b');
        expectedCharacters.add('c');
        expectedCharacters.add('d');
        expectedCharacters.add('x');
        expectedCharacters.add('k');

        Mockito.when(alphabetStub.getCharacters()).thenReturn(expectedCharacters);

        // Create a stub for the Frequency class
        Frequency frequencyStub1 = new Frequency("TestFreq", new String[]{"50","a b 5", "c d 10"}, Frequency.FREQ_MODE, alphabetStub);
        Frequency frequencyStub2 = new Frequency("TestFreq", new String[]{"50","a b 5", "c d 10", "x k 50"}, Frequency.FREQ_MODE, alphabetStub);

        //assertEquals(10.0, frequencyStub1.getNumberOfAppearances('a', 'b'), 0.001);

        frequencyStub1.fusion(frequencyStub2);
        assertEquals(10.0, frequencyStub1.getNumberOfAppearances('a', 'b'), 0.001);
        assertEquals(0.1, frequencyStub1.getFrequency('a', 'b'), 0.001);
        assertEquals(50.0, frequencyStub1.getNumberOfAppearances('x', 'k'), 0.001);
    }
}