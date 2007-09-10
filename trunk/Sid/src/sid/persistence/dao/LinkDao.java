package sid.persistence.dao;

import java.util.*;

import org.apache.log4j.Logger;

import sid.model.*;
import sid.persistence.*;

public class LinkDao implements GenericDao<Link, Long> {

    @Override
    public long count() {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM Link";
            Query query = session.createQuery(hql);
            num = (Long) query.iterate().next();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    public long countByCategoryId(Long id) {
        return countByCategoryId(id, false);
    }

    public long countByCategoryId(Long id, boolean bOnlyPub) {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = bOnlyPub ? "SELECT count(*) FROM Link AS link WHERE link.categoryId=:id AND link.pub=true"
                    : "SELECT count(*) FROM Link AS link WHERE link.categoryId=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", id);
            num = (Long) query.iterate().next();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    @Override
    public List<Link> list(int index, int num) {
        return list(index, num, false);
    }

    public List<Link> list(int index, int num, boolean bOnlyPub) {
        List<Link> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = bOnlyPub ? "FROM Link AS link WHERE link.pub=true"
                    : "FROM Link";
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

    public List<Link> listByCategoryId(Long id, int index, int num) {
        return listByCategoryId(id, index, num, false);
    }

    public List<Link> listByCategoryId(Long id, int index, int num,
            boolean bOnlyPub) {
        List<Link> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = bOnlyPub ? "FROM Link AS link WHERE link.pub=true AND link.categoryId=:id"
                    : "FROM Link AS link WHERE link.categoryId=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", id);
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
    public List<Link> listAll() {
        return listAll(false);
    }

    public List<Link> listAll(boolean bOnlyPub) {
        List<Link> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = bOnlyPub ? "FROM Link AS link WHERE link.pub=true"
                    : "FROM Link";
            Query query = session.createQuery(hql);
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

    public List<Link> listAllByCategoryId(long id) {
        return listAllByCategoryId(id, false);
    }

    public List<Link> listAllByCategoryId(long id, boolean bOnlyPub) {
        List<Link> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = bOnlyPub ? "FROM Link AS link WHERE link.pub=true AND link.categoryId=:id"
                    : "FROM Link AS link WHERE link.categoryId=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", id);
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

    @Override
    public Long create(Link newInstance) {
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
    public boolean delete(Link persistentObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            Link link = (Link) session.load(Link.class, persistentObject
                    .getId());
            session.delete(link);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

    public boolean deleteByCategoryId(Long id) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM Link AS link WHERE link.categoryId=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", id);

            Link link;
            for (Iterator it = query.iterate(); it.hasNext();) {
                link = (Link) it.next();
                session.delete(link);
            }

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Link read(Long id) {
        Link link = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            link = (Link) session.load(Link.class, id);

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            link = null;
        }
        return link;
    }

    @Override
    public boolean update(Link transientObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();
            Link obj = (Link) session.load(Link.class, transientObject.getId());

            obj = transientObject;

            session.save(obj);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

}
