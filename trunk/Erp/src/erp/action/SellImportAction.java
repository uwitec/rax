package erp.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.service.SellService;

public class SellImportAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger
            .getLogger(SellImportAction.class);

    private SellService sellService = null;

    private int sellId;
    private String content;

    @Override
    public String execute() throws Exception {
        if (content == null || content.isEmpty())
            return INPUT;

        try {
            Sell obj;
            if (sellId > 0)
                obj = sellService.getSellById(sellId);
            else
                obj = new Sell();

            String[] info = content.split("，");
            obj.setCustomerName(info[0].trim());
            obj.setCustomerPhone1(info[1].trim());
            if (info.length > 4) {
                obj.setCustomerPhone2(info[2].trim());
                obj.setCustomerAddress(info[3].trim());
                obj.setCustomerPostCode(info[4].trim());
            } else {
                obj.setCustomerAddress(info[2].trim());
                obj.setCustomerPostCode(info[3].trim());
            }

            logger.info("Name:" + obj.getCustomerName());
            logger.info("Phone1:" + obj.getCustomerPhone1());
            logger.info("Phone2:" + obj.getCustomerPhone2());
            logger.info("Address:" + obj.getCustomerAddress());
            logger.info("PostCode:" + obj.getCustomerPostCode());

            if (sellId > 0)
                sellService.updateSell(obj);
            else
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

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
