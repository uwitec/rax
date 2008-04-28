package erp.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import erp.dao.OrderItemDao;
import erp.model.OrderItem;

public class OrderItemDaoImpl extends SqlMapClientDaoSupport implements
        OrderItemDao {

    @Override
    public int create(OrderItem newInstance) throws DataAccessException {
        return (Integer) getSqlMapClientTemplate().insert("OrderItem.create",
                newInstance);
    }

    @Override
    public OrderItem read(int id) throws DataAccessException {
        return (OrderItem) getSqlMapClientTemplate().queryForObject(
                "OrderItem.read", id);
    }

    @Override
    public int update(OrderItem transientObject) throws DataAccessException {
        return getSqlMapClientTemplate().update("OrderItem.update",
                transientObject);
    }

    @Override
    public int delete(OrderItem persistentObject) throws DataAccessException {
        return getSqlMapClientTemplate().delete("OrderItem.delete",
                persistentObject.getId());
    }

    @Override
    public List<OrderItem> list() throws DataAccessException {
        return getSqlMapClientTemplate().queryForList("OrderItem.list");
    }

}
