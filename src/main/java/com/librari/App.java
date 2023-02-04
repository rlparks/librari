package com.librari;

import javafx.application.Application;

/**
 * Librari App Driver Class
 */
public class App {
    public static void main(String[] args) {
        try {
            Application.launch(Librari.class, args);
        } catch (Exception e) {
            System.err.println("Something went wrong in the app! " + e.getMessage());
            System.exit(1);
        }
    }
}