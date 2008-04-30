package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.LinkDao;
import rax.model.Link;

public class LinkDaoImpl extends SqlMapClientDaoSupport implements LinkDao {

    public int create(Link newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "createLink", newInstance));
        return newInstance.getId();
    }

    @Override
    public Link read(int id) throws DataAccessException {
        return (Link) getSqlMapClientTemplate().queryForObject("readLink", id);
    }

    @Override
    public int update(Link transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("updateLink", transientObject);
    }

    @Override
    public int delete(Link persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteLink",
                persistentObject.getId());
    }

    public int deleteByCategoryId(int id) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteLinkByCategoryId", id);
    }

    @Override
    public int count(boolean bOnlyPub) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubLink" : "countAllLink");
    }

    public int countByCategoryId(int id) throws DataAccessException {
        return countByCategoryId(id, false);
    }

    public int countByCategoryId(int id, boolean bOnlyPub)
            throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubLinkByCategoryId"
                        : "countAllLinkByCategoryId", id);
    }

    public List<Link> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listPubLink" : "listLink", param);
    }

    public List<Link> listByCategoryId(int id, int index, int num)
            throws DataAccessException {
        return listByCategoryId(id, index, num, false);
    }

    public List<Link> listByCategoryId(int id, int index, int num,
            boolean bOnlyPub) throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        param.put("categoryId", id);
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listPubLinkByCategoryId" : "listLinkByCategoryId",
                param);
    }

    public List<Link> listAll(boolean bOnlyPub) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listAllPubLink" : "listAllLink");
    }

    public List<Link> listAllByCategoryId(int id) throws DataAccessException {
        return listAllByCategoryId(id, false);
    }

    public List<Link> listAllByCategoryId(int id, boolean bOnlyPub)
            throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listAllPubLinkByCategoryId"
                        : "listAllLinkByCategoryId", id);
    }

}
