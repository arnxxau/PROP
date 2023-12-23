/**
 * La classe Frequency representa la freqüència de combinacions de caràcters en un context donat.
 * Es pot inicialitzar amb dades de freqüència en brut o amb dades de text, i proporciona mètodes
 * per actualitzar, fusionar amb una altra freqüència i imprimir la informació de freqüència.
 *
 * @author Arnau Granados
 * @version entrega 1.0
 */

package edu.upc.prop.cluster125.domain;

import com.google.gson.annotations.Expose;
import edu.upc.prop.cluster125.exceptions.CaractersfromFreq_notInAlph_Exception;
import edu.upc.prop.cluster125.exceptions.alphNotCompatible_Exception;
import edu.upc.prop.cluster125.exceptions.badExtraction_Exception;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;

public class Frequency {
    // Main data
    @Expose
    private Integer frequencyWeight;
    @Expose
    private HashMap<Character, HashMap<Character, Integer>> freq;
    // Read modes
    @Expose
    private int mode;
    @Expose
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());
    /**
     * Constant que representa el mode d'extracció de freqüències, utilitzat
     * quan es construeix la instància de la classe Frequency amb dades de freqüències en brut.
     */
    final static int FREQ_MODE = 0;
    /**
     * Constant que representa el mode d'extracció de freqüències, utilitzat
     * quan es construeix la instància de la classe Frequency amb dades de freqüències de text.
     */
    final static int TEXT_MODE = 1;

    // Insertion modes
    private final static int REPLACE = 3, ADD = 4;

    // General info
    @Expose
    private String name;
    @Expose
    private final String creationDate;
    @Expose
    private String lastModifiedTime;
    @Expose
    private Alphabet alphabet;


    // constructors

    /**
     * Construeix un objecte Frequency basat en els paràmetres proporcionats.
     *
     * @param name El nom de la freqüència.
     * @param lines Les línies de dades que contenen informació sobre la freqüència.
     * @param mode El mode que indica si les dades estan en forma de freqüències o de text. (FREQ_MODE o TEXT_MODE)
     * @param alphabet L'alfabet associat amb la freqüència.
     * @throws CaractersfromFreq_notInAlph_Exception Si els caràcters de freqüència no estan en l'alfabet.
     * @throws badExtraction_Exception Si hi ha un error durant l'extracció de les freqüències.
     */
    public Frequency(String name, String[] lines, int mode, Alphabet alphabet) throws CaractersfromFreq_notInAlph_Exception, badExtraction_Exception {
        this.mode = mode;
        this.name = name;
        this.alphabet = alphabet;
        freq = new HashMap<>();
        frequencyWeight = 0;
        if (mode == FREQ_MODE) extractRawFrequencies(lines);
        else if (mode == TEXT_MODE) extractTextFrequencies(lines);

        creationDate = formatter.format(Instant.now());
        lastModifiedTime = formatter.format(Instant.now());
    }

    /**
     * Crea una nova instància de freqüència a partir d'una freqüència existent, realitzant una còpia de les seves dades.
     *
     * @param f L'objecte Frequency existent del qual es realitzarà una còpia.
     */
    public Frequency(Frequency f) {
        this.mode = f.mode;
        this.name = f.name;
        this.alphabet = f.alphabet;
        this.freq = new HashMap<>(f.freq);
        this.frequencyWeight = f.frequencyWeight;

        this.creationDate = f.creationDate;
        this.lastModifiedTime = f.lastModifiedTime;
    }


    // extractors

    /**
     * Extreu les freqüències en brut dels continguts de les línies proporcionades.
     *
     * <p>La estructura del fitxer esperada és:</p>
     * <pre>
     *     a c 11
     *     a v 15
     *     a x 99
     * </pre>
     *
     * @param lines Les línies que contenen la informació de freqüències.
     * @throws Exception Si el fitxer conté caràcters que no estan presents a l'alfabet de la freqüència.
     */
    private void extractRawFrequencies(String[] lines) throws CaractersfromFreq_notInAlph_Exception, badExtraction_Exception {
        try {
            for (int i = 0; i < lines.length; ++i) {
                String[] line = lines[i].split(" ");
                Character first = line[0].charAt(0);
                Character second = line[1].charAt(0);
                checkFreqIntegrity(first);
                checkFreqIntegrity(second);

                String number = line[2].strip();
                int d_freq = Integer.parseInt(number);

                frequencyWeight += d_freq;

                insertNewFreq(first, second, d_freq, REPLACE);
            }
        } catch (CaractersfromFreq_notInAlph_Exception ch) {
            throw new CaractersfromFreq_notInAlph_Exception();
        } catch (Exception e) {
            throw new badExtraction_Exception();
        }


    }

    /**
     * Extreu les freqüències de text de les línies proporcionades.
     *
     * @param lines Línies de text amb el mateix alfabet.
     * @throws Exception Si el fitxer conté caràcters que no estan presents a l'alfabet de la freqüència.
     */
    private void extractTextFrequencies(String[] lines) throws CaractersfromFreq_notInAlph_Exception {
        for (String s1 : lines) {
            String[] line = s1.split(" ");

            for (String s2 : line) {
                processWord(s2);
                frequencyWeight += s2.length();
            }
        }

    }

    // utils

    /**
     * Processa una paraula per actualitzar les freqüències de caràcters adjacents.
     *
     * @param w La paraula a processar.
     * @throws Exception Si la paraula caràcters que no estan presents a l'alfabet de la freqüència.
     */
    private void processWord(String w) throws CaractersfromFreq_notInAlph_Exception {
        for (int i = 0; i < w.length() - 1; ++i) {
            checkFreqIntegrity(w.charAt(i));
            checkFreqIntegrity(w.charAt(i + 1));
            insertNewFreq(w.charAt(i), w.charAt(i + 1), 1, ADD);
            insertNewFreq(w.charAt(i + 1), w.charAt(i), 1, ADD);
        }
    }


    /**
     * Insereix o reemplaça una freqüència de caràcters adjacents en el mapa de freqüències.
     *
     * @param first     El primer caràcter de la combinació.
     * @param second    El segon caràcter de la combinació.
     * @param frequency La freqüència a inserir o reemplaçar.
     * @param mode      El mode que indica si és una inserció o un reemplaçament.
     */
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

    /**
     * Modifica la freqüència amb les dades proporcionades en les línies especificades i l'estableix en el mode especificat.
     *
     * @param lines Un array de cadenes de caràcters que conté les dades de la freqüència a modificar.
     * @param mode El mode en què es vol establir la freqüència (FREQ_MODE o TEXT_MODE).
     * @throws CaractersfromFreq_notInAlph_Exception Si es troben caràcters a les dades de freqüència que no pertanyen a l'alfabet associat.
     * @throws badExtraction_Exception Si hi ha un problema en l'extracció de les dades de freqüència.
     */
    public void modifyFrequency(String[] lines, int mode) throws CaractersfromFreq_notInAlph_Exception, badExtraction_Exception {
        frequencyWeight = 0;
        freq = new HashMap<>();
        this.mode = mode;
        if (mode == FREQ_MODE) extractRawFrequencies(lines);
        else if (mode == TEXT_MODE) extractTextFrequencies(lines);
        lastModifiedTime = formatter.format(Instant.now());
    }

    /**
     * Fusiona la freqüència actual amb una altra freqüència, actualitzant la informació de freqüència.
     *
     * @param f L'objecte Frequency amb el qual fusionar.
     * @throws alphNotCompatible_Exception Si els alfabets de les freqüències no coincideixen.
     */
    public void fusion(Frequency f) throws alphNotCompatible_Exception {
        if (f.alphabet != this.alphabet)
            throw new alphNotCompatible_Exception();

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


    // integrity checkers

    /**
     * Verifica la integritat de la freqüència assegurant-se que tots els caràcters pertanyen a l'alfabet associat.
     *
     * @param c El caràcter a verificar.
     * @throws CaractersfromFreq_notInAlph_Exception Si el caràcter no pertany a l'alfabet associat a la freqüència.
     */
    private void checkFreqIntegrity(Character c) throws CaractersfromFreq_notInAlph_Exception {
        if (!alphabet.existsCharacter(c))
            throw new CaractersfromFreq_notInAlph_Exception();
    }

    // public getters

    /**
     * Obté el mode de la freqüència, indicant si es troba en mode de freqüència o text.
     *
     * @return El mode de la freqüència.
     */
    public int getMode() {
        return mode;
    }


    /**
     * Obté el nom de la freqüència.
     *
     * @return El nom de la freqüència.
     */
    public String getName() {
        return name;
    }

    /**
     * Obté la data de creació de la freqüència.
     *
     * @return La data de creació.
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Obté el temps de l'última modificació de la freqüència.
     *
     * @return El temps de l'última modificació.
     */
    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Obté l'alfabet associat amb la freqüència.
     *
     * @return L'alfabet.
     */
    public Alphabet getAlphabet() {
        return alphabet;
    }

    /**
     * Obté el nombre d'aparicions d'una combinació de caràcters a la freqüència.
     *
     * @param first  El primer caràcter de la combinació.
     * @param second El segon caràcter de la combinació.
     * @return El nombre d'aparicions de la combinació de caràcters.
     */
    public double getNumberOfAppearances(Character first, Character second) {
        if (freq.containsKey(first)) {
            HashMap<Character, Integer> innerHash = freq.get(first);
            if (innerHash.containsKey(second)) {
                return innerHash.get(second);
            }
        }
        return 0;
    }


    // public setters

    /**
     * Estableix el nom de la freqüència al valor especificat.
     *
     * @param name El nou nom de la freqüència.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Estableix l'alfabet associat amb la freqüència a l'alfabet especificat.
     *
     * @param alphabet El nou alfabet.
     */
    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }


}
