package com.company;

import java.util.HashMap;
import java.util.Map;

public class Word implements Comparable<Word> {

    private final String val;
    private int count;
    private Map<String, Word> list;


    public Word(String val) {
        this.val = val;
        this.count = 1;
        this.list = new HashMap<String, Word>();
    }

    public String getVal() {
        return val;
    }

    public int getCount() {
        return count;
    }

    public Map<String, Word> getChildren() {
        return list;
    }

    /**
     * Append Word child to current word
     * Word will be converted to all lowercase
     * If child already exists, increment its frequency
     * @param next
     * @return The added child
     */
    public Word add(String next) {
        if (next == null || next.length() == 0 || next.equals(Util.WHITE_SPACE) || next.equals("")) return null;
        String nextLow = next.trim().toLowerCase();
        if (list.containsKey(nextLow)) {
            return list.get(nextLow).increment();
        }
        Word child = new Word(nextLow);
        list.put(nextLow, child);
        return child;
    }

    public Word increment() {
        this.count++;
        return this;
    }

    public int compareTo(Word o) {
        if (this == o) return 0;
        if (this.count == o.count) return 0;
        if (this.count > o.count) return -1;
        else return 1;
    }

    @Override
    public String toString() {
        StringBuilder node = new StringBuilder();
        node.append("( ").append(val).append(" : ").append(count).append(" )");
        for (Map.Entry<String, Word> children: list.entrySet()) {
            node.append(children.getValue().toString());
            node.append("|*|");
        }
        return node.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Word) {
            Word o = (Word) other;
            return this.val.equalsIgnoreCase(o.getVal());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return val.hashCode();
    }

}
