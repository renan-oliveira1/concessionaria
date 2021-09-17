package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Veiculo;
import model.VeiculoNacional;
import model.Vendedor;

public class ControllerListaVendas {

    @FXML private Label lbId;
    @FXML private Label lbNome;
    @FXML private Label lbVendas;
    @FXML private Label lbIdVendedor;
    @FXML private Label lbNomeVendedor;
    @FXML private Label lbCarro;
    @FXML private Label lbNomeCarro;
    @FXML private Label lbComissao;
    @FXML private Label lbValorComissao;

    @FXML private Button btnCalcComissao;
    @FXML private Button btnFechar;

    @FXML
    private TableView<Veiculo> tableVendas;
    @FXML
    private TableColumn<Vendedor, String> cCarro;
    @FXML
    private TableColumn<Vendedor, String> cMarca;
    @FXML
    private TableColumn<Vendedor, String> cValor;
    @FXML
    private TableColumn<Vendedor, String> cData;

    private ObservableList<Veiculo> vendas;

    @FXML
    private void initialize() {
        bindTableModel();
        bindDataToTable();
    }

    private void loadValues(Vendedor vendedor) {
        vendas.clear();
        vendas.addAll(vendedor.getVeiculosVendidos());
    }

    private void bindDataToTable() {
        vendas = FXCollections.observableArrayList();
        tableVendas.setItems(vendas);

    }

    private void bindTableModel() {
        cCarro.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        cValor.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        cData.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
    }


    public void setTipoVeiculoToView(Vendedor vendedor) {
        loadValues(vendedor);
        lbIdVendedor.setText(String.valueOf(vendedor.getId()));
        lbNomeVendedor.setText(vendedor.getNome());
    }

    public void fecharJanela(ActionEvent actionEvent) {
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
    }

    public void calcularComissaoCarro(ActionEvent actionEvent) {
        Veiculo veiculo = tableVendas.getSelectionModel().getSelectedItem();
        lbNomeCarro.setText(veiculo.getNome());
        double valorComissao = veiculo.calculaComissao();
        lbValorComissao.setText(String.format ("%.2f", veiculo.calculaComissao()));
    }
}
