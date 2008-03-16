package rax.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import rax.dao.FaqDao;
import rax.dao.LinkDao;
import rax.model.Link;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LinkDaoImpl extends SqlMapClientDaoSupport implements LinkDao {

    private final static Logger logger = Logger.getLogger(FaqDao.class);

    @Override
    public Long create(Link newInstance) {

        Long ret = new Long(0);
        SqlMapClient sqlMap = getSqlMapClient();

        try {
            sqlMap.insert("createLink", newInstance);
            ret = (Long) sqlMap.queryForObject("lastInsert");
        } catch (Exception ex) {
            logger.error(ex.toString());
            ret = Long.valueOf(0);
        }

        return ret;
    }

    @Override
    public Link read(Long id) {
        Link obj = null;
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            obj = (Link) sqlMap.queryForObject("readLink", id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            obj = null;
        }
        return obj;
    }

    @Override
    public boolean update(Link transientObject) {
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.update("updateLink", transientObject);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Link persistentObject) {
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.delete("deleteLink", persistentObject.getId());
        } catch (Exception ex) {
            logger.error(ex.toString());
            return false;
        }
        return true;
    }

    public boolean deleteByCategoryId(Long id) {
        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.delete("deleteLinkByCategoryId", id);
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
            sqlMap.queryForObject(bOnlyPub ? "countPubLink" : "countAllLink");
        } catch (Exception ex) {
            logger.error(ex.toString());
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    public long countByCategoryId(Long id) {
        return countByCategoryId(id, false);
    }

    public long countByCategoryId(Long id, boolean bOnlyPub) {
        Long num = new Long(0);

        SqlMapClient sqlMap = getSqlMapClient();
        try {
            sqlMap.queryForObject(bOnlyPub ? "countPubLinkByCategoryId"
                    : "countAllLinkByCategoryId");
        } catch (Exception ex) {
            logger.error(ex.toString());
            num = Long.valueOf(0);
        }

        return num.longValue();
    }

    @Override
    public List<Link> list(int index, int num) {
        return list(index, num, false);
    }

    public List<Link> list(int index, int num, boolean bOnlyPub) {
        List<Link> items = null;

        SqlMapClient sqlMap = getSqlMapClient();
        Map param = new HashMap();

        try {
            param.put("index", index);
            param.put("num", num);
            items = sqlMap.queryForList(bOnlyPub ? "listPubLink" : "listLink",
                    param);
        } catch (Exception ex) {
            logger.error(ex.toString());
            items = null;
        }

        return items;
    }

    public List<Link> listByCategoryId(Long id, int index, int num) {
        return listByCategoryId(id, index, num, false);
    }

    public List<Link> listByCategoryId(Long id, int index, int num,
            boolean bOnlyPub) {
        List<Link> items = null;

        SqlMapClient sqlMap = getSqlMapClient();
        Map param = new HashMap();

        try {
            param.put("id", id);
            param.put("index", index);
            param.put("num", num);
            items = sqlMap.queryForList(bOnlyPub ? "listPubLinkByCategoryId"
                    : "listLinkByCategoryId", param);
        } catch (Exception ex) {
            logger.error(ex.toString());
            items = null;
        }

        return items;
    }

    @Override
    public List<Link> listAll() {
        return listAll(false);
    }

    public List<Link> listAll(boolean bOnlyPub) {
        List<Link> items = null;

        SqlMapClient sqlMap = getSqlMapClient();

        try {
            items = sqlMap.queryForList(bOnlyPub ? "listAllPubLink"
                    : "listAllLink");
        } catch (Exception ex) {
            logger.error(ex.toString());
            items = null;
        }

        return items;
    }

    public List<Link> listAllByCategoryId(long id) {
        return listAllByCategoryId(id, false);
    }

    public List<Link> listAllByCategoryId(long id, boolean bOnlyPub) {
        List<Link> items = null;
        SqlMapClient sqlMap = getSqlMapClient();

        try {
            items = sqlMap.queryForList(bOnlyPub ? "listAllPubLinkByCategoryId"
                    : "listAllLinkByCategoryId", id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            items = null;
        }
        return items;
    }

}
