package com.company;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageTest extends TestCase {
    Page page;
    Page page2;
    String str1;
    String str2;

    protected void setUp() {
        page = new Page();
        page2 = new Page();
    }
    @Test
    public void test() {
        page.add(Arrays.asList(new String[]{"word"}));
        page.add(Arrays.asList(new String[]{"word"}));
        page.add(Arrays.asList(new String[]{"word", "density", "word", "density"}));
        str1 = page.toString();
        System.out.println(str1);

    }

    @Test
    public void testBuildPage() {
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(Arrays.asList(new String[]{"parent"}));
        list.add(Arrays.asList(new String[]{"parent", "child1"}));
        list.add(Arrays.asList(new String[]{"parent", "child1"}));
        list.add(Arrays.asList(new String[]{"parent", "child1", "child2"}));
        page2.buildPage(list);
        str2 = page2.toString();
        System.out.println(str2);

    }

}
