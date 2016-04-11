package com.company;


import junit.framework.TestCase;
import org.junit.Test;

public class AppTest extends TestCase{
    private App app;

    protected void setUp(){
        app = new App();
    }

    @Test
    public void testRun(){
        //app.run(CrawlerTest.URL1);
        app.run(CrawlerTest.URL2);
        //app.run(CrawlerTest.URL3);
        //app.run(CrawlerTest.URL4);
        //System.out.println(app.getPage().toString());
    }
}
