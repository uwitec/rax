package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.LinkCategoryDao;
import rax.model.LinkCategory;

public class LinkCategoryDaoImpl extends SqlMapClientDaoSupport implements
        LinkCategoryDao {

    @Override
    public int create(LinkCategory newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "createLinkCategory", newInstance));
        return newInstance.getId();
    }

    @Override
    public LinkCategory read(int id) throws DataAccessException {
        return (LinkCategory) getSqlMapClientTemplate().queryForObject(
                "readLinkCategory", id);
    }

    @Override
    public int update(LinkCategory transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("updateLinkCategory",
                transientObject);
    }

    @Override
    public int delete(LinkCategory persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteLinkCategory",
                persistentObject.getId());
    }

    @Override
    public int count() throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "countLinkCategory");
    }

    @Override
    public List<LinkCategory> list(int index, int num)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        return getSqlMapClientTemplate()
                .queryForList("listLinkCategory", param);
    }

    @Override
    public List<LinkCategory> listAll() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("listAllLinkCategory");
    }

}
