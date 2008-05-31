package erp.service;

import java.util.List;

import erp.dao.StatDao;
import erp.model.Stat;

public class StatService {

    private StatDao statDao;

    public StatService() {
    }

    public double getRestore() {
        return statDao.storeAmount();
    }

    public List<Stat> listStatByDay(int index, int num) {
        List<Stat> lstProfit = statDao.listProfitByDay(index, num);
        List<Stat> lstCount = statDao.listCountByDay(index, num); 
        for (Stat st : lstCount) {
            for (Stat t : lstProfit) {
                if (st.getStatDate().equals(t.getStatDate())) {
                    t.setNumber(st.getNumber());
                }
            }
        }
        return lstProfit;
    }

    public StatDao getStatDao() {
        return statDao;
    }

    public void setStatDao(StatDao statDao) {
        this.statDao = statDao;
    }

}
