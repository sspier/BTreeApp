/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAnalysis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Steven
 */
public class Graph {

    public ArrayList<Vertex> vertices = new ArrayList<>();
    public int max = 5;

    public Graph(String seed) throws IOException {
        ArrayDeque<String> urlsToExamine = new ArrayDeque<>();
        ArrayDeque<String> expiredUrls = new ArrayDeque<>();
        urlsToExamine.add(seed);
        while (vertices.size() < max) {
            if (!urlsToExamine.isEmpty()) {
                String thisUrl = urlsToExamine.poll();
                if (!expiredUrls.contains(thisUrl)) {
                    System.out.println("PAGE:: " + thisUrl);
                    vertices.add(new Vertex(thisUrl, grabLinks(thisUrl, urlsToExamine)));
                    expiredUrls.add(thisUrl);
                }
            }
        }
        saveUrls(getUrls());
    }

    private ArrayList<String> grabLinks(String url, ArrayDeque<String> q) throws IOException {
        ArrayList<String> edges = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        String domain = "http://en.wikipedia.org";
        for (Element link : links) {
            String thisUrl = domain + link.attr("href");
            if (validUrl(link)) {
                if (!q.contains(thisUrl)) {
                    q.add(thisUrl);
                }
                if (!edges.contains(thisUrl)) {
                    edges.add(thisUrl);
                }
            }
        }
        return edges;
    }

    private boolean validUrl(Element link) {
        String href = link.attr("href");
        if (href.contains("#") || href.contains("?") || href.contains(":")) {
            return false;
        } else if (link.text().isEmpty()) {
            return false;
        } else {
            return href.substring(0, 5).equals("/wiki");
        }
    }

    private void saveUrls(ArrayList<String> urls) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("urls.txt"));
        for (String u : urls) {
            out.write(u + " ");
        }
        out.close();
    }

    private ArrayList<String> getUrls() {
        ArrayList<String> urls = new ArrayList<>();
        for (Vertex v : vertices) {
            urls.add(v.url);
        }
        return urls;
    }

    
}
