package domain.use_case.vendedor;

import domain.model.Vendedor;
import domain.repository.IRepository;

public class SaveVendedorUseCase {
    private final IRepository<Vendedor, Integer> vendedorRepository;

    public SaveVendedorUseCase(IRepository<Vendedor, Integer> vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public void invoke(Vendedor vendedor){
        if(vendedor == null){
            return;
        }
        vendedorRepository.save(vendedor);
    }
}
