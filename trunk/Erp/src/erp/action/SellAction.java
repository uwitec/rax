package erp.action;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.model.SellItem;
import erp.service.SellItemService;
import erp.service.SellService;
import erp.service.WareService;

public class SellAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(SellAction.class);

    private SellService sellService = null;
    private SellItemService sellItemService = null;
    private WareService wareService = null;

    private int id;
    private String customerName;
    private String customerAddress;
    private String customerPhone1;
    private String customerPhone2;
    private String customerPostCode;
    private String customerWangwang;
    private String fee;
    private String feeReal;
    private int expressId;
    private String comment;
    private String sender;

    Map<Integer, String> expressSel;
    
    private List<Sell> sellList;
    private List<SellItem> sellItemList;
    private int page = 1;
    private int pagePer = 12;
    private int count;

    public String list() throws Exception {
        count = sellService.getCount();
        sellList = sellService.list((page - 1) * pagePer, pagePer);
        return SUCCESS;
    }

    public String get() throws Exception {
        expressSel = new HashMap<Integer, String>();
        expressSel.put(0, "韵达");
        expressSel.put(1, "申通");
        expressSel.put(2, "圆通");
        expressSel.put(3, "中通");
        expressSel.put(4, "天天");
        expressSel.put(5, "顺丰");
        expressSel.put(6, "其他");
        expressSel.put(99, "无");
        try {
            DecimalFormat f = new DecimalFormat("###0.00");
            Sell s = sellService.getSellById(id);
            if (s != null) {
                customerName = s.getCustomerName();
                customerAddress = s.getCustomerAddress();
                customerPhone1 = s.getCustomerPhone1();
                customerPhone2 = s.getCustomerPhone2();
                customerPostCode = s.getCustomerPostCode();
                customerWangwang = s.getCustomerWangwang();
                fee = f.format(s.getFee());
                feeReal = f.format(s.getFeeReal());
                expressId = s.getExpressId();
                comment = s.getComment();
                sender = s.getSender();

                sellItemList = sellItemService.listBySell(s);
                for (SellItem item : sellItemList) {
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
            sellService.deleteSell(id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        Sell obj = new Sell();
        obj.setId(id);
        obj.setCustomerName(customerName.trim());
        obj.setCustomerAddress(customerAddress.trim());
        obj.setCustomerPhone1(customerPhone1.trim());
        obj.setCustomerPhone2(customerPhone2.trim());
        obj.setCustomerPostCode(customerPostCode.trim());
        obj.setCustomerWangwang(customerWangwang.trim());
        obj.setExpressId(expressId);
        obj.setComment(comment.trim());
        obj.setSender(sender.trim());
        if (fee.isEmpty() == false)
            obj.setFee(Double.parseDouble(fee));
        if (feeReal.isEmpty() == false)
            obj.setFeeReal(Double.parseDouble(feeReal));
        try {
            if (id > 0) {
                sellService.updateSell(obj);
            } else
                sellService.createSell(obj);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public SellService getSellService() {
        return sellService;
    }

    public void setSellService(SellService sellService) {
        this.sellService = sellService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone1() {
        return customerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    public String getCustomerPhone2() {
        return customerPhone2;
    }

    public void setCustomerPhone2(String customerPhone2) {
        this.customerPhone2 = customerPhone2;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }

    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    public String getCustomerWangwang() {
        return customerWangwang;
    }

    public void setCustomerWangwang(String customerWangwang) {
        this.customerWangwang = customerWangwang;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFeeReal() {
        return feeReal;
    }

    public void setFeeReal(String feeReal) {
        this.feeReal = feeReal;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public Map<Integer, String> getExpressSel() {
        return expressSel;
    }

    public List<Sell> getSellList() {
        return sellList;
    }

    public void setSellList(List<Sell> sellList) {
        this.sellList = sellList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public WareService getWareService() {
        return wareService;
    }

    public void setWareService(WareService wareService) {
        this.wareService = wareService;
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

    public SellItemService getSellItemService() {
        return sellItemService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSellItemService(SellItemService sellItemService) {
        this.sellItemService = sellItemService;
    }

    public List<SellItem> getSellItemList() {
        return sellItemList;
    }

    public void setSellItemList(List<SellItem> sellItemList) {
        this.sellItemList = sellItemList;
    }

}
