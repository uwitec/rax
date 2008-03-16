package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.FaqDao;
import rax.model.Faq;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FaqDaoImpl extends SqlMapClientDaoSupport implements FaqDao {

    private final static Logger logger = Logger.getLogger(FaqDao.class);

    @Override
    public Long create(Faq newInstance) {
        Long ret = new Long(0);
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.insert("createFaq", newInstance);
            ret = (Long) sqlMap.queryForObject("lastInsert");
        } catch (Exception ex) {
            logger.error(ex.toString());
            ret = Long.valueOf(0);
        }
        return ret;
    }

    @Override
    public Faq read(Long id) {
        Faq obj = null;
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            obj = (Faq) sqlMap.queryForObject("readLink", id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            obj = null;
        }
        return obj;
    }

    @Override
    public boolean update(Faq transientObject) {
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.update("updateFaq", transientObject);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Faq persistentObject) {
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.delete("deleteFaq", persistentObject.getId());
        } catch (Exception ex) {
            logger.error(ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public long count() {
        return count(false);
    }

    public long count(boolean bOnlyPub) {
        Long num = new Long(0);
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.queryForObject(bOnlyPub ? "countPubFaq" : "countAllFaq");
        } catch (Exception ex) {
            logger.error(ex.toString());
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    @Override
    public List<Faq> list(int index, int num) {
        return list(index, num, false);
    }

    public List<Faq> list(int index, int num, boolean bOnlyPub) {
        List<Faq> items = null;
        SqlMapClient sqlMap = getSqlMapClient();
        Map param = new HashMap();
        try {
            param.put("index", index);
            param.put("num", num);
            items = sqlMap.queryForList(bOnlyPub ? "listPubFaq" : "listFaq",
                    param);
        } catch (Exception ex) {
            logger.error(ex.toString());
            items = null;
        }
        return items;
    }

    @Override
    public List<Faq> listAll() {
        return listAll(false);
    }

    public List<Faq> listAll(boolean bOnlyPub) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList(bOnlyPub ? "listAllPubFaq" : "listAllFaq");
        /*
        List<Faq> items = null;
        SqlMapClient sqlMap = getSqlMapClient();
                
        try {
            items = sqlMap.queryForList(bOnlyPub ? "listAllPubFaq"
                    : "listAllFaq");
        } catch (Exception ex) {
            logger.error("FaqDaoImpl::listAll() " + ex.toString());
            items = null;
        }
        return items;
        */
    }

}
