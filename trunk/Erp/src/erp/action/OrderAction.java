package erp.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Order;
import erp.model.OrderItem;
import erp.model.Ware;
import erp.service.OrderItemService;
import erp.service.OrderService;
import erp.service.WareService;

public class OrderAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(OrderAction.class);

    private OrderService orderService = null;
    private OrderItemService orderItemService = null;
    private WareService wareService = null;

    private int id;
    private Order order;

    private int status = 0;

    private List<Order> orderList;
    private List<OrderItem> orderItemList;

    private int page = 1;
    private int pagePer = 12;
    private int pageNum = 0;
    private int count;

    private String fileName = "";

    public String list() throws Exception {
        count = orderService.getCount(status);
        pageNum = (count + pagePer - 1) / pagePer;
        pageNum = pageNum > 0 ? pageNum : 1;
        orderList = orderService.list(status, (page - 1) * pagePer, pagePer);
        return SUCCESS;
    }

    public String get() throws Exception {
        try {
            order = orderService.getOrderById(id);
            if (order != null) {
                orderItemList = orderItemService.listByOrder(order);
                for (OrderItem item : orderItemList) {
                    item.setWare(wareService.getWareById(item.getWareId()));
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            order = orderService.getOrderById(id);
            if (order.getStatus() == 0) {
                orderItemList = orderItemService.listByOrder(order);
                for (OrderItem item : orderItemList) {
                    orderItemService.deleteOrderItem(item);
                }
                orderService.deleteOrder(order);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        try {
            Order obj = (id > 0) ? orderService.getOrderById(id) : new Order();
            if (obj.getStatus() == 0) {
                obj.setFee(order.getFee());
            }
            obj.setComment(order.getComment());
            if (id > 0) {
                orderService.updateOrder(obj);
            } else {
                id = orderService.createOrder(obj);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String status() throws Exception {
        try {
            if (id > 0) {
                order = orderService.getOrderById(id);
                int oldStatus = order.getStatus();
                order.setStatus(status);
                orderService.updateOrder(order);

                Ware ware;
                int total = 0;
                double fee;
                orderItemList = orderItemService.listByOrder(order);
                for (OrderItem item : orderItemList) {
                    total += item.getNumber();
                }
                fee = order.getFee() / total;
                for (OrderItem item : orderItemList) {
                    ware = wareService.getWareById(item.getWareId());
                    if (ware.getLowestCost() == 0
                            || item.getCost() < ware.getLowestCost()) {
                        ware.setLowestCost(item.getCost());
                    }
                    ware.setStatus(0);
                    wareService.check(ware, status > oldStatus, item
                            .getNumber(), item.getCost(), fee);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String export() throws Exception {
        order = orderService.getOrderById(id);
        if (order != null) {
            orderItemList = orderItemService.listByOrder(order);
            for (OrderItem item : orderItemList) {
                item.setWare(wareService.getWareById(item.getWareId()));
            }
            fileName = order.getComment()
                    + "-"
                    + new SimpleDateFormat("yyyyMMdd").format(order
                            .getCreateDate());
        }
        return SUCCESS;
    }

    public InputStream getInputStream() throws Exception {
        StringBuffer buf = new StringBuffer();
        buf.append("序号\t产品名称\t数量\t单价\t总价\t\n");
        DecimalFormat df = new DecimalFormat("####.00");
        try {
            int totalNumber = 0;
            double totalAmount = order.getFee();
            int i = 1;
            Ware ware = null;
            for (OrderItem item : orderItemList) {
                double sum = 0;
                ware = item.getWare();
                sum = item.getCost() * item.getNumber();
                totalNumber += item.getNumber();
                totalAmount += sum;
                buf.append(String.valueOf(i++) + "\t");
                buf.append(ware.getName() + "\t");

                buf.append(String.valueOf(item.getNumber()) + "\t");
                buf.append(df.format(item.getCost()) + "\t");
                buf.append(df.format(sum) + "\t");
                buf.append("\n");
            }
            buf.append("\t");
            buf.append("总数\t");
            buf.append(String.valueOf(totalNumber) + "\t");
            buf.append("运费\t");
            buf.append(df.format(order.getFee()) + "\t");
            buf.append("\n");

            buf.append("\t");
            buf.append("\t");
            buf.append("\t");
            buf.append("总额\t");
            buf.append(df.format(totalAmount) + "\t");
            buf.append("\n");
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return new ByteArrayInputStream(buf.toString().getBytes());
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

    public WareService getWareService() {
        return wareService;
    }

    public void setWareService(WareService wareService) {
        this.wareService = wareService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagePer() {
        return pagePer;
    }

    public void setPagePer(int pagePer) {
        this.pagePer = pagePer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getFileName() {
        String ret = fileName;
        try {
            ret = new String(fileName.getBytes(), "ISO8859-1");
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return ret;
    }
}
