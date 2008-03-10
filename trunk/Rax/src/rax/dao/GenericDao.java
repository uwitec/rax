package rax.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    PK create(T newInstance);

    T read(PK id);

    boolean update(T transientObject);

    boolean delete(T persistentObject);
    
    long count();

    List<T> list(int index, int num);

    List<T> listAll();

}
