package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Ware;

public interface WareDao {

    public int create(Ware newInstance) throws DataAccessException;

    public Ware read(int id) throws DataAccessException;

    public int update(Ware transientObject) throws DataAccessException;

    public int delete(Ware persistentObject) throws DataAccessException;

    public int count() throws DataAccessException;

    public List<Ware> list(int index, int num) throws DataAccessException;

}
