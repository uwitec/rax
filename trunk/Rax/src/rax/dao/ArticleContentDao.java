package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ArticleContentDao {

    public int create(String newInstance) throws DataAccessException;

    public int update(String transientObject) throws DataAccessException;

    public int delete(String persistentObject) throws DataAccessException;

    public int deleteByCategoryId(int id) throws DataAccessException;

    public String read(int id) throws DataAccessException;

    public int countByCategoryId(int id) throws DataAccessException;

    public List<String> listByCategoryId(int id, int index, int num)
            throws DataAccessException;

    public List<String> listAllByCategoryId(int id) throws DataAccessException;

}
