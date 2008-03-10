package rax.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void main(String[] args) {
        Configuration config = null;
        Transaction tx = null;
        Session session = null;

        try {
            config = new Configuration().configure();
            System.out.println("Creating tables...");
            SchemaExport schemaExport = new SchemaExport(config);
            schemaExport.create(true, true);
            System.out.println("Table created.");

            SessionFactory sessionFactory = config.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                tx.rollback();
            } catch (HibernateException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (null != session && session.isOpen())
                session.close();

        }
    }
}
