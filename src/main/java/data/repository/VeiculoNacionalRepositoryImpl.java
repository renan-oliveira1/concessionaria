package data.repository;

import data.dao.IDao;
import domain.model.VeiculoNacional;
import domain.repository.IRepository;

import java.util.List;

public class VeiculoNacionalRepositoryImpl implements IRepository<VeiculoNacional, Integer> {
    private final IDao<VeiculoNacional, Integer> veiculoNacionalDao;

    public VeiculoNacionalRepositoryImpl(IDao<VeiculoNacional, Integer> veiculoNacionalIDao) {
        this.veiculoNacionalDao = veiculoNacionalIDao;
    }

    @Override
    public void save(VeiculoNacional type) {
        veiculoNacionalDao.save(type);
    }

    @Override
    public void delete(Integer key) {
        veiculoNacionalDao.delete(key);
    }

    @Override
    public void update(VeiculoNacional type) {
        veiculoNacionalDao.update(type);
    }

    @Override
    public VeiculoNacional loadOne(Integer integer) {
        return veiculoNacionalDao.loadOne(integer);
    }

    @Override
    public List<VeiculoNacional> loadAll() {
        return veiculoNacionalDao.loadAll();
    }
}
