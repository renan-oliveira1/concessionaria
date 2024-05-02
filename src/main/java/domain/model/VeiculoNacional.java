package domain.model;

import java.time.LocalDate;
import java.util.Optional;

public class VeiculoNacional extends Veiculo{
    private Proprietario proprietario;

    public VeiculoNacional() {
    }

    public VeiculoNacional(String nome, String marca, double valorVenda) {
        this.setNome(nome);
        this.setMarca(marca);
        this.setDataVenda(null);
        this.setValorVenda(valorVenda);
        this.setVendedor(null);
        this.proprietario = null;
        this.setTipo(2);
    }

    public VeiculoNacional(int id, String nome, String marca, double valorVenda) {
        this.setId(id);
        this.setNome(nome);
        this.setMarca(marca);
        this.setDataVenda(null);
        this.setValorVenda(valorVenda);
        this.setVendedor(null);
        this.proprietario = null;
        this.setTipo(2);
    }

    public VeiculoNacional(String nome, String marca, double valorVenda, Proprietario proprietario) {
        this.setNome(nome);
        this.setMarca(marca);
        this.setDataVenda(null);
        this.setValorVenda(valorVenda);
        this.setVendedor(null);
        this.proprietario = proprietario;
        this.setTipo(2);
    }

    public VeiculoNacional(int id, String nome, String marca, double valorVenda, String localData, Vendedor vendedor) {
        this.setId(id);
        this.setNome(nome);
        this.setMarca(marca);
        this.setDataVenda(LocalDate.parse(localData));
        this.setValorVenda(valorVenda);
        this.setVendedor(vendedor);
        this.proprietario = null;
        this.setTipo(2);
    }


    public Optional<Proprietario> getProprietario() {
        return Optional.ofNullable(proprietario);
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public double calculaComissao() {
        if(getProprietario().isPresent())
            return getValorVenda()*0.03;
        return getValorVenda()*0.02;
    }

    @Override
    public String toString() {
        return "Nacional";
    }
}
