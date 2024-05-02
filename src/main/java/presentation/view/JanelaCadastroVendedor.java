package presentation.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class JanelaCadastroVendedor {

    public void showAndWait() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent sceneGraph = loader.load(getClass().getResource("CadastroVendedor.fxml").openStream());
        Scene scene = new Scene(sceneGraph, 238, 90);


        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
