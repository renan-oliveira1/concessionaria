package data.repository;

import data.dao.VeiculoDao;
import domain.model.Veiculo;
import domain.repository.IVeiculoRepository;

import java.util.List;

public class VeiculoRepositoryImpl implements IVeiculoRepository {
    private final VeiculoDao veiculoDao;

    public VeiculoRepositoryImpl(VeiculoDao veiculoDao) {
        this.veiculoDao = veiculoDao;
    }

    @Override
    public void updateSell(Veiculo veiculo) {
        veiculoDao.updateSell(veiculo);
    }

    @Override
    public void save(Veiculo veiculo) {
        veiculoDao.save(veiculo);
    }

    @Override
    public void delete(Integer id) {
        veiculoDao.delete(id);
    }

    @Override
    public void update(Veiculo veiculo) {
        veiculoDao.update(veiculo);
    }

    @Override
    public Veiculo loadOne(Integer id) {
        return veiculoDao.loadOne(id);
    }

    @Override
    public List<Veiculo> loadAll() {
        return veiculoDao.loadAll();
    }
}
