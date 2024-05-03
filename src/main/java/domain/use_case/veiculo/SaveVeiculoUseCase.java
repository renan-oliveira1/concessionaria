package domain.use_case.veiculo;

import domain.model.Veiculo;
import domain.model.VeiculoImportado;
import domain.model.VeiculoNacional;
import domain.repository.IRepository;
import domain.repository.IVeiculoRepository;

public class SaveVeiculoUseCase {
    private final IVeiculoRepository veiculoRepository;
    private final IRepository<VeiculoImportado, Integer> veiculoImportadoRepository;
    private final IRepository<VeiculoNacional, Integer> veiculoNacionalRepository;

    public SaveVeiculoUseCase(IVeiculoRepository veiculoRepository, IRepository<VeiculoImportado, Integer> veiculoImportadoRepository, IRepository<VeiculoNacional, Integer> veiculoNacionalRepository) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoImportadoRepository = veiculoImportadoRepository;
        this.veiculoNacionalRepository = veiculoNacionalRepository;
    }

    public void invoke(Veiculo veiculo){
        veiculoRepository.save(veiculo);
        if(veiculo instanceof VeiculoImportado){
            veiculoImportadoRepository.save((VeiculoImportado)veiculo);
        }else{
            veiculoNacionalRepository.save((VeiculoNacional) veiculo);
        }
    }
}
