package com.schoenbrot.website.searcher.service.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Get all the urls from the URLs CSV.
 */
public class UrlRetriever {
    public List<String> getUrls() {
        final List<String> urls = new ArrayList<>();
        try {
            final Reader reader = new FileReader(getClass().getResource("/urls.txt").getPath());
            final Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for (final CSVRecord record : records) {
                urls.add(record.get("URL"));
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
