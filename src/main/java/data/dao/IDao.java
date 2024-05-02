package data.dao;

import java.util.List;

public interface IDao<T, K>{
    void save(T type);
    void delete(K key);
    void update(T type);
    T loadOne(K k);
    List<T> loadAll();
}
