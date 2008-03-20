package rax.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import rax.model.ArticleCategory;
import rax.util.HibernateUtil;

public class ArticleCategoryDao implements GenericDao<ArticleCategory, Long> {

    private final static Logger logger = Logger
            .getLogger(ArticleCategoryDao.class);

    @Override
    public long count() {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM ArticleCategory";
            Query query = session.createQuery(hql);
            num = (Long) query.iterate().next();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    public long countByCategoryId(Long id) {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM ArticleCategory AS categoty WHERE category.parentId=:id";
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
    public Long create(ArticleCategory newInstance) {
        Long ret = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();
            session.save(newInstance);
            session.getTransaction().commit();
            ret = Long.valueOf(newInstance.getId());
        } catch (HibernateException ex) {
            ret = Long.valueOf(0);
        }
        return ret;
    }

    @Override
    public boolean delete(ArticleCategory persistentObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            ArticleCategory category = (ArticleCategory) session.load(
                    ArticleCategory.class, persistentObject.getId());
            session.delete(category);

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

            ArticleCategory category = (ArticleCategory) session.load(
                    ArticleCategory.class, id);
            session.delete(category);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<ArticleCategory> list(int index, int num) {
        List<ArticleCategory> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM ArticleCategory AS category";
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

    public List<ArticleCategory> listByCategoryId(Long id, int index, int num) {
        List<ArticleCategory> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM ArticleCategory AS category WHERE category.parentId=:id";
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
    public List<ArticleCategory> listAll() {
        List<ArticleCategory> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM ArticleCategory AS category";
            Query query = session.createQuery(hql);
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

    public List<ArticleCategory> listAllByCategoryId(Long id) {
        List<ArticleCategory> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "FROM ArticleCategory AS category WHERE category.parentId=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", id);
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

    public List<ArticleCategory> listAllSubCategoryByCategoryId(Long id) {
        List<ArticleCategory> items = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            ArticleCategory category = (ArticleCategory) session.load(
                    ArticleCategory.class, id);            
            
            String hql = "FROM ArticleCategory AS category WHERE category.lthread<:lthread AND category.rthread>:rthread";
            Query query = session.createQuery(hql);
            query.setLong("lthread", category.getLthread());
            query.setLong("rthread", category.getRthread());
            items = query.list();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            items = null;
        }
        return items;
    }

    @Override
    public ArticleCategory read(Long id) {
        ArticleCategory category = null;
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            category = (ArticleCategory) session
                    .load(ArticleCategory.class, id);

            logger.trace("Dao:read");
            logger.trace("Id:" + String.valueOf(category.getId()));
            logger.trace("ParentId:" + String.valueOf(category.getParentId()));
            logger.trace(category.getName());
            logger.trace(category.getSummary());
            logger.trace(category.getCreateDate());

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            logger.error(ex.toString());
            category = null;
        }
        return category;
    }

    @Override
    public boolean update(ArticleCategory transientObject) {
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();
            ArticleCategory obj = (ArticleCategory) session.load(
                    ArticleCategory.class, transientObject.getId());

            obj = transientObject;

            session.save(obj);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return true;
    }

}
