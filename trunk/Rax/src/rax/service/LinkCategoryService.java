package rax.service;

import java.util.List;

import rax.dao.hibernate.LinkCategoryDao;
import rax.dao.hibernate.LinkDao;
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
        LinkCategory category = linkCategoryDao.read(id);
        boolean bFlag = linkDao.deleteByCategoryId(category.getId());
        return (null != category && bFlag) ? linkCategoryDao.delete(category)
                : false;
    }

    public boolean updateCategory(long id, LinkCategory category) {
        return linkCategoryDao.update(category);
    }

    public long getCount(boolean onlyPub) {
        return linkCategoryDao.count();
    }

    public List<LinkCategory> listAllCategorys() {
        return linkCategoryDao.listAll();
    }

}
