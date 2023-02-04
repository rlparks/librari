package com.librari;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main Librari Application Code
 */
public class Librari extends Application { 
    Scene scene; 
    VBox root;

    /**
     * Method used for testing Library API; will remove later.
     */
    public void testStuff() {
        Book book1 = new Book("fantastic mr fox");
        System.out.println(book1.toString());

        Book book2 = new Book("lord of the rings");
        System.out.println(book2.toString());
    }

    /**
     * Initialize the elements of the scene graph.
     */
    @Override
    public void init() {
        root = new VBox();
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
        testStuff();
    }
}