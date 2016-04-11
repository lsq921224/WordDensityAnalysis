package com.company;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class Crawler {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String CHAR_SET = "UTF-8";
    private static final int TIME_OUT = 30000;
    private static final int STATUS_SUCCESS = 200;
    private static final InputStream STOP_WORDS_STREAM = Crawler.class.getResourceAsStream("/stopWords.txt");
    private static Set<String> STOP_WORDS;

    static {
        try {
            STOP_WORDS = buildStopWords();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getStopWordsList() {
        return STOP_WORDS;
    }

    /**
     * Given a url, get the DOM document using Jsoup
     * @param url
     * @return A DOM document of given URL
     * @throws IOException
     */
    public static Document getPage(String url) throws IOException{
        Connection con = Jsoup.connect(url).timeout(TIME_OUT).userAgent(USER_AGENT);
        Connection.Response response = con.execute();
        Document doc = null;
        if (response.statusCode() == STATUS_SUCCESS) {
            doc = Jsoup.parse(new String(response.bodyAsBytes(),CHAR_SET ));
        }
        return doc;
    }

    /**
     * Given a DOM document, return a list of string list
     * The list starts a new list of string when stopwords or punctuation is detected
     * @param doc
     * @return a list of a list of string
     */
    public static List<List<String>> parsePage(Document doc) {
        if (doc == null) return null;
        List<List<String>> parsed = new ArrayList<List<String>>();
        List<String> current = new ArrayList<String>();
        String text = doc.text();
        String[] textArray = text.split(Util.WHITE_SPACE);
        for (int i = 0; i < textArray.length; i++) {
            String word = textArray[i];
            if (STOP_WORDS.contains(word.toLowerCase()) ) {
                if (current.size() > 0) {
                    parsed.add(new ArrayList<String>(current));
                    current.clear();
                }
            } else if ( Util.endsWithPunc(word)) {
                word = Util.parseWord(word.substring(0, word.length()));
                if (word != null) current.add(word);
                parsed.add(new ArrayList<String>(current));
                current.clear();
            } else {
                word = Util.parseWord(word);
                if (word != null)  current.add(word);
            }
        }
        return parsed;
    }

    private static Set<String> buildStopWords() throws FileNotFoundException, URISyntaxException {
        Set<String> STOP_WORDS = new HashSet<String>();
        Scanner scanner = new Scanner(STOP_WORDS_STREAM);
        while (scanner.hasNextLine()) {
            STOP_WORDS.add(scanner.nextLine());
        }
        return STOP_WORDS;
    }
}
