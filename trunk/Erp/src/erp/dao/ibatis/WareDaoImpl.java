package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.WareDao;
import erp.model.Ware;

public class WareDaoImpl extends SqlMapClientDaoSupport implements WareDao {

    @Override
    public int create(Ware newInstance) throws DataAccessException {
        newInstance.setId((Integer) getSqlMapClientTemplate().insert(
                "Ware.create", newInstance));
        return newInstance.getId();
    }

    @Override
    public List<Ware> findByBarcode(String barcode) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("Ware.findByBarcode",
                barcode);
    }

    @Override
    public List<Ware> findByKeywords(List<String> keywordList)
            throws DataAccessException {
        /*
         * System.out.print("Keywords : "); for (String k : keywordList) {
         * System.out.print(k + " "); } System.out.println();
         */
        return getSqlMapClientTemplate().queryForList("Ware.findByKeywords",
                keywordList);
    }

    @Override
    public List<Ware> findByNum(int min, int max) throws DataAccessException {
        Map param = new HashMap();
        param.put("min", min);
        param.put("max", max);
        // System.out.println("min:" + min + " max:" + max);
        return getSqlMapClientTemplate().queryForList("Ware.findByNum", param);
    }

    @Override
    public List<Ware> fullTextSearch(String content) throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("Ware.fullTextSearch",
                content);
    }

    @Override
    public Ware read(int id) throws DataAccessException {
        return (Ware) getSqlMapClientTemplate().queryForObject("Ware.read", id);
    }

    @Override
    public int update(Ware transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("Ware.update", transientObject);
    }

    @Override
    public int delete(Ware persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("Ware.delete",
                persistentObject.getId());
    }

    @Override
    public int count(int status) throws DataAccessException {
        Map param = new HashMap();
        param.put("status", status);
        return (Integer) getSqlMapClientTemplate().queryForObject("Ware.count",
                param);
    }

    @Override
    public List<Ware> list(int status, int index, int num)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        param.put("status", status);
        return getSqlMapClientTemplate().queryForList("Ware.list", param);
    }

    @Override
    public List<Ware> listByCategoryId(int id, int status)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("id", id);
        param.put("status", status);
        return getSqlMapClientTemplate().queryForList("Ware.listByCategoryId",
                param);
    }

}
