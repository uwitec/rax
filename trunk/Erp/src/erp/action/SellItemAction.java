package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.model.SellItem;
import erp.service.SellItemService;
import erp.service.SellService;

public class SellItemAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(SellItemAction.class);

    private SellService sellService = null;
    private SellItemService sellItemService = null;

    private int id;
    private int sellId;
    private int wareId;
    private double price;
    private int number;
    
    private List<SellItem> sellItemList;

    public String get() throws Exception {
        try {
            // DecimalFormat f = new DecimalFormat("###0.00");
            SellItem item = sellItemService.getSellItemById(id);
            if (item != null) {
                sellId = item.getSellId();
                wareId = item.getWareId();
                price = item.getPrice();
                number = item.getNumber();
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            sellItemService.deleteSellItem(id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        try {
            if (sellId == 0) {
                Sell sell = new Sell();
                sellId = sellService.createSell(sell);
            }
            SellItem obj = (id > 0) ? sellItemService.getSellItemById(id)
                    : new SellItem();
            obj.setSellId(sellId);
            obj.setWareId(wareId);
            obj.setPrice(price);
            obj.setNumber(number);
            if (id > 0) {
                sellItemService.updateSellItem(obj);
            } else {
                id = sellItemService.createSellItem(obj);
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

    public SellItemService getSellItemService() {
        return sellItemService;
    }

    public void setSellItemService(SellItemService sellItemService) {
        this.sellItemService = sellItemService;
    }

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public int getWareId() {
        return wareId;
    }

    public void setWareId(int wareId) {
        this.wareId = wareId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SellService getSellService() {
        return sellService;
    }

    public void setSellService(SellService sellService) {
        this.sellService = sellService;
    }

    public List<SellItem> getSellItemList() {
        return sellItemList;
    }

    public void setSellItemList(List<SellItem> sellItemList) {
        this.sellItemList = sellItemList;
    }

}
