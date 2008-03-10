package rax.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import rax.model.Faq;
import rax.model.Link;
import rax.util.HibernateUtil;

public class FaqDao implements GenericDao<Faq, Long> {
    
    private final static Logger logger = Logger
    .getLogger(FaqDao.class);

    @Override
    public long count() {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM Faq";
            Query query = session.createQuery(hql);
            num = (Long) query.iterate().next();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    @Override
    public List<Faq> list(int index, int num) {
        return list(index, num, false);
    }

    public List<Faq> list(int index, int num, boolean bOnlyPub) {
        List<Faq> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = bOnlyPub ? "FROM Faq AS faq WHERE faq.pub=true"
                    : "FROM Faq";
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
    public List<Faq> listAll() {
        return listAll(false);
    }

    public List<Faq> listAll(boolean bOnlyPub) {
        List<Faq> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = bOnlyPub ? "FROM Faq AS faq WHERE faq.pub=true"
                    : "FROM Faq";
            Query query = session.createQuery(hql);
            items = query.list();
            
            /*
            Faq faq;
            for (Iterator it = query.iterate(); it.hasNext(); ) {
                faq = (Faq) it.next();
                logger.error(String.valueOf(faq.getId()));
                logger.error(faq.getQuestion());
                logger.error(faq.getAnswer());
                logger.error(faq.getPubDate().toString());
            }
            */

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            logger.error(ex.toString());
            items = null;
        }
        return items;
    }

    @Override
    public Long create(Faq newInstance) {
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
    public boolean delete(Faq persistentObject) {
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

    @Override
    public Faq read(Long id) {
        Faq faq = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            faq = (Faq) session.load(Faq.class, id);

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            faq = null;
        }
        return faq;
    }

    @Override
    public boolean update(Faq transientObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();
            Faq obj = (Faq) session.load(Faq.class, transientObject.getId());

            obj = transientObject;

            session.save(obj);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

}
