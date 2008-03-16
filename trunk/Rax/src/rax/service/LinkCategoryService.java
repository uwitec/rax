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

    public void setLinkDao(LinkDao dao) {
        linkDao = dao;
    }

    public void setLinkCategoryDao(LinkCategoryDao dao) {
        linkCategoryDao = dao;
    }

    public LinkCategory getCategoryById(long id) {
        return linkCategoryDao.read(id);
    }

    public long createCategory(LinkCategory category) {
        return linkCategoryDao.create(category);
    }

    public boolean deleteCategory(long id) {
        boolean ret = false;
        LinkCategory category = linkCategoryDao.read(id);
        if (category != null) {
            linkDao.deleteByCategoryId(category.getId());
            linkCategoryDao.delete(category);
            ret = true;
        }
        return ret;
    }

    public boolean updateCategory(long id, LinkCategory category) {
        linkCategoryDao.update(category);
        return true;
    }

    public long getCount(boolean onlyPub) {
        return linkCategoryDao.count();
    }

    public List<LinkCategory> listAllCategorys() {
        return linkCategoryDao.listAll();
    }

}
