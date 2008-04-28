package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Ware;

public interface WareGroupingDao {

    public int create(int wareId, int categoryId) throws DataAccessException;

    public int delete(int id) throws DataAccessException;

    public List<Ware> listWareByCategoryId(int categoryId) throws DataAccessException;
    
}