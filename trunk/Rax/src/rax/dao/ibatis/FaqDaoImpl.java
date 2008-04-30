package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.FaqDao;
import rax.model.Faq;

public class FaqDaoImpl extends SqlMapClientDaoSupport implements FaqDao {

    @Override
    public int create(Faq newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "createFaq", newInstance));
        return newInstance.getId();
    }

    @Override
    public Faq read(int id) throws DataAccessException {
        return (Faq) getSqlMapClientTemplate().queryForObject("readFaq", id);
    }

    @Override
    public int update(Faq transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("updateFaq", transientObject);
    }

    @Override
    public int delete(Faq persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("deleteFaq",
                persistentObject.getId());
    }

    public int count(boolean bOnlyPub) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubFaq" : "countAllFaq");
    }

    public List<Faq> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listPubFaq" : "listFaq", param);
    }

    public List<Faq> listAll(boolean bOnlyPub) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listAllPubFaq" : "listAllFaq");
    }

}
