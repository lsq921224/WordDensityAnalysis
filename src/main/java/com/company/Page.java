package com.company;

import java.util.*;

public class Page {

    private final int INITIAL_CAP = 100;
    private Word root;
    private PriorityQueue<Map.Entry<String, Integer>> maxHeap;

    public Page() {
        root = new Word(null);
        maxHeap = new PriorityQueue<Map.Entry<String, Integer>>(INITIAL_CAP, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return -o1.getValue().compareTo(o2.getValue());
            }
        });
    }

    public PriorityQueue<Map.Entry<String, Integer>> getMaxHeap() {
        return maxHeap;
    }

    public Word getRoot() {
        return root;
    }

    /**
     * Add a word to the page
     * @param word
     */
    public void add(String word) {
        root.add(word);
    }

    /**
     * Add a list of words to the page
     * @param words
     */
    public void add(List<String> words) {
        if (words == null || words.size() == 0) return;
        for (int i = 0; i < words.size();) {
            String word = words.get(i);
            root.add(word);
            addChild(words, ++i, root.getChildren().get(word.toLowerCase()));
        }
    }

    /**
     * Recursively add words in the list to the page
     * @param words
     * @param idx
     * @param parent
     */
    public void addChild(List<String> words, int idx, Word parent) {
        if (idx == words.size() || parent == null) return;
        String word = words.get(idx);
        parent.add(word);
        addChild(words, idx + 1, parent.getChildren().get(word.toLowerCase()));
    }

    /**
     * Build page given a list of list of strings
     * @param list
     */
    public void buildPage(List<List<String>> list) {
        if (list == null || list.size() == 0) return;
        for (List<String> strings : list) {
            add(strings);
        }
    }

    /**
     * Recursively build a maxHeap by current page elements
     * Using a Map to remove potential duplicates
     * @param prefix
     * @param parent
     * @param unique
     */
    public void buildHeap(StringBuilder prefix, Word parent, Map<String, Integer> unique) {
        if (parent.getChildren() == null ) {
            prefix.setLength(0);
            return;
        }
        if (!unique.containsKey(parent.getVal()) || unique.get(parent.getVal()) < parent.getCount()) {
            maxHeap.offer(new AbstractMap.SimpleEntry(parent.getVal(), parent.getCount()));
            unique.put(parent.getVal(), parent.getCount());
        }

        for (Map.Entry<String, Word> entry : parent.getChildren().entrySet()) {
            int length = prefix.length();
            if (length > 0) prefix.append(Util.WHITE_SPACE);
            prefix.append(parent.getVal());
            if (!unique.containsKey(prefix.toString()) || unique.get(parent.getVal()) < parent.getCount()) {
                maxHeap.offer(new AbstractMap.SimpleEntry(prefix.toString(), parent.getCount()));
                unique.put(prefix.toString(), parent.getCount());
            }
            buildHeap(prefix, entry.getValue(), unique);
            prefix.setLength(length);
        }
    }


    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        for (Map.Entry<String, Word> children: root.getChildren().entrySet()) {
            list.append(children.toString());
            list.append("\n");
        }
        return list.toString();
    }
}
