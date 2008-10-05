package erp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Stat;
import erp.model.StatFee;
import erp.service.ExpressService;
import erp.service.StatService;
import erp.service.UtilService;

public class StatAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(StatAction.class);

    private StatService statService = null;
    private ExpressService expressService = null;
    private UtilService utilService = null;

    private double storeAmount;
    private List<Stat> weekProfitList;
    private List<Stat> monthProfitList;

    private String startDate;
    private String endDate;
    private List<StatFee> feeList;

    @Override
    public String execute() throws Exception {
        try {
            storeAmount = statService.getStoreAmount();
            weekProfitList = statService.listStatByDay(0, 7);
            monthProfitList = statService.listStatByMonth(3);
            startDate = utilService.getLastFeeDateStr();
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String statFee() throws Exception {
        // AJAX 方法
        try {
            Date from;
            Date to;
            Map<Integer, String> expressMap;

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            from = df.parse(startDate);
            to = df.parse(endDate);

            feeList = statService.listFeeByDay(from, to);
            expressMap = expressService.getExpressSel();
            for (StatFee st : feeList) {
                st.setExpressName(expressMap.get(st.getExpressId()));
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveFeeStatDate() {
        // AJAX 方法
        endDate = utilService.setLastFeeDate(endDate) ? "OK" : "ERR";
        logger.info("saveFeeStatDate:" + endDate);
        return SUCCESS;
    }

    public void setUtilService(UtilService utilService) {
        this.utilService = utilService;
    }

    public void setStatService(StatService statService) {
        this.statService = statService;
    }

    public void setExpressService(ExpressService expressService) {
        this.expressService = expressService;
    }

    public double getStoreAmount() {
        return storeAmount;
    }

    public void setStoreAmount(double storeAmount) {
        this.storeAmount = storeAmount;
    }

    public List<Stat> getWeekProfitList() {
        return weekProfitList;
    }

    public void setWeekProfitList(List<Stat> weekProfitList) {
        this.weekProfitList = weekProfitList;
    }

    public List<Stat> getMonthProfitList() {
        return monthProfitList;
    }

    public void setMonthProfitList(List<Stat> monthProfitList) {
        this.monthProfitList = monthProfitList;
    }

    public List<StatFee> getFeeList() {
        return feeList;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
