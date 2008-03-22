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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ArticleCategory read(int id) throws DataAccessException {
        return (ArticleCategory) getSqlMapClientTemplate().queryForObject(
                "readLinkCategory", id);
    }

    @Override
    public int update(ArticleCategory transientObject)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(ArticleCategory persistentObject)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteByCategoryId(int id) throws DataAccessException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int countByCategoryId(int id) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countArticleCategoryByCategoryId", id);
    }

    @Override
    public List<ArticleCategory> listAll() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("listAllLinkCategory");
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

}
