package rax.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import rax.dao.LinkCategoryDao;
import rax.model.LinkCategory;
import rax.util.HibernateUtil;

public class LinkCategoryDaoImpl implements LinkCategoryDao {

    private final static Logger logger = Logger
            .getLogger(LinkCategoryDaoImpl.class);

    @Override
    public Long create(LinkCategory newInstance) {
        Long ret = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();
            session.save(newInstance);
            session.getTransaction().commit();
            ret = newInstance.getId();
        } catch (HibernateException ex) {
            ret = Long.valueOf(0);
        }
        return ret;
    }

    @Override
    public boolean delete(LinkCategory persistentObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            LinkCategory category = (LinkCategory) session.load(
                    LinkCategory.class, persistentObject.getId());
            session.delete(category);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

    @Override
    public LinkCategory read(Long id) {
        LinkCategory category = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            category = (LinkCategory) session.load(LinkCategory.class, id);

            logger.trace("Dao:read");
            logger.trace(String.valueOf(category.getId()));
            logger.trace(category.getTitle());
            logger.trace(category.getSummary());

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            logger.error(ex.toString());
            category = null;
        }
        return category;
    }

    @Override
    public boolean update(LinkCategory transientObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();
            LinkCategory obj = (LinkCategory) session.load(LinkCategory.class,
                    transientObject.getId());

            obj = transientObject;

            session.save(obj);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

    @Override
    public long count() {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM LinkCategory";
            Query query = session.createQuery(hql);
            num = (Long) query.iterate().next();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    @Override
    public List<LinkCategory> list(int index, int num) {
        List<LinkCategory> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM LinkCategory AS category";
            Query query = session.createQuery(hql);
            query.setFirstResult(index);
            if (num > 0) {
                query.setMaxResults(num);
            }
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

    @Override
    public List<LinkCategory> listAll() {
        List<LinkCategory> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM LinkCategory AS category";
            Query query = session.createQuery(hql);
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

}
