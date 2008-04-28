package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.WareGroupingDao;
import erp.model.Ware;

public class WareGroupingDaoImpl extends SqlMapClientDaoSupport implements
        WareGroupingDao {

    @Override
    public int create(int wareId, int categoryId) throws DataAccessException {
        Map param = new HashMap();
        param.put("wareId", wareId);
        param.put("categoryId", categoryId);
        return (Integer) getSqlMapClientTemplate().insert("WareGrouping.create", param);
    }

    @Override
    public int delete(int id) throws DataAccessException {
        return getSqlMapClientTemplate().delete("WareGrouping.delete", id);
    }
    
    @Override
    public List<Ware> listWareByCategoryId(int categoryId) {
        return getSqlMapClientTemplate().queryForList("WareGrouping.list", categoryId);
    }
    
}
