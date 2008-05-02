package erp.action;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Ware;
import erp.service.WareService;

public class WareAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(WareAction.class);

    private WareService wareService = null;

    private int id;
    private String name;
    private String cost;
    private String price;
    private String barcode;
    private String number;

    private List<Ware> wareList;
    private int page = 1;
    private int pagePer = 30;
    private int count;

    public String list() throws Exception {
        count = wareService.getCount();
        wareList = wareService.list((page - 1) * pagePer, pagePer);
        return SUCCESS;
    }

    public String get() throws Exception {
        try {
            DecimalFormat f = new DecimalFormat("###0.00");
            Ware w = wareService.getWareById(id);
            if (w != null) {
                name = w.getName();
                barcode = w.getBarcode();
                cost = f.format(w.getCost());
                price = f.format(w.getPrice());
                number = String.valueOf(w.getNumber());
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            wareService.deleteWare(id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        Ware obj = new Ware();
        obj.setId(id);
        obj.setName(name);
        obj.setBarcode(barcode);
        if (cost.isEmpty() == false)
            obj.setCost(Double.parseDouble(cost));
        if (number.isEmpty() == false)
            obj.setNumber(Integer.parseInt(number));
        if (price.isEmpty() == false)
            obj.setPrice(Double.parseDouble(price));
        try {
            if (id > 0) {
                wareService.updateWare(obj);
            } else
                wareService.createWare(obj);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public void setWareService(WareService service) {
        wareService = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Ware> getWareList() {
        return wareList;
    }

    public int getPage() {
        return page;
    }

    public int getPagePer() {
        return pagePer;
    }

    public int getCount() {
        return count;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPagePer(int pagePer) {
        this.pagePer = pagePer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
