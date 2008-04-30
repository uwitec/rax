package erp.service;

import java.util.List;

import erp.dao.WareDao;
import erp.dao.WareGroupingDao;
import erp.model.Ware;
import erp.model.WareCategory;

public class WareService {

    private WareDao wareDao;
    private WareGroupingDao wareGroupingDao;

    public WareService() {
    }

    public Ware getWareById(int id) {
        return wareDao.read(id);
    }

    public int createWare(Ware obj) {
        obj.setId(wareDao.create(obj));
        return obj.getId();
    }

    public boolean deleteWare(int id) {
        boolean ret = false;
        Ware obj = wareDao.read(id);
        if (null != obj) {
            wareDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateWare(Ware obj) {
        wareDao.update(obj);
        return true;
    }

    public int getCount() {
        return wareDao.count();
    }

    public List<Ware> list(int index, int num) {
        return wareDao.list(index, num);
    }
    
    public List<Ware> listByCategory(WareCategory category) {
        return wareGroupingDao.listWareByCategoryId(category.getId());
    }

    public void setWareDao(WareDao dao) {
        wareDao = dao;
    }
    
    public void setWareGroupingDao(WareGroupingDao wareGroupingDao) {
        this.wareGroupingDao = wareGroupingDao;
    }

}
