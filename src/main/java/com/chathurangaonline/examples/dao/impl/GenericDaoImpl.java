package com.chathurangaonline.examples.dao.impl;

import com.chathurangaonline.examples.dao.GenericDao;
import com.chathurangaonline.examples.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.util.List;


public  class GenericDaoImpl<T,PK extends Serializable>  implements GenericDao<T,PK>{

    private Class<T> entityClass;

    private static SessionFactory sessionFactory;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

//    public GenericDaoImpl(){
//        this.entityClass = new T();
//    }

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
        System.out.println(" session factory obj ["+sessionFactory+"]");
        return sessionFactory.openSession();
    }

    @Override
    public T findById(Serializable id) {
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(entityClass);
        System.out.println(" criteria ["+criteria+"]");
        T object =  (T)session.get(entityClass,id);
        System.out.println(" ["+object+"] ");
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
            System.out.println(" saved ");
        }
        catch (HibernateException ex){
            if(session!=null && session.getTransaction()!=null){
                session.getTransaction().rollback();
            }
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
    public void delete(T t) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
