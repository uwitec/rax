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

    public int countByCategoryId(int id) throws DataAccessException;

    public List<ArticleCategory> listAll() throws DataAccessException;

    public List<ArticleCategory> listByCategoryId(int id, int index, int num)
            throws DataAccessException;

    public List<ArticleCategory> listAllByCategoryId(int id)
            throws DataAccessException;

    public List<ArticleCategory> listSubCategoryByCategoryId(int id, int index,
            int num) throws DataAccessException;

    public List<ArticleCategory> listAllSubCategoryByCategoryId(int id)
            throws DataAccessException;

    public int adjustLthread() throws DataAccessException;

    public int adjustRthread() throws DataAccessException;

    public List<ArticleCategory> listPathToCategory(int lthread, int rthread)
            throws DataAccessException;
}
