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

    public int getCount(boolean bOnlyPub) {
        return linkDao.count(bOnlyPub);
    }

    public int getCountByCategory(LinkCategory category, boolean bOnlyPub) {
        return linkDao.countByCategoryId(category.getId(), bOnlyPub);
    }

    public List<Link> listLinks(int index, int num, boolean bOnlyPub) {
        return linkDao.list(index, num, bOnlyPub);
    }

    public List<Link> listLinksByCategory(LinkCategory category, int index,
            int num, boolean bOnlyPub) {
        return linkDao.listByCategoryId(category.getId(), index, num, bOnlyPub);
    }

    public void setLinkDao(LinkDao dao) {
        linkDao = dao;
    }
}
