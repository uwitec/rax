package erp.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.service.SellService;

public class SellAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(SellAction.class);

    private SellService sellService = null;

    private int id;
    private String customerName;
    private String customerAddress;
    private String customerPhone1;
    private String customerPhone2;
    private String customerPostCode;
    private String customerWangwang;
    private String fee;
    private String feeReal;
    private boolean print;
    private String expressId;
    private String expressBarcode;
    
    Map printSel;
    
    private List<Sell> sellList;
    private int page = 1;
    private int pagePer = 12;
    private int count;

    public String list() throws Exception {
        count = sellService.getCount();
        sellList = sellService.list((page - 1) * pagePer, pagePer);
        return SUCCESS;
    }

    public String get() throws Exception {      
        printSel = new HashMap();
        printSel.put("未打印", false);
        printSel.put("已打印", true);
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
                print = s.isPrint();
                expressId = String.valueOf(s.getExpressId());
                expressBarcode = s.getExpressBarcode();
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
        obj.setCustomerName(customerName);
        obj.setCustomerAddress(customerAddress);
        obj.setCustomerPhone1(customerPhone1);
        obj.setCustomerPhone2(customerPhone2);
        obj.setCustomerPostCode(customerPostCode);
        obj.setCustomerWangwang(customerWangwang);
        obj.setFee(Double.parseDouble(fee));
        obj.setFeeReal(Double.parseDouble(feeReal));
        obj.setCreateDate(new Date());
        obj.setPrint(print);
        obj.setExpressId(Integer.parseInt(expressId));
        obj.setExpressBarcode(expressBarcode);
        try {
            if (id > 0) {
                sellService.updateSell(obj);
                logger.error("Save: " + id);
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

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public String getExpressId() {
        return expressId;
    }

    public void setExpressId(String expressId) {
        this.expressId = expressId;
    }

    public String getExpressBarcode() {
        return expressBarcode;
    }

    public void setExpressBarcode(String expressBarcode) {
        this.expressBarcode = expressBarcode;
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

    public Map getPrintSel() {
        return printSel;
    }

    public void setPrintSel(Map printSel) {
        this.printSel = printSel;
    }

}
