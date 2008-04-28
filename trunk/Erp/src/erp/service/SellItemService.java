package erp.service;

import java.util.List;

import erp.dao.SellItemDao;
import erp.model.SellItem;

public class SellItemService {
    
    private SellItemDao sellItemDao;

    public SellItemService() {
    }

    public SellItem getSellItemById(int id) {
        return sellItemDao.read(id);
    }

    public int createSellItem(SellItem ware) {
        return sellItemDao.create(ware);
    }

    public boolean deleteSellItem(int id) {
        boolean ret = false;
        SellItem obj = sellItemDao.read(id);
        if (null != obj) {
            sellItemDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateSellItem(SellItem obj) {
        sellItemDao.update(obj);
        return true;
    }

    public List<SellItem> list() {
        return sellItemDao.list();
    }
    
    public void setSellItemDao(SellItemDao dao) {
        sellItemDao = dao;
    }
    
 
    
}
