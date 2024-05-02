package presentation.controller;

import data.dao.VeiculoDAO;
import data.dao.VeiculoImportadoDAO;
import data.dao.VeiculoNacionalDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import domain.model.*;
import util.VerificacaoErroInput;

import java.util.Optional;


public class ControllerCadastroCarro {

    @FXML private Label lbNome;
    @FXML private Label lbMarca;
    @FXML private Label lbValor;
    @FXML private Label lbTipo;
    @FXML private Label lbProprietario;
    @FXML private Label lbPais;
    @FXML private Label lbTelefone;

    @FXML private TextField txtNomeCarro;
    @FXML private TextField txtMarca;
    @FXML private TextField txtValor;
    @FXML private TextField txtProprietario;
    @FXML private TextField txtTelefone;
    @FXML private ChoiceBox<String> cbTipo;
    @FXML private ChoiceBox<Paises> cbPais;
    @FXML private Button btnSalvarAtualizar;

    private final ObservableList<String> tiposCarro = FXCollections.observableArrayList("Nacional", "Importado");
    private final ObservableList<Paises> paises = FXCollections.observableArrayList(Paises.values());

    private Veiculo veiculoToLoad;
    private int tipoEditarOuSalvar;
    private int tipoUpdate;

    @FXML
    private void initialize(){
        cbTipo.setItems(tiposCarro);
        cbPais.setItems(paises);
    }

    public void setTipoVeiculoToView(Veiculo veiculo) {
        this.tipoEditarOuSalvar = 2;
        if(veiculo instanceof VeiculoNacional) {
            this.veiculoToLoad = veiculo;
            cbTipo.setValue("Nacional");
            cbTipo.setDisable(true);
            lbPais.setVisible(false);
            cbPais.setVisible(false);
        }
        else {
            this.veiculoToLoad = veiculo;
            cbTipo.setValue("Importado");
            cbTipo.setDisable(true);
            lbProprietario.setVisible(false);
            txtProprietario.setVisible(false);
            lbTelefone.setVisible(false);
            txtTelefone.setVisible(false);
        }
    }


    public void setVeiculoToView(Veiculo veiculo) {
        txtNomeCarro.setText(veiculo.getNome());
        txtMarca.setText(veiculo.getMarca());
        txtValor.setText(String.valueOf(veiculo.getValorVenda()));
        setTipoVeiculoToView(veiculo);
        this.tipoEditarOuSalvar = 1;
        this.veiculoToLoad = veiculo;
        if(veiculo instanceof  VeiculoNacional){
            this.tipoUpdate = 2;
            Optional<Proprietario> proprietario = ((VeiculoNacional) veiculo).getProprietario();
            if(proprietario.isPresent()){
                txtProprietario.setText(String.valueOf(proprietario.get().getNome()));
                String telefone = proprietario.get().getTelefone();
                if(telefone!=null)
                    txtTelefone.setText(telefone);
            }

        }
        else{
            this.tipoUpdate = 1;
            cbPais.setValue(((VeiculoImportado) veiculo).getPaisOrigem());
        }
        btnSalvarAtualizar.setText("Editar");
    }

    private VeiculoImportado getVeiculoImportado() throws VerificacaoErroInput {
        if(txtNomeCarro.getText() == null || txtNomeCarro.getText().isEmpty())
            throw ( new VerificacaoErroInput("O campo nome do carro esta null ou esta vazio"));

        if(txtMarca.getText() == null || txtMarca.getText().isEmpty())
            throw ( new VerificacaoErroInput("O campo marca esta null ou esta vazio"));

        if(txtValor.getText().isEmpty())
            throw ( new VerificacaoErroInput("O campo valor esta vazio ou os valores inseridos são invalidos"));

        if(cbPais.getSelectionModel().getSelectedItem()==null)
            throw ( new VerificacaoErroInput("O campo pais origem não foi preenchido"));


        String nome = txtNomeCarro.getText();
        String marca = txtMarca.getText();
        double valorVenda = Double.parseDouble(txtValor.getText());
        Paises paisOrigem = cbPais.getSelectionModel().getSelectedItem();
        return new VeiculoImportado(nome, marca, valorVenda, paisOrigem);
    }

    private VeiculoNacional getVeiculoNacional() throws VerificacaoErroInput {
        if(txtNomeCarro.getText() == null || txtNomeCarro.getText().isEmpty())
            throw ( new VerificacaoErroInput("O campo nome do carro esta null ou esta vazio"));

        if(txtMarca.getText() == null || txtMarca.getText().isEmpty())
            throw ( new VerificacaoErroInput("O campo marca esta null ou esta vazio"));

        if(txtValor.getText().isEmpty())
            throw ( new VerificacaoErroInput("O campo valor esta vazio ou os valores inseridos são invalidos"));

        String nome = txtNomeCarro.getText();
        String marca = txtMarca.getText();
        double valorVenda = Double.parseDouble(txtValor.getText());

        if(txtProprietario.getText().isEmpty()){
            return new VeiculoNacional(nome, marca, valorVenda);
        }

        String nomeProprietario = txtProprietario.getText();
        String proprietarioTelefone = txtTelefone.getText();

        if(txtTelefone.getText().isEmpty()) {
            Proprietario proprietario = new Proprietario(nomeProprietario, null);
            return new VeiculoNacional(nome, marca, valorVenda, proprietario);
        }

        Proprietario proprietario = new Proprietario(nomeProprietario, proprietarioTelefone);
        return new VeiculoNacional(nome, marca, valorVenda, proprietario);
    }


    public void salvarOuEditar(ActionEvent actionEvent) throws VerificacaoErroInput {
        if(tipoEditarOuSalvar == 1){
            updateVeiculo();
        }
        else{
            if(veiculoToLoad instanceof VeiculoImportado){
                salvarVeiculoImportado();
            }
            else{
                salvarVeiculoNacional();
            }
        }

    }

    private void salvarVeiculoImportado() throws VerificacaoErroInput {
        VeiculoImportado veiculoToSave = getVeiculoImportado();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        VeiculoImportadoDAO veiculoImportadoDAO = new VeiculoImportadoDAO();
        veiculoDAO.save(veiculoToSave);
        veiculoImportadoDAO.save(veiculoToSave);
        fecharJanela();
    }

    private void salvarVeiculoNacional() throws VerificacaoErroInput {
        VeiculoNacional veiculoToSave = getVeiculoNacional();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        VeiculoNacionalDAO veiculoINacionalDAO = new VeiculoNacionalDAO();
        veiculoDAO.save(veiculoToSave);
        veiculoINacionalDAO.save(veiculoToSave);
        fecharJanela();
    }

    private void updateVeiculo() throws VerificacaoErroInput {
        if(tipoUpdate == 1){
            VeiculoImportado veiculoImportado = getVeiculoImportado();
            veiculoImportado.setId(veiculoToLoad.getId());
            VeiculoImportadoDAO veiculoImportadoDAO = new VeiculoImportadoDAO();
            veiculoImportadoDAO.update(veiculoImportado);
        }
        else{
            VeiculoNacional veiculoNacional = getVeiculoNacional();
            veiculoNacional.setId(veiculoToLoad.getId());
            VeiculoNacionalDAO veiculoNacionalDAO = new VeiculoNacionalDAO();
            veiculoNacionalDAO.update(veiculoNacional);
        }
        fecharJanela();
    }

    private void fecharJanela(){
        Stage stage = (Stage) btnSalvarAtualizar.getScene().getWindow();
        stage.close();
    }
}
