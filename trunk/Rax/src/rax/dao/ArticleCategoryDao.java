package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.ArticleCategory;

public interface ArticleCategoryDao {

    public int create(ArticleCategory newInstance) throws DataAccessException;

    public ArticleCategory read(int id) throws DataAccessException;

    public int update(ArticleCategory transientObject)
            throws DataAccessException;

    public int delete(ArticleCategory persistentObject)
            throws DataAccessException;

    public int deleteByCategoryId(int id) throws DataAccessException;

    public int count() throws DataAccessException;

    public int countByCategoryId(int id) throws DataAccessException;

    public List<ArticleCategory> list(int index, int num)
            throws DataAccessException;

    public List<ArticleCategory> listByCategoryId(int id, int index, int num)
            throws DataAccessException;

    public List<ArticleCategory> listAll() throws DataAccessException;

    public List<ArticleCategory> listAllByCategoryId(int id)
            throws DataAccessException;

    public List<ArticleCategory> listAllSubCategoryByCategoryId(int id)
            throws DataAccessException;

}
