package com.schoenbrot.website.searcher.service;

import com.schoenbrot.website.searcher.service.util.UrlRetriever;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RunnerTest {
    private Runner runner;
    @Mock
    private KeywordSearcher keywordSearcher;
    @Mock
    private UrlRetriever urlRetriever;

    @Before
    public void setUp() throws Exception {
        runner = new Runner(urlRetriever, keywordSearcher);
    }

    @Test
    public void doWebPagesContainKeyWord() throws Exception {
        final String url1 = "one.com";
        final String url2 = "two.com";
        final String url3 = "three.com";
        final String keyword = "keyword";
        final Map<String, Boolean> expected = new HashMap<>();
        expected.put(url1, true);
        expected.put(url2, false);
        expected.put(url3, false);

        when(urlRetriever.getUrls()).thenReturn(Arrays.asList(url1, url2, url3));
        when(keywordSearcher.wasKeywordFound(url1, keyword)).thenReturn(expected.get(url1));
        when(keywordSearcher.wasKeywordFound(url2, keyword)).thenReturn(expected.get(url2));
        when(keywordSearcher.wasKeywordFound(url3, keyword)).thenReturn(expected.get(url3));

        final Map<String, Boolean> actual = runner.doWebPagesContainKeyword(keyword);
        assertEquals(actual.get(url1), expected.get(url1));
        assertEquals(actual.get(url2), expected.get(url2));
        assertEquals(actual.get(url3), expected.get(url3));
    }

}