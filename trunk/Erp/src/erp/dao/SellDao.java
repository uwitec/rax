package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Sell;

public interface SellDao {

    public int create(Sell newInstance) throws DataAccessException;

    public Sell read(int id) throws DataAccessException;

    public int update(Sell transientObject) throws DataAccessException;

    public int delete(Sell persistentObject) throws DataAccessException;

    public int count() throws DataAccessException;

    public List<Sell> list(int index, int num) throws DataAccessException;

}
