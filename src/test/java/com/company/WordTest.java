package com.company;
import junit.framework.TestCase;
import org.junit.Test;

public class WordTest extends TestCase {

    Word word;

    protected void setUp() {
        word = new Word("test");
    }
    @Test
    public void test() {
        String message = "( test : 1 )";
        assertEquals(message, word.toString());
    }

    @Test
    public void testAdd() {
        String message = "( hello : 1 )";
        assertEquals(message, word.add("hello").toString());
    }

    @Test
    public void testAdd2() {
        String message = "( hello : 2 )";
        word.add("hello");
        assertEquals(message, word.add("hello").toString());
    }
}
