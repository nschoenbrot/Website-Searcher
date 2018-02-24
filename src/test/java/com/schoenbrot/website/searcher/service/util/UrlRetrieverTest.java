package com.schoenbrot.website.searcher.service.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UrlRetrieverTest {
    private UrlRetriever urlRetriever;

    @Before
    public void setUp() throws Exception {
        urlRetriever = new UrlRetriever();
    }

    @Test
    public void getUrls() throws Exception {
        final List<String> expected = Arrays.asList("facebook.com/", "twitter.com/", "google.com/");
        final List<String> actual = urlRetriever.getUrls();
        assertThat(actual, is(expected));
    }

}