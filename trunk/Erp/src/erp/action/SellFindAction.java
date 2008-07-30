package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.service.SellService;

public class SellFindAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(SellFindAction.class);

    private SellService sellService = null;
    private String keyword;
    private List<Sell> sellList;

    @Override
    public String execute() throws Exception {
        if ((keyword != null) && keyword.isEmpty() == false) {
            logger.info("findByKeyword:" + keyword);
            sellList = sellService.findByKeyword(keyword);
        }
        return SUCCESS;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<Sell> getSellList() {
        return sellList;
    }

    public void setSellService(SellService sellService) {
        this.sellService = sellService;
    }

}
