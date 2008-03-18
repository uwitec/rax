package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Article;

public interface ArticleDao {

    public Long create(Article newInstance) throws DataAccessException;

    public Article read(Long id) throws DataAccessException;

    public int update(Article transientObject) throws DataAccessException;

    public int delete(Article persistentObject) throws DataAccessException;

    public int deleteByCategoryId(Long id) throws DataAccessException;

    public int count() throws DataAccessException;

    public int count(boolean onlyPub) throws DataAccessException;

    public int countByCategoryId(Long id) throws DataAccessException;

    public int countByCategoryId(Long id, boolean onlyPub)
            throws DataAccessException;

    public List<Article> list(int index, int num) throws DataAccessException;

    public List<Article> list(long index, int num, boolean onlyPub)
            throws DataAccessException;

    public List<Article> listAll() throws DataAccessException;

    public List<Article> listAll(boolean onlyPub) throws DataAccessException;

    public List<Article> listByCategoryId(Long id, long index, int num)
            throws DataAccessException;

    public List<Article> listByCategoryId(Long id, long index, int num,
            boolean onlyPub) throws DataAccessException;

    public List<Article> listAllByCategoryId(Long id)
            throws DataAccessException;

    public List<Article> listAllByCategoryId(Long id, boolean onlyPub)
            throws DataAccessException;

}
