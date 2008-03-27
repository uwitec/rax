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

	public int countChildCategoryByCategoryId(int id) throws DataAccessException;

	public int countSubCategoryByCategoryId(int id)
			throws DataAccessException;

	public List<ArticleCategory> listAll() throws DataAccessException;

	public List<ArticleCategory> listChildByCategoryId(int id)
			throws DataAccessException;

	public List<ArticleCategory> listSubCategoryByCategoryId(int id)
			throws DataAccessException;

	public int adjustLthread(int from, int diff) throws DataAccessException;

	public int adjustRthread(int from, int diff) throws DataAccessException;

	public List<ArticleCategory> listPathToCategory(int lthread, int rthread)
			throws DataAccessException;
}
