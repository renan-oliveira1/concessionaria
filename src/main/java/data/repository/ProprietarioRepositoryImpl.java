package data.repository;

import data.dao.ProprietarioDao;
import domain.model.Proprietario;
import domain.repository.IRepository;

import java.util.List;

public class ProprietarioRepositoryImpl implements IRepository<Proprietario, Integer> {
    private final ProprietarioDao proprietarioDao;

    public ProprietarioRepositoryImpl(ProprietarioDao proprietarioDao) {
        this.proprietarioDao = proprietarioDao;
    }

    @Override
    public void save(Proprietario proprietario) {
        proprietarioDao.save(proprietario);
    }

    @Override
    public void delete(Integer id) {
        proprietarioDao.delete(id);
    }

    @Override
    public void update(Proprietario proprietario) {
        proprietarioDao.update(proprietario);
    }

    @Override
    public Proprietario loadOne(Integer id) {
        return proprietarioDao.loadOne(id);
    }

    @Override
    public List<Proprietario> loadAll() {
        return proprietarioDao.loadAll();
    }
}
