package rax.dao.hibernate;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    PK create(T newInstance);

    T read(PK id);

    boolean update(T transientObject);

    boolean delete(T persistentObject);
    
    /*{
        try {
            Session session = HibernateUtil.getSessionFactory()
                    .getCurrentSession();
            session.beginTransaction();

            T obj = (T) session.load(T.class,
                    persistentObject.getId());
            session.delete(obj);

            session.getTransaction().commit();
        } catch (HibernateException ex) {
            return false;
        }
        return false;        
    }*/

    long count();

    List<T> list(int index, int num);

    List<T> listAll();

}
