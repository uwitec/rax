package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.ArticleCategoryDao;
import rax.model.ArticleCategory;

public class ArticleCategoryDaoImpl extends SqlMapClientDaoSupport implements
        ArticleCategoryDao {

    @Override
    public int create(ArticleCategory newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "createArticleCategory", newInstance));
        return newInstance.getId();
    }

    @Override
    public ArticleCategory read(int id) throws DataAccessException {
        return (ArticleCategory) getSqlMapClientTemplate().queryForObject(
                "readArticleCategory", id);
    }

    @Override
    public int update(ArticleCategory transientObject)
            throws DataAccessException {
        return getSqlMapClientTemplate().update("updateArticleCategory",
                transientObject);
    }

    @Override
    public int delete(ArticleCategory persistentObject)
            throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteArticleCategory",
                persistentObject.getId());
    }

    @Override
    public int countChildCategoryByCategoryId(int id)
            throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countChildArticleCategoryByCategoryId", id);
    }

    @Override
    public int countSubCategoryByCategoryId(int id) throws DataAccessException {
        ArticleCategory category = read(id);
        Map param = new HashMap();
        param.put("lthread", category.getLthread());
        param.put("rthread", category.getRthread());
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countSubArticleCategoryByCategoryId", param);
    }

    @Override
    public List<ArticleCategory> listAll() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("listAllArticleCategory");
    }

    @Override
    public List<ArticleCategory> listChildByCategoryId(int id)
            throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                "listChildArticleCategoryByCategoryId", id);
    }

    @Override
    public List<ArticleCategory> listSubCategoryByCategoryId(int id)
            throws DataAccessException {
        ArticleCategory category = read(id);
        Map param = new HashMap();
        param.put("l", category.getLthread());
        param.put("r", category.getRthread());
        return getSqlMapClientTemplate().queryForList(
                "listSubArticleCategoryByCategoryId", param);
    }

    @Override
    public int adjustLthread(int from, int diff) throws DataAccessException {
        Map param = new HashMap();
        param.put("diff", diff);
        param.put("from", from);
        return getSqlMapClientTemplate().update(
                "adjustArticleCategoryLeftThread", param);
    }

    @Override
    public int adjustRthread(int from, int diff) throws DataAccessException {
        Map param = new HashMap();
        param.put("diff", diff);
        param.put("from", from);
        return getSqlMapClientTemplate().update(
                "adjustArticleCategoryRightThread", param);
    }

    @Override
    public List<ArticleCategory> listPathToCategory(int lthread, int rthread)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("l", lthread);
        param.put("r", rthread);
        return getSqlMapClientTemplate().queryForList("listPathToCategory",
                param);
    }
}
