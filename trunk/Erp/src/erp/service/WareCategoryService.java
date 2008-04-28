package erp.service;

import java.util.List;

import erp.dao.WareCategoryDao;
import erp.model.WareCategory;


public class WareCategoryService {

    private WareCategoryDao wareCategoryDao;

    public WareCategoryService() {
    }

    public WareCategory getWareById(int id) {
        return wareCategoryDao.read(id);
    }

    public int createWare(WareCategory ware) {
        return wareCategoryDao.create(ware);
    }

    public boolean deleteWare(int id) {
        boolean ret = false;
        WareCategory obj = wareCategoryDao.read(id);
        if (null != obj) {
            wareCategoryDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateWare(WareCategory obj) {
        wareCategoryDao.update(obj);
        return true;
    }

    public int getCount() {
        return wareCategoryDao.count();
    }

    public List<WareCategory> list(int index, int num) {
        return wareCategoryDao.list(index, num);
    }
    
    public void setWareCategoryDao(WareCategoryDao wareCategoryDao) {
        this.wareCategoryDao = wareCategoryDao;
    }
    
}
