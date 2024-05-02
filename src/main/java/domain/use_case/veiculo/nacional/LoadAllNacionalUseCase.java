package domain.use_case.veiculo.nacional;

import domain.model.VeiculoNacional;
import domain.repository.IRepository;

import java.util.List;

public class LoadAllNacionalUseCase {
    private final IRepository<VeiculoNacional, Integer> veiculoNacionalRepository;

    public LoadAllNacionalUseCase(IRepository<VeiculoNacional, Integer> veiculoNacionalRepository) {
        this.veiculoNacionalRepository = veiculoNacionalRepository;
    }

    public List<VeiculoNacional> invoke(){
        return veiculoNacionalRepository.loadAll();
    }
}
