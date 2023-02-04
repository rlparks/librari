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

    /**
     * Returns a Book whose metadata is based on the search results
     * obtained when searching for rawTitle.
     * 
     * @param rawTitle the rough title of the book, spaces okay
     */
    public Book(String rawTitle) {
        JsonObject results = query(rawTitle);
        updateMetadata(results);
    }

    /**
     * Queries the Open Library API using the rawTitle search term, and
     * returns a JsonObject containing the entire book's metadata.
     * 
     * @param rawTitle the rough title of the book, spaces okay
     * @return the entire book's metadata in JSON form
     */
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

    /**
     * Updates the metadata fields of this Book using the JSON data
     * specified by results
     * 
     * @param results the JSON data used to update this Book
     */
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

    /**
     * Prints all of the metadata of this book.
     */
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
