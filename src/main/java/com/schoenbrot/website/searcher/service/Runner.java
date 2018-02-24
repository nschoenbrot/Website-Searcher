package com.schoenbrot.website.searcher.service;

import com.schoenbrot.website.searcher.service.util.UrlRetriever;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Runs the keyword search on all web pages.
 */
public class Runner {
    private final UrlRetriever urlRetriever;
    private final KeywordSearcher keywordSearcher;

    public Runner(UrlRetriever urlRetriever, KeywordSearcher keywordSearcher) {
        this.urlRetriever = urlRetriever;
        this.keywordSearcher = keywordSearcher;
    }

    public Map<String, Boolean> doWebPagesContainKeyword(final String keyword) {
        final Map<String, Boolean> wasKeywordFound = new ConcurrentHashMap<>();
        final Set<Thread> threads = ConcurrentHashMap.newKeySet();
        final int maxNumberOfThreads = 20;
        final List<String> urls = urlRetriever.getUrls();

        int index;
        for (index = 0; index < maxNumberOfThreads && index < urls.size(); index++) {
            final String url = urls.get(index);
            final Thread thread = new Thread(() -> wasKeywordFound.put(url, keywordSearcher.wasKeywordFound(url, keyword)));
            thread.start();
            threads.add(thread);
        }

        while (!threads.isEmpty()) {
            for (final Thread thread : threads) {
                if (!thread.isAlive()) {
                    threads.remove(thread);
                    if (index < urls.size()) {
                        final String url = urls.get(index++);
                        final Thread nextThread = new Thread(() -> wasKeywordFound.put(url, keywordSearcher.wasKeywordFound(url, keyword)));
                        nextThread.start();
                        threads.add(nextThread);
                    }
                }
            }
        }

        return wasKeywordFound;
    }
}
