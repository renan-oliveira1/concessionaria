package presentation.controller;

import data.dao.VendedorDao;
import domain.use_case.vendedor.SaveVendedorUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import domain.model.Vendedor;
import util.AppDependencies;

public class ControllerCadastroVendedor {

    @FXML private Label lbNome;
    @FXML private TextField txtNome;
    @FXML private Button btnCadastrar;
    private final SaveVendedorUseCase saveVendedorUseCase = AppDependencies.getSaveVendedorUseCase();

    public void cadastrarVendedor(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        Vendedor vendedor = new Vendedor(nome);
        saveVendedorUseCase.invoke(vendedor);
        Stage stage = (Stage) btnCadastrar.getScene().getWindow();
        stage.close();
    }
}
