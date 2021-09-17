package view;

import controller.ControllerCadastroCarro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Veiculo;


import java.io.IOException;

public class JanelaCadastroCarro {
    private ControllerCadastroCarro controller;

    public void showAndWait(Veiculo veiculo, boolean tipoEdicaoOuCadastro) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent sceneGraph = loader.load(getClass().getResource("CadastroCarro.fxml").openStream());
        Scene scene = new Scene(sceneGraph, 388, 253);

        controller = loader.getController();

        if(veiculo!=null){
            if(tipoEdicaoOuCadastro)
                controller.setTipoVeiculoToView(veiculo);
            else
                controller.setVeiculoToView(veiculo);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }

    }
}
