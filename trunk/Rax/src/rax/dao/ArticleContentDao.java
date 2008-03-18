package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ArticleContentDao {

    public Long create(String newInstance) throws DataAccessException;

    public int update(String transientObject) throws DataAccessException;

    public int delete(String persistentObject) throws DataAccessException;

    public int deleteByCategoryId(Long id) throws DataAccessException;

    public String read(Long id) throws DataAccessException;

    public int countByCategoryId(Long id) throws DataAccessException;

    public List<String> listByCategoryId(Long id, int index, int num)
            throws DataAccessException;

    public List<String> listAllByCategoryId(Long id) throws DataAccessException;

}
