module com.librari {
    requires javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.logging;
    requires transitive com.google.gson;
    opens com.librari to javafx.fxml;
    exports com.librari;
}
