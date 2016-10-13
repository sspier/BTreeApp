/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAnalysis;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author travis
 */
public class Vertex {

    String url;
    ArrayList<String> edges;
    ArrayList<Integer> weights;
    static int max = 0;

    public Vertex(String u, ArrayList<String> edges) throws IOException {
        url = u;
        this.edges = edges;
        weights = weightMeDown();
    }

    private ArrayList<Integer> weightMeDown() throws IOException {
        ArrayList<Integer> theWeights = new ArrayList<>();
        HashTable vertex = new HashTable(url);
        for (String e : edges) {
            theWeights.add(vertex.compare(new HashTable(e)));
        }
        return theWeights;
    }

    @Override
    public String toString() {
        String output = "";
        for (String s : edges) {
            output += "\nedge::" + s;
        }
        return output;
    }

//    public void addWeights(Cache cache) throws MalformedURLException, IOException {
////        int[] matches = new int[edges.length + 1];
////        String[] urlsCopy = edges;
//        for (String e : edges) {
//            ByteBuffer b = cache.get(e);
//            if (b == null) {
//                HashTable table = new HashTable(e);
//                getTheMatches(table, weights);
//                cache.add(e, placeWordsInBuffer(table));
//                int sizeOfBuffer = (4 + e.length()) + 4 + (weights.length * 8);
//                b = ByteBuffer.allocate(sizeOfBuffer);
//                b.putInt(weights.length - 1).putInt(e.length()).put(e.getBytes());
//                for (int i = 0; i < weights.length; i++) {
//                    b.putInt(i).putInt(weights[i]);
//                }
//                System.out.println("Added to cache >> ");
//            } else {
//                b.getInt();
//                int length = b.getInt();
//                b.position(length + 8);
//                while (b.hasRemaining()) {
//                    weights[b.getInt()] = b.getInt();
//                }
//                System.out.println("Found in cache >> ");
//            }
//        }
//    }
}
