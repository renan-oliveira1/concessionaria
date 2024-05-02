package presentation.view;

import presentation.controller.ControllerEfetuarVenda;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import domain.model.Veiculo;

import java.io.IOException;

public class JanelaVendedores {

    private ControllerEfetuarVenda controller;

    public void showAndWait(Veiculo veiculo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent sceneGraph = loader.load(getClass().getResource("EfetuarVenda.fxml").openStream());
        Scene scene = new Scene(sceneGraph, 396, 447);

        controller = loader.getController();

        if(veiculo!=null){
            controller.setTipoVeiculoToView(veiculo);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }

    }

}
