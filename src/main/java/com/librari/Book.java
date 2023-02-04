package com.librari;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.io.InputStreamReader;

/** A struct to hold metadata we need for a book. */
public class Book {

    private static final String SEARCH_ENDPOINT = "https://openlibrary.org/search.json?q=";

    String title = null;
    String author = null;
    int year = -1;
    int pages = -1;
    String olid = null;

    public Book(String rawTitle) {
        JsonObject results = query(rawTitle);
        updateMetadata(results);
    }

    public JsonObject query(String rawTitle) {
        try {
            String sUrl = SEARCH_ENDPOINT + rawTitle.replaceAll(" ", "+");
            URL url = new URL(sUrl);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonElement je = JsonParser.parseReader(reader);
            return je.getAsJsonObject();
        } catch (Exception e) {
            System.err.println("Error while querying API: " + e.getMessage());
        }
        return null;
    }

    public void updateMetadata(JsonObject results) {
            // Parse through results to get the book's OLID
            JsonObject metadata = results
                .get("docs")
                .getAsJsonArray()
                .get(0)
                .getAsJsonObject();

            this.title = metadata
                .get("title")
                .getAsString();

            this.author = metadata
                .get("author_name")
                .getAsJsonArray()
                .get(0)
                .getAsString();

            this.year = metadata
                .get("first_publish_year")
                .getAsInt();

            this.pages = metadata
                .get("number_of_pages_median")
                .getAsInt();

            this.olid = metadata
                .get("edition_key")
                .getAsJsonArray()
                .get(0)
                .getAsString();
    }

    @Override
    public String toString() {
        return String.format(
            "Title: %s\n" +
            "Author: %s\n" +
            "Year: %d\n" +
            "Pages: %d\n" +
            "OLID: %s\n",
            title, author, year, pages, olid
        );
    }
}
