module com.librari {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.librari to javafx.fxml;
    exports com.librari;
}
