package com.librari;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;

public class Reader extends VBox {

    Librari mainApp;
    Book currentBook;
    VBox topBar;
    HBox topMenu;
    ProgressBar percentReadIndicator;

    /**
     * Initializes the Reader VBox.
     * 
     * @param app  the main librari app
     * @param book the book being read
     */
    public Reader(Librari app, Book book) {
        mainApp = app;
        currentBook = book;

        topBar = new VBox();
        topMenu = new HBox();

        percentReadIndicator = new ProgressBar(0.0);
        percentReadIndicator.setMaxWidth(Double.MAX_VALUE);
        topBar.getChildren().addAll(topMenu, percentReadIndicator);

        Button backButton = new Button("Back");
        Label titleText = new Label(book.title);
        titleText.setAlignment(Pos.CENTER);
        Separator leftSep = new Separator();
        Separator rightSep = new Separator();
        HBox.setHgrow(leftSep, Priority.ALWAYS);
        HBox.setHgrow(rightSep, Priority.ALWAYS);

        Button increaseFont = new Button("+");
        Button decreaseFont = new Button("-");

        topMenu.getChildren().addAll(backButton, leftSep, titleText, rightSep, increaseFont, decreaseFont);

        TextArea bookText = new TextArea();
        try {
            bookText.setText(new String(Files.readAllBytes(Paths.get(book.bookFile.getPath())), StandardCharsets.UTF_8));
        } catch (IOException ie) {
            bookText.setText("");
        }
        bookText.setWrapText(true);
        bookText.setEditable(false);
        //initText(bookText, currentBook.bookFile);

        VBox.setVgrow(bookText, Priority.ALWAYS);
        bookText.positionCaret(0);

        backButton.setOnAction(e -> {
            Platform.runLater(() -> app.closeReader());
        });

        increaseFont.setOnAction(e -> {
            bookText.setFont(Font.font(bookText.getFont().getSize() + 2.5));
        });

        decreaseFont.setOnAction(e -> {
            bookText.setFont(Font.font(bookText.getFont().getSize() - 2.5));
        });

        this.getChildren().addAll(topBar, bookText);

        // https://stackoverflow.com/questions/54926625/javafx-textarea-get-scrolltop-percentage
        Platform.runLater(() -> {

            // Get the ScrollBar from the TextArea using a lookup
            ScrollBar scrollBar = (ScrollBar) bookText.lookup(".scroll-bar:vertical");

            // Now create a DoubleProperty that we'll bind to our scroll value
            DoubleProperty scrollPercentage = new SimpleDoubleProperty();
            scrollPercentage.bind(Bindings.createDoubleBinding(() -> scrollBar.valueProperty().get(),
                    scrollBar.valueProperty()));

            // Bind the progressbar
            percentReadIndicator.progressProperty().bind(scrollPercentage);

        });
    }

    /**
     * Reads text from the book file and inserts it into the
     * TextArea of the reader.
     * 
     * @param bookText the TextArea
     * @param bookFile {@link File} object of book
     */
    public void initText(TextArea bookText, File bookFile) {
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(bookFile);
            while (fileScanner.hasNext()) {
                bookText.appendText(fileScanner.nextLine() + '\n');
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}