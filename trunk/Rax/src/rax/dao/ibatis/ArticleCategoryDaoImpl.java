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
        return (Integer) getSqlMapClientTemplate().insert("createArticleCategory",
                newInstance);
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
    public int countByCategoryId(int id) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countArticleCategoryByCategoryId", id);
    }

    @Override
    public List<ArticleCategory> listAll() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("listAllArticleCategory");
    }

    @Override
    public List<ArticleCategory> listByCategoryId(int id, int index, int num)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        param.put("categoryId", id);
        return getSqlMapClientTemplate().queryForList(
                "listArticleCategoryByCategoryId", param);
    }

    @Override
    public List<ArticleCategory> listAllByCategoryId(int id)
            throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                "listArticleCategoryByCategoryId", id);
    }

    @Override
    public List<ArticleCategory> listSubCategoryByCategoryId(int id, int index,
            int num) throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        param.put("categoryId", id);
        return getSqlMapClientTemplate().queryForList(
                "listSubArticleCategoryByCategoryId", param);
    }

    @Override
    public List<ArticleCategory> listAllSubCategoryByCategoryId(int id)
            throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                "listAllSubArticleCategoryByCategoryId", id);
    }

    @Override
    public int adjustLthread() throws DataAccessException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int adjustRthread() throws DataAccessException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ArticleCategory> listPathToCategory(int lthread, int rthread)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("lThread", lthread);
        param.put("rThread", rthread);
        return getSqlMapClientTemplate().queryForList("listPathToCategory",
                param);
    }
}
