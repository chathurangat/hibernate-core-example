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


    /**
     * <p>
     *     getting a list of customers whose status is isActive
     * </p>
     * @return  list of customer instances as {@link List<Customer>}
     */
    @Override
    public List<Customer> getActiveCustomers() {
        Session session = null;
        List<Customer> customerList = null;
        try{
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Customer.class).add(Restrictions.eq("isActive",true));
            customerList = (List<Customer>) criteria.list();
            System.out.println(" size ["+customerList.size()+"]");
        }
        finally {
            if(session!=null){
                session.close();
            }
        }
        return customerList;
    }
}
