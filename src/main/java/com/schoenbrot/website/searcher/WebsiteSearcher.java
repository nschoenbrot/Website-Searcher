package com.schoenbrot.website.searcher;

import com.schoenbrot.website.searcher.service.NaiveKeywordSearcher;
import com.schoenbrot.website.searcher.service.Runner;
import com.schoenbrot.website.searcher.service.util.UrlRetriever;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebsiteSearcher {
    public static void main(String... args) {
        if (args.length < 1) {
            System.out.println("Please specify a keyword.");
            return;
        }

        final String keyword = args[0];
        final Map<String, Boolean> doWebPagesContainKeyword = new Runner(
                new UrlRetriever(),
                new NaiveKeywordSearcher()
        ).doWebPagesContainKeyword(keyword);

        try {
            Files.write(Paths.get("results.txt"),
                    readyMapToBePrinted(keyword, doWebPagesContainKeyword),
                    Charset.forName("UTF-8")
            );
        } catch (IOException e) {
            System.out.println("Error writing file " + e.getMessage());
        }

        System.out.println("Complete.\nResults output to results.txt");
    }

    private static List<String> readyMapToBePrinted(final String keyword, final Map<String, Boolean> map) {
        final List<String> list = new ArrayList<>();
        list.add("Results for " + keyword);
        for (final Map.Entry<String, Boolean> entry : map.entrySet()) {
            list.add(entry.getKey() + " " + (entry.getValue() ? "found" : "not found"));
        }
        return list;
    }
}
