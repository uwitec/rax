package rax.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import rax.model.Attachment;
import rax.util.HibernateUtil;

public class AttachmentDao implements GenericDao<Attachment, Long> {

    @Override
    public long count() {
        Long num = new Long(0);
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            String hql = "SELECT count(*) FROM Attachment";
            Query query = session.createQuery(hql);
            num = (Long) query.iterate().next();

            session.getTransaction().rollback();
        } catch (HibernateException ex) {
            num = Long.valueOf(0);
        }
        return num.longValue();
    }

    public long countByArticleId(Long id) {
        return 0;
    }

    @Override
    public List<Attachment> list(int index, int num) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Attachment> listAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Attachment> listByArticleId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long create(Attachment newInstance) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(Attachment persistentObject) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Attachment read(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean update(Attachment transientObject) {
        // TODO Auto-generated method stub
        return false;
    }

}
