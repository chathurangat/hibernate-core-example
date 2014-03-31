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
import java.util.List;


public  class GenericDaoImpl<T>  implements GenericDao<T>{

    private Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private  Session getSession(){
        SessionFactory sessionFactory = this.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public T findById(Long id) {

//        Session session = this.getSession();
//        session.beginTransaction();
//        Criteria criteria = session.createCriteria(entityClass);
//        criteria.setMaxResults(1);
//        criteria.add(Restrictions.eq("id",id));
//        return  (T)(criteria.list().get(0));



        Session session = this.getSession();
//        session.beginTransaction();
        System.out.println("Session ["+session+"]");
        System.out.println(" entity class ["+entityClass+"]");
        T object =  (T)session.get(entityClass,id);
//        session.getTransaction().commit();
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
