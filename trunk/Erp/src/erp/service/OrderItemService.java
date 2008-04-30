package erp.service;

import java.util.List;

import erp.dao.OrderItemDao;
import erp.model.OrderItem;

public class OrderItemService {
    
    private OrderItemDao orderItemDao;

    public OrderItemService() {
    }

    public OrderItem getOrderItemById(int id) {
        return orderItemDao.read(id);
    }

    public int createOrderItem(OrderItem obj) {
        obj.setId( orderItemDao.create(obj));
        return obj.getId();
    }

    public boolean deleteOrderItem(int id) {
        boolean ret = false;
        OrderItem obj = orderItemDao.read(id);
        if (null != obj) {
            orderItemDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateOrderItem(OrderItem obj) {
        orderItemDao.update(obj);
        return true;
    }

    public List<OrderItem> list() {
        return orderItemDao.list();
    }
    
    public void setOrderItemDao(OrderItemDao dao) {
        orderItemDao = dao;
    }
    
 
    
}
