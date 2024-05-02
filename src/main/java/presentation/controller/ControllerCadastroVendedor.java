package presentation.controller;

import data.dao.VendedorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import domain.model.Vendedor;

public class ControllerCadastroVendedor {

    @FXML private Label lbNome;
    @FXML private TextField txtNome;
    @FXML private Button btnCadastrar;

    public void cadastrarVendedor(ActionEvent actionEvent) {
        String nome = txtNome.getText();
        Vendedor vendedor = new Vendedor(nome);
        VendedorDAO vendedorDAO = new VendedorDAO();
        vendedorDAO.save(vendedor);
        Stage stage = (Stage) btnCadastrar.getScene().getWindow();
        stage.close();
    }
}
