package erp.service;

import java.util.List;

import erp.dao.OrderDao;
import erp.model.Order;

public class OrderService {

    private OrderDao orderDao;

    public OrderService() {
    }

    public Order getOrderById(int id) {
        return orderDao.read(id);
    }

    public int createOrder(Order obj) {
        return orderDao.create(obj);
    }

    public boolean deleteOrder(Order obj) {
        boolean ret = false;
        if (null != obj) {
            orderDao.delete(obj);
            ret = true;
        }
        return ret;
    }
    
    public boolean deleteOrderById(int id) {
        boolean ret = false;
        Order obj = orderDao.read(id);
        if (null != obj) {
            orderDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateOrder(Order obj) {
        orderDao.update(obj);
        return true;
    }

    public int getCount() {
        return orderDao.count();
    }

    public List<Order> list(int index, int num) {
        return orderDao.list(index, num);
    }
    
    public void setOrderDao(OrderDao dao) {
        orderDao = dao;
    }
    
}
