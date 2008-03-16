package rax.service;

import java.util.List;

import rax.dao.hibernate.LinkDao;
import rax.model.Link;
import rax.model.LinkCategory;

public class LinkService {

    private LinkDao linkDao;

    public LinkService() {
    }
    
    public void setLink(LinkDao dao) {
        linkDao = dao;
    }

    public long createLink(Link link) {
        return linkDao.create(link);
    }

    public boolean deleteLink(long id) {
        Link link = linkDao.read(id);
        return (null == link) ? false : linkDao.delete(link);
    }

    public boolean updateLink(long id, Link link) {
        return linkDao.update(link);
    }

    public long getCount(boolean onlyPub) {
        return linkDao.count();
    }
    
    public long getCountByCategory(LinkCategory category, boolean onlyPub) {
        return linkDao.countByCategoryId(category.getId(), onlyPub);
    }
    
    public List<Link> listLinks(int index, int num, boolean onlyPub) {
        return linkDao.list(index, num, onlyPub);
    }

    public List<Link> listLinksByCategory(LinkCategory category, int index,
            int num, boolean onlyPub) {
        return linkDao.listByCategoryId(category.getId(), index, num, onlyPub);
    }

}
