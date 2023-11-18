package edu.upc.prop.clusterxx;

import java.time.Instant;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class Frequency {
    // main data
    private Integer frequencyWeight;
    private HashMap<Character, HashMap<Character, Integer>> freq;

    // read modes
    private int mode;
    final static int FREQ_MODE = 0;
    final static int TEXT_MODE = 1;
    // insertion modes
    private final static int REPLACE = 3, ADD = 4;

    // error messages
    private final static String EXTRACTION_ERROR = "An error occurred, the frequency couldn't be extracted";
    private final static String WRONG_FILE_PATH_ERROR = "Wrong file path.";

    private final static String ALPHABET_ERROR = "The alphabet is not compatible with this frequency.";
    private final static String NON_EXISTING_FREQUENCY = "The character combination doesn't exist.";

    // general info
    private String name;
    private final Instant creationDate;


    private Instant lastModifiedTime;
    private Alphabet alphabet;


    // constructors
    public Frequency(String name, String[] lines, int mode, Alphabet alphabet) throws Exception {
        this.mode = mode;
        this.name = name;
        this.alphabet = alphabet;
        freq = new HashMap<>();
        frequencyWeight = 0;
        if (mode == FREQ_MODE) extractRawFrequencies(lines);
        else if (mode == TEXT_MODE) extractTextFrequencies(lines);

        creationDate = Instant.now();
        lastModifiedTime = Instant.now();
    }


    // extractors

    /*
    file structure ->
    600
    a c 11
    a v 15
    a x 99
     */
    private void extractRawFrequencies(String[] lines) throws Exception {
        frequencyWeight = Integer.parseInt(lines[0]);
        for (int i = 1; i < lines.length; ++i) {
            String[] line = lines[i].split(" ");
            Character first = line[0].charAt(0);
            Character second = line[1].charAt(0);
            checkFreqIntegrity(first);
            checkFreqIntegrity(second);

            Integer d_freq = Integer.parseInt(line[2]);

            insertNewFreq(first, second, d_freq, REPLACE);
        }

    }

    private void extractTextFrequencies(String[] lines) throws Exception {
        for (String s1 : lines) {
            String[] line = s1.split(" ");

            for (String s2 : line) {
                processWord(s2);
                frequencyWeight += s2.length();
            }
        }

    }

    // utils
    private void processWord(String w) throws Exception {
        for (int i = 0; i < w.length() - 1; ++i) {
            checkFreqIntegrity(w.charAt(i));
            checkFreqIntegrity(w.charAt(i + 1));
            insertNewFreq(w.charAt(i), w.charAt(i + 1), 1, ADD);
            insertNewFreq(w.charAt(i + 1), w.charAt(i), 1, ADD);
        }
    }

    private void insertNewFreq(Character first, Character second, Integer frequency, int mode) {
        if (freq.containsKey(first)) {
            HashMap<Character, Integer> innerHashMap = freq.get(first);

            if (mode == REPLACE) innerHashMap.put(second, frequency);
            else if (mode == ADD) {
                if (innerHashMap.containsKey(second))
                    innerHashMap.put(second, innerHashMap.get(second) + frequency);
                else innerHashMap.put(second, frequency);
            }
        } else {
            HashMap<Character, Integer> innerHashMap = new HashMap<>();
            innerHashMap.put(second, frequency);
            freq.put(first, innerHashMap);
        }
    }

    public void updateFrequency(String[] lines) throws Exception {
        if (mode == FREQ_MODE) extractRawFrequencies(lines);
        else if (mode == TEXT_MODE) extractTextFrequencies(lines);
        lastModifiedTime = Instant.now();
    }

    public void fusion(Frequency f) throws Exception {
        if (f.alphabet != this.alphabet)
            throw new Exception(ALPHABET_ERROR);

        Iterator<Character> it1 = alphabet.getCharacters().iterator();
        Iterator<Character> it2 = alphabet.getCharacters().iterator();
        HashMap<Character, HashMap<Character, Integer>> freqcopy1 = new HashMap<>(freq);
        HashMap<Character, HashMap<Character, Integer>> freqcopy2 = new HashMap<>(f.freq);

        frequencyWeight += f.frequencyWeight;

        freq = new HashMap<>();

        while (it1.hasNext()) {
            Character c1 = it1.next();
            while (it2.hasNext()) {
                Character c2 = it2.next();

                if (freqcopy1.containsKey(c1) && freqcopy1.get(c1).containsKey(c2))
                    insertNewFreq(c1, c2, freqcopy1.get(c1).get(c2), ADD);
                if (freqcopy2.containsKey(c1) && freqcopy2.get(c1).containsKey(c2))
                    insertNewFreq(c1, c2, freqcopy2.get(c1).get(c2), ADD);
            }
            it2 = alphabet.getCharacters().iterator();
        }
    }

    public void printFrequencies() {
        System.out.println("Freq weight = " + frequencyWeight);
        for (Map.Entry<Character, HashMap<Character, Integer>> outerEntry : freq.entrySet()) {
            char outerKey = outerEntry.getKey();
            System.out.print(outerKey + ": {");

            HashMap<Character, Integer> innerMap = outerEntry.getValue();
            for (Map.Entry<Character, Integer> innerEntry : innerMap.entrySet()) {
                char innerKey = innerEntry.getKey();
                double innerValue = innerEntry.getValue();
                System.out.print(innerKey + "=" + innerValue + ", ");
            }

            if (!innerMap.isEmpty()) {
                System.out.print("\b\b");
            }

            System.out.println("}");
        }
    }


    // integrity checkers
    private void checkFreqIntegrity(Character c) throws Exception {
        if (!alphabet.existsCharacter(c))
            throw new Exception(ALPHABET_ERROR);
    }

    // public getters

    public Integer getFrequencyWeight() {
        return frequencyWeight;
    }

    public HashMap<Character, HashMap<Character, Integer>> getFreq() {
        return freq;
    }

    public int getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getLastModifiedTime() {
        return lastModifiedTime;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public double getNumberOfAppearances(Character first, Character second) {
        return (double) freq.get(first).get(second);
    }

    public double getFrequency(Character first, Character second) {
        return getNumberOfAppearances(first, second) / frequencyWeight;
    }


    // public (putos) setters
    public void setFrequencyWeight(Integer frequencyWeight) {
        this.frequencyWeight = frequencyWeight;
    }

    public void setFreq(HashMap<Character, HashMap<Character, Integer>> freq) {
        this.freq = freq;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastModifiedTime(Instant lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }


}
