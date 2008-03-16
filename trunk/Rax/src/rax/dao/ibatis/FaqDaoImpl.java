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
    public Long create(Faq newInstance) throws DataAccessException {
        return (Long) getSqlMapClientTemplate()
                .insert("createFaq", newInstance);
    }

    @Override
    public Faq read(Long id) throws DataAccessException {
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

    @Override
    public long count() throws DataAccessException {
        return count(false);
    }

    public long count(boolean bOnlyPub) throws DataAccessException {
        return (Long) getSqlMapClientTemplate().queryForObject(
                bOnlyPub ? "countPubFaq" : "countAllFaq");
    }

    @Override
    public List<Faq> list(int index, int num) throws DataAccessException {
        return list(index, num, false);
    }

    public List<Faq> list(int index, int num, boolean bOnlyPub)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("num", num);
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listPubFaq" : "listFaq", param);
    }

    @Override
    public List<Faq> listAll() throws DataAccessException {
        return listAll(false);
    }

    public List<Faq> listAll(boolean bOnlyPub) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(
                bOnlyPub ? "listAllPubFaq" : "listAllFaq");
    }

}
