/*
 * Steven Spier
 */
package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class StopWords {

    private static volatile StopWords instance;
    private final Set<String> stopWords;

    private StopWords(File file) throws IOException {
        System.out.println("### Reading stopwords file: " + file.getAbsolutePath());
        Set<String> words = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String word : line.split(",")) {
                    if (!word.isBlank()) {
                        words.add(word.trim().toLowerCase());
                    }
                }
            }
        }
        this.stopWords = Collections.unmodifiableSet(words);
    }

    public static StopWords getInstance() throws IOException {
        return getInstance(new File("stopwords.txt"));
    }

    public static StopWords getInstance(File file) throws IOException {
        if (instance == null) {
            synchronized (StopWords.class) {
                if (instance == null) {
                    instance = new StopWords(file);
                }
            }
        }
        return instance;
    }

    public Set<String> get() {
        return stopWords;
    }

    public boolean contains(String word) {
        return stopWords.contains(word.toLowerCase());
    }
}
