package erp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import erp.dao.StatDao;
import erp.model.Stat;
import erp.model.StatFee;

public class StatService {

    private final static Logger logger = Logger.getLogger(StatService.class);

    private StatDao statDao;

    public StatService() {
    }

    public double getStoreAmount() {
        return statDao.getStoreAmount();
    }

    public List<StatFee> listFeeByDay(Date from, Date to) {
        return statDao.listFeeByDay(from, to);
    }

    public List<Stat> listStatByDay(int index, int num) {
        List<Stat> lstProfit = statDao.listProfitByDay(index, num);
        List<Stat> lstCount = statDao.listCountByDay(index, num);
        for (Stat st : lstCount) {
            for (Stat t : lstProfit) {
                if (st.getStatDate().equals(t.getStatDate())) {
                    t.setNumber(st.getNumber());
                    break;
                }
            }
        }
        return lstProfit;
    }

    public List<Stat> listStatByMonth(int num) {

        Date start = new Date();
        Date end = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(start);

        // logger.info("Calendar.YEAR:" + c.get(Calendar.YEAR));
        // logger.info("Calendar.MONTH:" + c.get(Calendar.MONTH));
        // logger.info("Calendar.DATE:" + c.get(Calendar.DATE));

        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DATE, 1);
        end = c.getTime();

        c.add(Calendar.MONTH, -num);
        start = c.getTime();

        logger.info("start:" + start.toString() + " end:" + end.toString());

        List<Stat> lstRet = new ArrayList<Stat>();
        List<Stat> lstProfit = statDao.listProfitByMonth(start, end);
        List<Stat> lstCount = statDao.listCountByMonth(start, end);
        boolean flag;

        for (Stat st : lstProfit) {
            c.setTime(st.getStatDate());
            c.set(Calendar.DATE, 1);
            flag = false;
            for (Stat t : lstRet) {
                if (c.getTime().equals(t.getStatDate())) {
                    t.setProfit(t.getProfit() + st.getProfit());
                    t.setAmount(t.getAmount() + st.getAmount());
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                Stat s = new Stat();
                s.setStatDate(c.getTime());
                s.setProfit(st.getProfit());
                s.setAmount(st.getAmount());
                lstRet.add(s);
            }
        }

        for (Stat st : lstCount) {
            c.setTime(st.getStatDate());
            c.set(Calendar.DATE, 1);
            flag = false;
            for (Stat t : lstRet) {
                if (c.getTime().equals(t.getStatDate())) {
                    t.setNumber(t.getNumber() + st.getNumber());
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                Stat s = new Stat();
                s.setStatDate(c.getTime());
                s.setNumber(st.getNumber());
                lstRet.add(s);
            }
        }

        return lstRet;
    }

    public StatDao getStatDao() {
        return statDao;
    }

    public void setStatDao(StatDao statDao) {
        this.statDao = statDao;
    }

}
