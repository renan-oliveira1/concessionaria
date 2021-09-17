package model;

import java.time.LocalDate;

public abstract class Veiculo {
    private int id;
    private String nome;
    private String marca;
    private LocalDate dataVenda;
    private double valorVenda;
    private Vendedor vendedor;
    private int tipo;


    public abstract double calculaComissao();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Id - " + id +
                " | Nome:" + nome +
                " | Marca: " + marca +
                " | Data venda: " + dataVenda +
                " | Valor: " + valorVenda;
    }
}
