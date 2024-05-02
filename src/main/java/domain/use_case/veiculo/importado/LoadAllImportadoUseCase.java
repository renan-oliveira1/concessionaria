package domain.use_case.veiculo.importado;

import domain.model.VeiculoImportado;
import domain.repository.IRepository;

import java.util.List;

public class LoadAllImportadoUseCase {
    private final IRepository<VeiculoImportado, Integer> veiculoImportadoRepository;

    public LoadAllImportadoUseCase(IRepository<VeiculoImportado, Integer> veiculoImportadoRepository) {
        this.veiculoImportadoRepository = veiculoImportadoRepository;
    }

    public List<VeiculoImportado> loadAll(){
        return veiculoImportadoRepository.loadAll();
    }
}
