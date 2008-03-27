package rax.service;

import java.util.List;

import rax.dao.LinkDao;
import rax.model.Link;
import rax.model.LinkCategory;

public class LinkService {

    private LinkDao linkDao;

    public LinkService() {
    }

    public Link getLinkById(int id) {
        return linkDao.read(id);
    }

    public int createLink(Link link) {
        return linkDao.create(link);
    }

    public boolean deleteLink(int id) {
        boolean ret = false;
        Link link = linkDao.read(id);
        if (null != link) {
            linkDao.delete(link);
            ret = true;
        }
        return ret;
    }

    public boolean updateLink(int id, Link link) {
        linkDao.update(link);
        return true;
    }

    public int getCount(boolean onlyPub) {
        return linkDao.count();
    }

    public int getCountByCategory(LinkCategory category, boolean onlyPub) {
        return linkDao.countByCategoryId(category.getId(), onlyPub);
    }

    public List<Link> listLinks(int index, int num, boolean onlyPub) {
        return linkDao.list(index, num, onlyPub);
    }

    public List<Link> listLinksByCategory(LinkCategory category, int index,
            int num, boolean onlyPub) {
        return linkDao.listByCategoryId(category.getId(), index, num, onlyPub);
    }

    public void setLinkDao(LinkDao dao) {
        linkDao = dao;
    }
}
