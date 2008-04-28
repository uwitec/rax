package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.SellDao;
import erp.model.Sell;

public class SellDaoImpl extends SqlMapClientDaoSupport implements
        SellDao {

    @Override
    public int create(Sell newInstance) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().insert("Sell.create",
                newInstance);
    }

    @Override
    public Sell read(int id) throws DataAccessException {
        return (Sell) getSqlMapClientTemplate().queryForObject(
                "Sell.read", id);
    }

    @Override
    public int update(Sell transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("Sell.update",
                transientObject);
    }

    @Override
    public int delete(Sell persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("Sell.delete",
                persistentObject.getId());
    }

    @Override
    public int count() throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "Sell.count");
    }

    @Override
    public List<Sell> list(int index, int num) throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        return getSqlMapClientTemplate().queryForList("Sell.list", param);
    }

}
