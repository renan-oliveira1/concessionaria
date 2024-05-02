package data.repository;

import data.dao.IDao;
import domain.model.Vendedor;
import domain.repository.IRepository;

import java.util.List;

public class VendedorRepositoryImpl implements IRepository<Vendedor, Integer> {
    private final IDao<Vendedor, Integer> vendedorDao;

    public VendedorRepositoryImpl(IDao<Vendedor, Integer> vendedorDao) {
        this.vendedorDao = vendedorDao;
    }

    @Override
    public void save(Vendedor type) {
        vendedorDao.save(type);
    }

    @Override
    public void delete(Integer key) {
        vendedorDao.delete(key);

    }

    @Override
    public void update(Vendedor type) {
        vendedorDao.update(type);
    }

    @Override
    public Vendedor loadOne(Integer key) {
        return vendedorDao.loadOne(key);
    }

    @Override
    public List<Vendedor> loadAll() {
        return vendedorDao.loadAll();
    }
}
