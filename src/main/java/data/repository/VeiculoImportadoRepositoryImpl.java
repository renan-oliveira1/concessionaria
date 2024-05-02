package data.repository;

import data.dao.IDao;
import domain.model.VeiculoImportado;
import domain.repository.IRepository;

import java.util.List;

public class VeiculoImportadoRepositoryImpl implements IRepository<VeiculoImportado, Integer> {
    private final IDao<VeiculoImportado, Integer> veiculoImportadoDao;

    public VeiculoImportadoRepositoryImpl(IDao<VeiculoImportado, Integer> veiculoImportadoDao) {
        this.veiculoImportadoDao = veiculoImportadoDao;
    }

    @Override
    public void save(VeiculoImportado veiculo) {
        veiculoImportadoDao.save(veiculo);
    }

    @Override
    public void delete(Integer id) {
        veiculoImportadoDao.delete(id);
    }

    @Override
    public void update(VeiculoImportado veiculo) {
        veiculoImportadoDao.update(veiculo);
    }

    @Override
    public VeiculoImportado loadOne(Integer id) {
        return veiculoImportadoDao.loadOne(id);
    }

    @Override
    public List<VeiculoImportado> loadAll() {
        return veiculoImportadoDao.loadAll();
    }
}
