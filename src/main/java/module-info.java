module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens view to javafx.fxml;
    opens controller;
    opens model;

    exports model;
    exports controller;
    exports view;
}