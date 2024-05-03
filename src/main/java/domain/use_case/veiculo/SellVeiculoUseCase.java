package domain.use_case.veiculo;

import data.repository.VeiculoRepositoryImpl;
import domain.model.Veiculo;
import domain.model.Vendedor;

import java.time.LocalDate;

public class SellVeiculoUseCase {
    private final VeiculoRepositoryImpl veiculoRepository;

    public SellVeiculoUseCase(VeiculoRepositoryImpl veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public boolean invoke(Veiculo veiculo, Vendedor vendedor){
        if(veiculo == null || vendedor == null){
            return false;
        }
        veiculo.setDataVenda(LocalDate.now());
        veiculo.setVendedor(vendedor);
        veiculoRepository.updateSell(veiculo);
        return true;
    }
}
