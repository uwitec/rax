package erp.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Ware;
import erp.service.WareService;

public class WareAutoCompleteAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger
            .getLogger(WareAutoCompleteAction.class);

    private WareService wareService = null;
    private String keyword;
    private String barcode;
    private List<String[]> options;
    private List<Ware> wareList;

    @Override
    public String execute() throws Exception {
        //logger.info("finded.");
        if ((barcode != null) && (barcode.isEmpty() == false)) {
            logger.info("findByBarcode:" + barcode);
            wareList = wareService.findByBarcode(barcode);
        } else if ((keyword != null) && keyword.isEmpty() == false) {
            logger.info("findByKeywords:" + keyword);
            wareList = wareService.findByKeywords(keyword);
        }

        options = new ArrayList<String[]>();
        if (wareList != null) {
            for (Ware w : wareList) {
                options.add(new String[] { w.getName(), w.getBarcode() });
            }
        }
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

    public List<String[]> getOptions() {
        return options;
    }

}
