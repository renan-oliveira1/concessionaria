package domain.model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private Integer id;
    private String nome;

    private List<Veiculo> veiculosVendidos = new ArrayList<>();

    public Vendedor(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Vendedor(String nome) {
        this.id = null;
        this.nome = nome;
    }

    public void addVeiculoVenda(Veiculo veiculo){
        if(veiculo!=null)
            veiculosVendidos.add(veiculo);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Veiculo> getVeiculosVendidos() {
        return veiculosVendidos;
    }

    public void setVeiculosVendidos(List<Veiculo> veiculosVendidos) {
        this.veiculosVendidos = veiculosVendidos;
    }

    @Override
    public String toString() {
        return nome;
    }
}
