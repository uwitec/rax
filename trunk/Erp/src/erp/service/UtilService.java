package erp.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import erp.dao.UtilDao;
import erp.model.Util;

public class UtilService {

    private final static Logger logger = Logger.getLogger(UtilService.class);

    private UtilDao utilDao;

    public UtilService() {
    }

    public String getLastFeeDateStr() {
        // 取得快递费用结算日期
        logger.info("getLastFeeDateStr");
        Util u = utilDao.read("last_fee_date");
        String ret = (null == u) ? "" : u.getValue();
        logger.info("getLastFeeDateStr:" + ret);
        return ret;
    }

    public boolean setLastFeeDate(String lastFeeDate) {
        // 设置最近快递费用结算日期
        boolean bFlag = true;
        Util u = null;
        logger.info("setLastFeeDateStr:" + String.valueOf(lastFeeDate));
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = f.parse(lastFeeDate);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, 1);
            lastFeeDate = f.format(c.getTime());
        } catch (Exception ex) {
            logger.info("setLastFeeDate parse date failed:" + ex.toString());
        }
        try {
            u = utilDao.read("last_fee_date");
            if (null == u) {
                utilDao.create(new Util("last_fee_date", lastFeeDate));
            } else {
                u.setValue(lastFeeDate);
                utilDao.update(u);
            }
        } catch (DataAccessException ex) {
            logger.error("setLastFeeDate failed:" + ex.toString());
            bFlag = false;
        }
        return bFlag;
    }

    public UtilDao getUtilDao() {
        return utilDao;
    }

    public void setUtilDao(UtilDao utilDao) {
        this.utilDao = utilDao;
    }

}
