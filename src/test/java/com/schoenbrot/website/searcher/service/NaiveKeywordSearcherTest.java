package com.schoenbrot.website.searcher.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NaiveKeywordSearcherTest {
    private NaiveKeywordSearcher naiveKeywordSearcher;

    @Before
    public void setUp() throws Exception {
        naiveKeywordSearcher = new NaiveKeywordSearcher();
    }

    @Ignore("This is not a real unit test. Will attempt to get the web page. " +
            "To enable real unit testing use a client that supports DI or try power mockito.")
    @Test
    public void wasKeywordFound() throws Exception {
        final String url = "https://google.com";
        final String keyword = "google";

        final boolean expected = true;
        final boolean actual = naiveKeywordSearcher.wasKeywordFound(url, keyword);
        assertEquals(expected, actual);
    }

}