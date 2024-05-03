package domain.use_case.veiculo;

import domain.model.Veiculo;
import domain.model.VeiculoImportado;
import domain.model.VeiculoNacional;
import domain.model.Vendedor;
import domain.repository.IRepository;
import domain.repository.IVeiculoRepository;

import java.time.LocalDate;

public class UpdateVeiculoUseCase {
    private final IRepository<VeiculoImportado, Integer> veiculoImportadoRepository;
    private final IRepository<VeiculoNacional, Integer> veiculoNacionalRepository;

    public UpdateVeiculoUseCase(IRepository<VeiculoImportado, Integer> veiculoImportadoRepository, IRepository<VeiculoNacional, Integer> veiculoNacionalRepository) {
        this.veiculoImportadoRepository = veiculoImportadoRepository;
        this.veiculoNacionalRepository = veiculoNacionalRepository;
    }

    public void invoke(Veiculo veiculo){
        if(veiculo == null){
            return;
        }
        if(veiculo instanceof VeiculoImportado){
            veiculoImportadoRepository.update((VeiculoImportado)veiculo);
        }else{
            veiculoNacionalRepository.update((VeiculoNacional) veiculo);
        }
    }
}
