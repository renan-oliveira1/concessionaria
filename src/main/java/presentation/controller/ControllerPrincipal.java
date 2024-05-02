package presentation.controller;

import data.dao.VeiculoImportadoDAO;
import data.dao.VeiculoNacionalDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import domain.model.*;
import presentation.view.JanelaCadastroCarro;
import presentation.view.JanelaVendedores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerPrincipal {

    @FXML
    private TableView<Veiculo> tableVeiculo;
    @FXML
    private TableColumn<Veiculo, String> cId;
    @FXML
    private TableColumn<Veiculo, String> cNomeCarro;
    @FXML
    private TableColumn<Veiculo, String> cMarca;
    @FXML
    private TableColumn<Veiculo, String> cValor;
    @FXML
    private Button btnAddCarroImportado;
    @FXML
    private Button btnAddCarroNacional;
    @FXML
    private Button btnEditarCarro;
    @FXML private Button btnVender;

    private ObservableList<Veiculo> veiculos;
    private final List<Veiculo> veiculosDisponivel = new ArrayList<>();


    @FXML
    private void initialize() {
        bindTableModel();
        bindDataToTable();
        loadValues();
    }


    private void bindTableModel() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNomeCarro.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        cValor.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
    }

    private void loadValues() {
        veiculos.clear();
        ajudarFiltrarVeiculosDisponiveis();
        for(Veiculo teste: veiculosDisponivel){
            if(teste.getVendedor()==null)
                veiculos.add(teste);
        }
    }

    private void bindDataToTable() {
        veiculos = FXCollections.observableArrayList();
        tableVeiculo.setItems(veiculos);
    }


    public void addCarroImportado(ActionEvent actionEvent) throws IOException {
        JanelaCadastroCarro newWindow = new JanelaCadastroCarro();
        VeiculoImportado veiculo = new VeiculoImportado();
        newWindow.showAndWait(veiculo, true);
        loadValues();
    }

    public void addCarroNacional(ActionEvent actionEvent) throws IOException {
        JanelaCadastroCarro newWindow = new JanelaCadastroCarro();
        VeiculoNacional veiculo = new VeiculoNacional();
        newWindow.showAndWait(veiculo, true);
        loadValues();
    }

    public void editarCarro(ActionEvent actionEvent) throws IOException {
        JanelaCadastroCarro newWindow = new JanelaCadastroCarro();
        Veiculo veiculo = tableVeiculo.getSelectionModel().getSelectedItem();
        newWindow.showAndWait(veiculo, false);
        loadValues();
    }

    public void ajudarFiltrarVeiculosDisponiveis() {
        veiculosDisponivel.clear();
        VeiculoImportadoDAO veiculoImportadoDAO = new VeiculoImportadoDAO();
        VeiculoNacionalDAO veiculoNacionalDAO = new VeiculoNacionalDAO();
        veiculosDisponivel.addAll(veiculoImportadoDAO.loadAll());
        veiculosDisponivel.addAll(veiculoNacionalDAO.loadAll());
    }

    public void venderCarro(ActionEvent actionEvent) throws IOException {
        JanelaVendedores newWindow = new JanelaVendedores();
        Veiculo veiculo = tableVeiculo.getSelectionModel().getSelectedItem();
        newWindow.showAndWait(veiculo);
        loadValues();
    }
}
