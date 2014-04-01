package com.chathurangaonline.examples.dao.impl;

import com.chathurangaonline.examples.dao.CustomerDao;
import com.chathurangaonline.examples.model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerDaoImpl extends GenericDaoImpl<Customer,Long> implements CustomerDao{

    private static SessionFactory sessionFactory;

    public CustomerDaoImpl(Class<Customer> entityClass) {
        super(entityClass);
        sessionFactory = this.getSessionFactory();
    }


    @Override
    public List<Customer> getActiveCustomers() {
        System.out.println(" started");
        GenericDaoImpl genericDao = new GenericDaoImpl(Customer.class);
        System.out.println(" gene ["+genericDao+"]");
//        sessionFactory = genericDao.getSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println(" session in dao impl ["+session+"]");
        Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.eq("isActive",true));
        List<Customer> customerList = (List<Customer>) criteria.list();
        System.out.println(" size ["+customerList.size()+"]");
        //closing the session
        return null;
    }
}
