package com.company;

import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CrawlerTest extends TestCase{

    public static String URL1 = "http://www.brightedge.com/";
    public static String URL2  = "http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster";
    public static String URL3 = "http://blog.rei.com/camp/how-to-introduce-your-indoorsy-friend-to-the-outdoors/";
    public static String URL4 = "http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/";

    protected void setUp() {

    }

    @Test
    public void test() {
        try {
            Document doc = Crawler.getPage(URL3);
            List<List<String>> parsed = Crawler.parsePage(doc);
            for (List<String> list : parsed) {
                for (String s : list) {
                    System.out.print(s + ",");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStopWords() {
        Set<String> set = Crawler.getStopWordsList();
        System.out.println(set.contains("you're"));
        assertEquals(true, set.contains("whereas"));
    }
}

