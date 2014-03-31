package com.chathurangaonline.examples.dao.impl;

import com.chathurangaonline.examples.dao.CustomerDao;
import com.chathurangaonline.examples.model.Customer;
import junit.framework.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GenericDaoImplTest {


//    private static GenericDao genericDao;
    private static CustomerDao customerDao;


    /**
     * <p>
     *     this method will be executed before running every test-case
     * </p>
     */
    @BeforeMethod
    public void setUp(){
       this.getInstance();
    }


//    @Test
//    public void testSave() throws Exception {
//        Customer customer = new Customer();
//        customer.setCustomerName("chathuranga tennakoon");
//        customer.setCustomerAddress("www.chathurangaonline.com");
//        genericDao.save(customer);
//
//        Customer customerRetrieved = (Customer)genericDao.findById(1L);
//        Assert.assertNotNull(customerRetrieved);
//    }
//
//
//    private GenericDao getInstance(){
//        if(genericDao==null){
//            genericDao = new GenericDaoImpl();
//        }
//        return  genericDao;
//    }




    @Test
    public void testSave() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("chathuranga tennakoon");
        customer.setCustomerAddress("www.chathurangaonline.com");
        customerDao.save(customer);

        Customer customerRetrieved = (Customer)customerDao.findById(customer.getCustomerID());
        Assert.assertNotNull(customerRetrieved);
    }


    private CustomerDao getInstance(){
        if(customerDao==null){
            customerDao = new CustomerDaoImpl(Customer.class);
        }
        return  customerDao;
    }
}
