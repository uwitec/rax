package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.ArticleDao;
import rax.model.Article;

public class ArticleDaoImpl extends SqlMapClientDaoSupport implements
        ArticleDao {

    @Override
    public int create(Article newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "createArticle", newInstance));
        return newInstance.getId();
    }

    @Override
    public Article read(int id) throws DataAccessException {
        return (Article) getSqlMapClientTemplate().queryForObject(
                "readArticle", id);
    }

    @Override
    public int update(Article transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("updateArticle",
                transientObject);
    }

    @Override
    public int delete(Article persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteArticle",
                persistentObject.getId());
    }

    @Override
    public int deleteByCategoryId(int id) throws DataAccessException {
        return getSqlMapClientTemplate()
                .delete("deleteArticleByCategoryId", id);
    }

    @Override
    public int count(boolean bOnlyPub) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubArticle" : "countAllArticle");
    }

    @Override
    public int countByCategoryId(int id, boolean bOnlyPub)
            throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubArticleByCategoryId"
                        : "countAllArticleByCategoryId", id);
    }

    @Override
    public List<Article> listByCategoryId(int id, int index, int num,
            boolean bOnlyPub) throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        param.put("categoryId", id);
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listPubArticleByCategoryId"
                        : "listArticleByCategoryId", param);
    }

    @Override
    public List<Article> listAllByCategoryId(int id, boolean bOnlyPub)
            throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listAllPubArticleByCategoryId"
                        : "listAllArticleByCategoryId", id);
    }

}
