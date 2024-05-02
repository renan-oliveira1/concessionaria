module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens presentation.view to javafx.fxml;
    opens presentation.controller;
    opens domain.model;

    exports domain.model;
    exports presentation.controller;
    exports presentation.view;
}