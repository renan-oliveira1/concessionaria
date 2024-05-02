package domain.use_case.vendedor;

import data.dao.IDao;
import data.dao.VendedorDao;
import domain.model.VeiculoImportado;
import domain.model.VeiculoNacional;
import domain.model.Vendedor;
import domain.repository.IRepository;

import java.util.ArrayList;
import java.util.List;

public class LoadAllSellersUseCase {
    private final IRepository<Vendedor, Integer> vendedorRepository;
    private final IRepository<VeiculoNacional, Integer> veiculoNacionalRepository;
    private final IRepository<VeiculoImportado, Integer> veiculoImportadoRepository;

    public LoadAllSellersUseCase(IRepository<Vendedor, Integer> vendedorRepository, IRepository<VeiculoNacional, Integer> veiculoNacionalRepository, IRepository<VeiculoImportado, Integer> veiculoImportadoRepository) {
        this.vendedorRepository = vendedorRepository;
        this.veiculoNacionalRepository = veiculoNacionalRepository;
        this.veiculoImportadoRepository = veiculoImportadoRepository;
    }

    public List<Vendedor> invoke(){
        List<Vendedor> vendedores = new ArrayList<>();
        for(Vendedor vendedor : vendedorRepository.loadAll()) {

            for (VeiculoImportado veiculoImportado : veiculoImportadoRepository.loadAll()) {
                if (veiculoImportado.getVendedor() != null) {
                    if (veiculoImportado.getVendedor().getId().equals(vendedor.getId()))
                        vendedor.addVeiculoVenda(veiculoImportado);
                }
            }
            for (VeiculoNacional veiculoNacional : veiculoNacionalRepository.loadAll()){
                if (veiculoNacional.getVendedor() != null) {
                    if (veiculoNacional.getVendedor().getId().equals(vendedor.getId()))
                        vendedor.addVeiculoVenda(veiculoNacional);
                }
            }

            vendedores.add(vendedor);
        }
        return vendedores;
    }
}
