package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.*;

public class ArticleContentDaoImpl extends SqlMapClientDaoSupport implements
        ArticleContentDao {

    @Override
    public int create(int id, List<String> newInstance)
            throws DataAccessException {
        int i = 1;
        Map param = new HashMap();
        for (String content : newInstance) {
            param.put("articleId", id);
            param.put("pageId", i++);
            param.put("content", content);
            getSqlMapClientTemplate().insert("createArticleContent", param);
        }
        return i;
    }

    @Override
    public String read(int id, int pageId) throws DataAccessException {
        Map param = new HashMap();
        param.put("articleId", id);
        param.put("pageId", pageId);
        return (String) getSqlMapClientTemplate().queryForObject(
                "readArticleContent", param);
    }

    @Override
    public int update(int id, List<String> transientObject)
            throws DataAccessException {
        deleteByArticleId(id);
        return create(id, transientObject);
    }

    @Override
    public int deleteByArticleId(int id) throws DataAccessException {
        return getSqlMapClientTemplate().delete(
                "deleteArticleContentByArticleId", id);
    }

    @Override
    public int countByArticleId(int id) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countArticleContentByArticleId", id);
    }

    @Override
    public List<String> listByArticleId(int id) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                "listArticleContentByArticleId", id);
    }

}
