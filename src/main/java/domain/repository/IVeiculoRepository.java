package domain.repository;

import domain.model.Veiculo;

public interface IVeiculoRepository extends IRepository<Veiculo, Integer> {
    void updateSell(Veiculo veiculo);
}
