package presentation.controller;

import domain.use_case.veiculo.SellVeiculoUseCase;
import domain.use_case.vendedor.LoadAllSellersUseCase;
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
import domain.model.Veiculo;
import domain.model.VeiculoImportado;
import domain.model.VeiculoNacional;
import domain.model.Vendedor;
import presentation.view.JanelaCadastroVendedor;
import presentation.view.JanelaListaVendas;
import util.AppDependencies;

import java.io.IOException;
import java.time.LocalDate;

public class ControllerEfetuarVenda {

    @FXML private Label lbCarro;
    @FXML private Label lbMarca;
    @FXML private Label lbValor;
    @FXML private Label lbComissao;

    @FXML private Label lbNomeCarro;
    @FXML private Label lbCarroMarca;
    @FXML private Label lbCarroValor;
    @FXML private Label lbCarroComissao;
    @FXML private Label lbPaisOuProprietario;
    @FXML private Label lbCarroProp;
    @FXML private Label lbCarroPais;

    @FXML private Button btnVender;
    @FXML private Button btnAdicionarVendedor;
    @FXML private Button btnListaVendas;

    @FXML
    private TableView<Vendedor> tableVendedores;
    @FXML
    private TableColumn<Vendedor, String> cId;
    @FXML
    private TableColumn<Vendedor, String> cNome;

    private ObservableList<Vendedor> vendedores;
    private Veiculo veiculoToSell;

    private final LoadAllSellersUseCase loadAllSellersUseCase = AppDependencies.loadAllSellersUseCase();
    private final SellVeiculoUseCase sellVeiculoUseCase = AppDependencies.getSellVeiculoUseCase();

    @FXML
    private void initialize() {
        bindTableModel();
        bindDataToTable();
        loadValues();
    }

    private void loadValues() {
        vendedores.clear();
        vendedores.addAll(loadAllSellersUseCase.invoke());
    }

    private void bindDataToTable() {
        vendedores = FXCollections.observableArrayList();
        tableVendedores.setItems(vendedores);
    }

    private void bindTableModel() {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    public void setTipoVeiculoToView(Veiculo veiculo) {
        this.veiculoToSell = veiculo;

        lbNomeCarro.setText(veiculo.getNome());
        lbCarroMarca.setText(veiculo.getMarca());
        lbCarroValor.setText(String.format ("%.2f", veiculo.getValorVenda()));
        lbCarroComissao.setText(String.format ("%.2f", veiculo.calculaComissao()));

        if(veiculo instanceof VeiculoNacional){

            if( ((VeiculoNacional) veiculo).getProprietario().isPresent() ) {
                lbCarroProp.setText( ((VeiculoNacional) veiculo).getProprietario().get().getNome() );
                lbPaisOuProprietario.setText("Proprietario:");
            }
            else{
                lbCarroProp.setText(" ");
                lbPaisOuProprietario.setText(" ");
            }

        }
        else{
            lbCarroPais.setText( ((VeiculoImportado) veiculo).getPaisOrigem().toString() );
            lbPaisOuProprietario.setText("Pais:");
        }

    }

    public void adicionarVendedor(ActionEvent actionEvent) throws IOException {
        JanelaCadastroVendedor newWindow = new JanelaCadastroVendedor();
        newWindow.showAndWait();
        loadValues();
    }

    public void efetuarVenda(ActionEvent actionEvent) {
        Vendedor vendedor = tableVendedores.getSelectionModel().getSelectedItem();
        sellVeiculoUseCase.invoke(veiculoToSell, vendedor);

        fecharJanela();
    }

    public void verListaVendas(ActionEvent actionEvent) throws IOException {
        JanelaListaVendas listaVendas = new JanelaListaVendas();
        Vendedor vendedor = tableVendedores.getSelectionModel().getSelectedItem();
        listaVendas.showAndWait(vendedor);
        loadValues();
    }

    public void fecharJanela(){
        Stage stage = (Stage) btnVender.getScene().getWindow();
        stage.close();
    }

}
