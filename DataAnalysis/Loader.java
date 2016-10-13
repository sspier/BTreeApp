/*
 * Read in 10 root pages and gather all links on each page
 For each page, create a HashTable of word, frequency pairs
 If the word doesn't exist in the Tree, insert it, otherwise
 add the word, frequency pair for that page to the appropriate word
 */
package DataAnalysis;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

class Loader {

    public static Graph graph;

    public static void main(String[] args) throws MalformedURLException, IOException {
        setUpFileStructure();
        graph = new Graph("http://en.wikipedia.org/wiki/Doug_Lea");
        
    }

    private static void setUpFileStructure() {
        File f = new File("nodes.data");
        if (f.exists()) {
            f.delete();
        }
        f = new File("values.data");
        if (f.exists()) {
            f.delete();
        }
        Path p = null;
        try {
            p = Paths.get("cache");
        } catch (InvalidPathException e) {
            System.out.println(e.toString());
        }
        if (p!= null && !Files.exists(p)) {
            new File("cache").mkdir();
            System.out.println("making directory for cache");
        }
    }

}
