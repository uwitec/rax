package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.Article;

public interface ArticleDao {

    public int create(Article newInstance) throws DataAccessException;

    public Article read(int id) throws DataAccessException;

    public int update(Article transientObject) throws DataAccessException;

    public int delete(Article persistentObject) throws DataAccessException;

    public int deleteByCategoryId(int id) throws DataAccessException;

    public int count() throws DataAccessException;

    public int count(boolean onlyPub) throws DataAccessException;

    public int countByCategoryId(int id) throws DataAccessException;

    public int countByCategoryId(int id, boolean onlyPub)
            throws DataAccessException;

    public List<Article> list(int index, int num) throws DataAccessException;

    public List<Article> list(int index, int num, boolean onlyPub)
            throws DataAccessException;

    public List<Article> listAll() throws DataAccessException;

    public List<Article> listAll(boolean onlyPub) throws DataAccessException;

    public List<Article> listByCategoryId(int id, int index, int num)
            throws DataAccessException;

    public List<Article> listByCategoryId(int id, int index, int num,
            boolean onlyPub) throws DataAccessException;

    public List<Article> listAllByCategoryId(int id)
            throws DataAccessException;

    public List<Article> listAllByCategoryId(int id, boolean onlyPub)
            throws DataAccessException;

}
