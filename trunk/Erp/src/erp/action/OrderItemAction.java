package erp.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Order;
import erp.model.OrderItem;
import erp.service.OrderItemService;
import erp.service.OrderService;

public class OrderItemAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger
            .getLogger(OrderItemAction.class);

    private OrderService orderService = null;
    private OrderItemService orderItemService = null;

    private int id;
    private int orderId;
    private OrderItem orderItem;

    public String get() throws Exception {
        try {
            orderItem = orderItemService.getOrderItemById(id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            orderItemService.deleteOrderItemById(id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        try {
            if (orderId == 0) {
                Order order = new Order();
                orderId = orderService.createOrder(order);
            }
            OrderItem newItem = (id > 0) ? orderItemService
                    .getOrderItemById(id) : new OrderItem();
            newItem.setOrderId(orderId);
            newItem.setWareId(orderItem.getWareId());
            newItem.setCost(orderItem.getCost());
            newItem.setNumber(orderItem.getNumber());
            if (id > 0) {
                orderItemService.updateOrderItem(orderItem);
            } else {
                orderItemService.createOrderItem(orderItem);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderItemService getOrderItemService() {
        return orderItemService;
    }

    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

}
