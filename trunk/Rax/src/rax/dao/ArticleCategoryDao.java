package rax.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import rax.model.ArticleCategory;

public interface ArticleCategoryDao {

    public Long create(ArticleCategory newInstance) throws DataAccessException;

    public ArticleCategory read(Long id) throws DataAccessException;

    public int update(ArticleCategory transientObject)
            throws DataAccessException;

    public int delete(ArticleCategory persistentObject)
            throws DataAccessException;

    public int deleteByCategoryId(Long id) throws DataAccessException;

    public int count() throws DataAccessException;

    public int countByCategoryId(Long id) throws DataAccessException;

    public List<ArticleCategory> list(int index, int num)
            throws DataAccessException;

    public List<ArticleCategory> listByCategoryId(Long id, int index, int num)
            throws DataAccessException;

    public List<ArticleCategory> listAll() throws DataAccessException;

    public List<ArticleCategory> listAllByCategoryId(Long id)
            throws DataAccessException;

    public List<ArticleCategory> listAllSubCategoryByCategoryId(Long id)
            throws DataAccessException;

}
