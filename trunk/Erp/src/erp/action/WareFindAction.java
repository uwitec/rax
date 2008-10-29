package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Ware;
import erp.service.WareService;

public class WareFindAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(WareFindAction.class);

    private WareService wareService = null;
    private String keyword;
    private String barcode;
    private int min;
    private int max;

    private List<Ware> wareList;

    @Override
    public String execute() throws Exception {
        if ((barcode != null) && (barcode.isEmpty() == false)) {
            logger.info("findByBarcode:" + barcode);
            wareList = wareService.findByBarcode(barcode);
        } else if ((keyword != null) && keyword.isEmpty() == false) {
            logger.info("findByKeywords:" + keyword + " between(" + min + ","
                    + max + ")");
            wareList = wareService.findByKeywords(keyword, min, max);
        } else if (min > 0 || max > 0) {
            logger.info("findByNumber:(" + min + "," + max + ")");
            try {
                wareList = wareService.findByNum(min, max);
            } catch (NumberFormatException ex) {
                logger.error("Search from num error:" + ex.toString());
            }
        }
        return SUCCESS;
    }

    public String fulltext_search() throws Exception {
        logger.info("fullTextSearch:" + keyword);
        wareList = wareService.fullTextSearch(keyword);
        return SUCCESS;
    }
    
    public void setWareService(WareService service) {
        wareService = service;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<Ware> getWareList() {
        return wareList;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}
