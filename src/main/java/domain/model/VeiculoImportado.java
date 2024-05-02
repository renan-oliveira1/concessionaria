package domain.model;


import java.time.LocalDate;

public class VeiculoImportado extends Veiculo{
    private Paises paisOrigem;

    public VeiculoImportado() {
    }

    public VeiculoImportado(int id, String nome, String marca, double valorVenda, String localData, Vendedor vendedor) {
        this.setId(id);
        this.setNome(nome);
        this.setMarca(marca);
        this.setDataVenda(LocalDate.parse(localData));
        this.setValorVenda(valorVenda);
        this.setVendedor(vendedor);
        this.paisOrigem = null;
        this.setTipo(1);
    }

    public VeiculoImportado(String nome, String marca, double valorVenda, Paises paisOrigem) {
        this.setNome(nome);
        this.setMarca(marca);
        this.setDataVenda(null);
        this.setValorVenda(valorVenda);
        this.setVendedor(null);
        this.paisOrigem = paisOrigem;
        this.setTipo(1);
    }

    public VeiculoImportado(int id, String nome, String marca, double valorVenda) {
        this.setId(id);
        this.setNome(nome);
        this.setMarca(marca);
        this.setDataVenda(null);
        this.setValorVenda(valorVenda);
        this.setVendedor(null);
        this.setTipo(1);
    }

    public Paises getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(Paises paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    @Override
    public double calculaComissao() {
        return getValorVenda()*0.05;
    }

    @Override
    public String toString() {
        return super.toString() + "Importado";
    }
}
