package com.librari;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;

public class BookListEntry extends HBox {
    Librari app;
    Label title;
    Label author;
    Label year;
    Label pages;

    Button read;

    public BookListEntry(Librari app, Book book) {
        super(10);
        this.app = app;
        Separator sep1 = new Separator();
        HBox.setHgrow(sep1, Priority.ALWAYS);
        title = new Label(String.format("\"%s\" by %s (%s)", book.title, book.author, Integer.toString(book.year)));
        pages = new Label(String.format("%12s", "Pages: " + Integer.toString(book.pages)));
        read = new Button("Read");

        read.setOnAction(event -> {
            Platform.runLater(() -> app.showReader(book));
        });


        this.getChildren().addAll(read, title, sep1, pages);

    }
}