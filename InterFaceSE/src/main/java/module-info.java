module org.openjfx.testFX {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.testFX to javafx.fxml;
    exports org.openjfx.testFX;
}
