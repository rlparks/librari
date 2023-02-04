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
    
    @Override
    public void init() {
        root = new VBox();
    }

    @Override
    public void start(Stage stage) {
        scene = new Scene(root);
        stage.setTitle("Librari");
        stage.setMaxWidth(640);
        stage.setMaxHeight(480);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();
    }
}