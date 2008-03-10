package rax.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import rax.model.Picture;
import rax.util.HibernateUtil;

public class PictureDao implements GenericDao<Picture, Long> {

    @Override
    public long count() {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM Picture";
            Query query = session.createQuery(hql);
            num = (Long) query.iterate().next();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    public long countByArticleId(Long id) {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM Picture AS pic WHERE pic.articleId=:id";
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
    public List<Picture> list(int index, int num) {
        List<Picture> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM Picture";
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
    public List<Picture> listAll() {
        List<Picture> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM Picture";
            Query query = session.createQuery(hql);
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

    public List<Picture> listByArticleId(Long id) {
        List<Picture> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM Picture AS pic WHERE pic.articleId=:id";
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
    public Long create(Picture newInstance) {
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
    public boolean delete(Picture persistentObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            Picture picture = (Picture) session.load(Picture.class,
                    persistentObject.getId());
            session.delete(picture);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return false;
    }

    @Override
    public Picture read(Long id) {
        Picture picture = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            picture = (Picture) session.load(Picture.class, id);

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            picture = null;
        }
        return picture;
    }

    @Override
    public boolean update(Picture transientObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();
            Picture picture = (Picture) session.load(Picture.class,
                    transientObject.getId());

            picture = transientObject;
            
            /*
            picture.setTitle(transientObject.getTitle());
            picture.setMimeType(transientObject.getMimeType());
            picture.setSize(transientObject.getSize());
            picture.setSummary(transientObject.getSummary());
            picture.setFileName(transientObject.getFileName());
            picture.setUploadDate(transientObject.getUploadDate());
            */

            session.save(picture);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

}
