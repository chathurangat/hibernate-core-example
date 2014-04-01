package com.chathurangaonline.examples.dao.impl;

import com.chathurangaonline.examples.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.io.Serializable;
import java.util.List;


public  class GenericDaoImpl<T,PK extends Serializable>  implements GenericDao<T,PK>{

    private Class<T> entityClass;

    private static SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public   Session getSession(){
        SessionFactory sessionFactory = this.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public T findById(Serializable id) {
        T object = null;
        if(id!=null){
            Session session = this.getSession();
            object =  (T)session.get(entityClass,id);
            return object;
        }
        return object;
    }


    @Override
    public void save(T object) {
        Session session = null;
        try{
            session = getSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        }
        catch (HibernateException ex){
            if(session!=null && session.getTransaction()!=null){
                session.getTransaction().rollback();
            }
            throw ex;
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
    }


    @Override
    public List<T> findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Integer countAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(T object) {
        if(object!=null){
            Session session = null;
            try{
                session = this.getSessionFactory().openSession();
                session.beginTransaction();
                session.delete(object);
                session.getTransaction().commit();
            }
            catch (HibernateException ex){
                if(session!=null && session.getTransaction()!=null){
                    session.getTransaction().rollback();
                }
                throw ex;
            }
            finally {
                if(session!=null){
                    session.close();
                }
            }
        }
    }



    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
