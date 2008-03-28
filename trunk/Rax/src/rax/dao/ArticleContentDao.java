package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ArticleContentDao {

    public int create(int id, List<String> newInstance) throws DataAccessException;

    public String read(int id, int pageId) throws DataAccessException;
    
    public int update(int id, List<String> transientObject) throws DataAccessException;

    public int deleteByArticleId(int id) throws DataAccessException;

    public int countByArticleId(int id) throws DataAccessException;

    public List<String> listByArticleId(int id) throws DataAccessException;

}
