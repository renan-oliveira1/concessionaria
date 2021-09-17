package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JanelaPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane sceneGraph = FXMLLoader.load(getClass().getResource("JanelaPrincipal.fxml"));
        Scene scene = new Scene(sceneGraph, 573, 573);
        stage.setScene(scene);
        stage.show();
    }
}
