package com.librari;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class BookList extends VBox {
    Librari app;
    Label header;

    ListView<BookListEntry> entryListView;

    ObservableList<BookListEntry> entryList;

    public BookList(Librari app) {
        super(5);
        this.app = app;
        header = new Label("Your Collection:");
        entryList = FXCollections.observableArrayList();
        entryListView = new ListView<BookListEntry>();

        ObservableList<BookListEntry> entries = FXCollections.observableArrayList();
        for (Book book : app.books) {
            entries.add(new BookListEntry(app, book));
        }
        header.setAlignment(Pos.TOP_CENTER);
        entryListView.setPrefWidth(600);
        Platform.runLater(() -> {
            entryList.clear();
            entryList = entries;
            entryListView.setItems(entryList);
        });
        
        this.getChildren().addAll(header, entryListView);
    }
}
