package domain.repository;

import java.util.List;

public interface IRepository<T, K>{
    void save(T type);
    void delete(K key);
    void update(T type);
    T loadOne(K k);
    List<T> loadAll();
}
