package erp.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import erp.model.Ware;

public interface WareDao {

    public int create(Ware newInstance) throws DataAccessException;

    public Ware read(int id) throws DataAccessException;

    public int update(Ware transientObject) throws DataAccessException;

    public int delete(Ware persistentObject) throws DataAccessException;

    public int count(int status) throws DataAccessException;

    public List<Ware> list(int status, int index, int num) throws DataAccessException;
    
    public List<Ware> listLimited(int status) throws DataAccessException;

    public List<Ware> findByBarcode(String barcode) throws DataAccessException;

    public List<Ware> findByKeywords(List<String> keywordList) throws DataAccessException;

}
