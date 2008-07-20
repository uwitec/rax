package erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import erp.dao.WareCategoryDao;
import erp.model.WareCategory;

public class WareCategoryService {

    private WareCategoryDao wareCategoryDao;

    public WareCategoryService() {
    }

    public WareCategory getWareCategoryById(int id) {
        return wareCategoryDao.read(id);
    }

    public int createWareCagegory(WareCategory obj) {
        return wareCategoryDao.create(obj);
    }

    public boolean deleteWareCategoryById(int id) {
        boolean ret = false;
        WareCategory obj = wareCategoryDao.read(id);
        if (null != obj) {
            wareCategoryDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean deleteWareCategory(WareCategory obj) {
        boolean ret = false;
        if (null != obj) {
            wareCategoryDao.delete(obj);
            ret = true;
        }
        return ret;
    }

    public boolean updateWareCategory(WareCategory obj) {
        wareCategoryDao.update(obj);
        return true;
    }

    public int getCount() {
        return wareCategoryDao.count();
    }

    public List<WareCategory> list() {
        return wareCategoryDao.list();
    }

    public Map<Integer, String> getMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        List<WareCategory> list = wareCategoryDao.list();
        
        map.put(0, "无分类");
        for (WareCategory category : list) {
            map.put(category.getId(), category.getName());
        }
        return map;
    }

    public void setWareCategoryDao(WareCategoryDao wareCategoryDao) {
        this.wareCategoryDao = wareCategoryDao;
    }

}
