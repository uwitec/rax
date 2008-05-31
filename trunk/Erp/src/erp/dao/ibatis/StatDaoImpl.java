package erp.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.StatDao;
import erp.model.Stat;

public class StatDaoImpl extends SqlMapClientDaoSupport implements StatDao {

    @Override
    public double storeAmount() throws DataAccessException {
        return (Double) getSqlMapClientTemplate()
                .queryForObject("Stat.storeAmount");
    }
    
    @Override
    public List<Stat> listProfitByDay(int index, int num)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        return getSqlMapClientTemplate()
                .queryForList("Stat.profitByDay", param);
    }
    
    @Override
    public List<Stat> listCountByDay(int index, int num)
            throws DataAccessException {
        Map param = new HashMap();
        param.put("index", index);
        param.put("number", num);
        return getSqlMapClientTemplate()
                .queryForList("Stat.countByDay", param);
    }
    
}
