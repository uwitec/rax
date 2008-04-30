package erp.service;

import java.util.List;

import erp.dao.SellDao;
import erp.model.Sell;

public class SellService {

    private SellDao sellDao;

    public SellService() {
    }

    public Sell getSellById(int id) {
        return sellDao.read(id);
    }

    public int createSell(Sell obj) {
        obj.setId(sellDao.create(obj));
        return obj.getId();
    }

    public boolean deleteSell(int id) {
        boolean ret = false;
        Sell obj = sellDao.read(id);
        if (null != obj) {
            sellDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateSell(Sell obj) {
        sellDao.update(obj);
        return true;
    }

    public int getCount() {
        return sellDao.count();
    }

    public List<Sell> list(int index, int num) {
        return sellDao.list(index, num);
    }
    
    public void setSellDao(SellDao dao) {
        sellDao = dao;
    }
    
}
