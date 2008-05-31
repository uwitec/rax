package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Stat;
import erp.service.StatService;

public class StatAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(StatAction.class);

    private StatService statService = null;

    private double restore;
    private List<Stat> weekProfitList;

    @Override
    public String execute() throws Exception {
        try {
            restore = statService.getRestore();
            weekProfitList = statService.listStatByDay(0, 7);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public StatService getStatService() {
        return statService;
    }

    public void setStatService(StatService statService) {
        this.statService = statService;
    }

    public double getRestore() {
        return restore;
    }

    public void setRestore(double restore) {
        this.restore = restore;
    }

    public List<Stat> getWeekProfitList() {
        return weekProfitList;
    }

    public void setWeekProfitList(List<Stat> weekProfitList) {
        this.weekProfitList = weekProfitList;
    }

}
