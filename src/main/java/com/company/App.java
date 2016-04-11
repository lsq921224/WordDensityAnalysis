package com.company;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class App {

    private Page page;
    private static String DEFAULT_URL = "http://www.brightedge.com/";

    public App() {
        this.page = new Page();
    }

    public Page getPage() {
        return page;
    }

    /**
     * Run the application with given url
     * @param url
     */
    public void run(String url) {
        try {
            Document doc = Crawler.getPage(url);
            List<List<String>> lists = Crawler.parsePage(doc);
            page.buildPage(lists);
            Map<String,Word> roots = page.getRoot().getChildren();
            Map<String,Integer> unique = new HashMap<String, Integer>();
            if (roots != null) {
                for (Map.Entry<String, Word> entry : roots.entrySet()) {
                    page.buildHeap(new StringBuilder(), entry.getValue(), unique);
                }
            }

            PriorityQueue<Map.Entry<String, Integer>> maxHeap = page.getMaxHeap();
            if (maxHeap != null) {
                for (int i = 0; i < maxHeap.size(); i++) {
                    Map.Entry<String, Integer> entry = maxHeap.poll();
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
            } else {
                System.out.println("Oops! No result!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String url;
        if (args.length == 0) {
            System.out.println("No URL input, using default URL: http://www.brightedge.com/");
            url = DEFAULT_URL;
        } else {
            url = args[0];
        }
        new App().run(url);
    }

}
