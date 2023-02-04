package com.librari;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/**
 * Main Librari Application Code
 */
public class Librari extends Application {

    ArrayList<Book> books;
    Scene scene;
    VBox root;

    /**
     * Initialize the elements of the scene graph.
     */
    @Override
    public void init() {
        loadBooks("./books");
        root = new VBox(5);
        root.getChildren().add(new BookGrid());
    }

    /**
     * Load the book files from the specified booksPath.
     */
    public void loadBooks(String booksPath) {
        books = new ArrayList<Book>();
        File folder = new File(booksPath);
        File[] fileList = folder.listFiles();
        for (File file : fileList) {
            String bookName = file.getName().substring(0, file.getName().indexOf("."));
            books.add(new Book(bookName, file));
        }
    }

    /**
     * Initialize the scene and stage of the JavaFX app.
     */
    @Override
    public void start(Stage stage) {
        scene = new Scene(root);
        stage.setTitle("librari");
        stage.setMaxWidth(640);
        stage.setMaxHeight(480);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();
    }
}