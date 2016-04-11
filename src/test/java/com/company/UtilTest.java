package com.company;

import junit.framework.TestCase;
import org.junit.Test;

public class UtilTest extends TestCase{

    @Test
    public void testIsNumeric(){
        assertEquals(true, Util.isNumeric("1234"));
        assertEquals(true, Util.isNumeric("1234.2"));
        assertEquals(true, Util.isNumeric("1,234"));
        assertEquals(false, Util.isNumeric("1234a"));
    }

    @Test
    public void testEndsWithPunc(){
        assertEquals(true, Util.endsWithPunc("a."));
        assertEquals(true, Util.endsWithPunc("a,"));
        assertEquals(false, Util.endsWithPunc("a"));
    }

    @Test
    public void testParse(){
        assertEquals(null, Util.parseWord(""));
        assertEquals(null, Util.parseWord("$12.30"));
        assertEquals("abc", Util.parseWord(" abc  "));
    }
}
