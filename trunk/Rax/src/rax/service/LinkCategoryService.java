package rax.service;

import java.util.List;

import rax.dao.LinkCategoryDao;
import rax.dao.LinkDao;
import rax.model.LinkCategory;

public class LinkCategoryService {

    private LinkDao linkDao;
    private LinkCategoryDao linkCategoryDao;

    public LinkCategoryService() {
    }

    public LinkCategory getCategoryById(int id) {
        return linkCategoryDao.read(id);
    }

    public int createCategory(LinkCategory category) {
        return linkCategoryDao.create(category);
    }

    public boolean deleteCategory(int id) {
        boolean ret = false;
        LinkCategory category = linkCategoryDao.read(id);
        if (category != null) {
            linkDao.deleteByCategoryId(category.getId());
            linkCategoryDao.delete(category);
            ret = true;
        }
        return ret;
    }

    public boolean updateCategory(LinkCategory category) {
        linkCategoryDao.update(category);
        return true;
    }

    public int getCount(boolean onlyPub) {
        return linkCategoryDao.count();
    }

    public List<LinkCategory> listAllCategorys() {
        return linkCategoryDao.listAll();
    }

    public void setLinkDao(LinkDao dao) {
        linkDao = dao;
    }

    public void setLinkCategoryDao(LinkCategoryDao dao) {
        linkCategoryDao = dao;
    }
}
