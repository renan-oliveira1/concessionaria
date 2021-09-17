package view;

import controller.ControllerListaVendas;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Vendedor;

import java.io.IOException;

public class JanelaListaVendas {


    private ControllerListaVendas controller;

    public void showAndWait(Vendedor vendedor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent sceneGraph = loader.load(getClass().getResource("ListaVendas.fxml").openStream());
        Scene scene = new Scene(sceneGraph, 595, 573);

        controller = loader.getController();

        if(vendedor!=null){
            controller.setTipoVeiculoToView(vendedor);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }

    }

}
