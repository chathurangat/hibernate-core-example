package com.chathurangaonline.examples.dao.impl;

import com.chathurangaonline.examples.dao.CustomerDao;
import com.chathurangaonline.examples.model.Customer;
import junit.framework.Assert;
import org.hibernate.PropertyValueException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerDaoImplTest {

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

    /**
     * <p>
     *     trying to save the customer without setting the values for the mandatory/not nullable fields
     *     this method should throws the {@link PropertyValueException} since the property values are not set properly
     * </p>
     */
    @Test(expectedExceptions = org.hibernate.PropertyValueException.class)
    public void testSaveEmptyObject() throws Exception{
        Customer customer = new Customer();
        customerDao.save(customer);
    }


    /**
     * <p>
     *     trying to save the customer instance with valid data for all the properties
     * </p>
     *
     */
    @Test
    public void testSave() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("chathuranga tennakoon");
        customer.setCustomerAddress("www.chathurangaonline.com");
        customer.setActive(true);
        customerDao.save(customer);

        //retrieving the saved customer instance
        Customer customerRetrieved = customerDao.findById(customer.getCustomerID());
        Assert.assertNotNull(customerRetrieved);
        Assert.assertEquals(customer.getCustomerID(),customerRetrieved.getCustomerID());

        //delete the instance
        customerDao.delete(customerRetrieved);
        Customer customerDeleted= customerDao.findById(customerRetrieved.getCustomerID());
        Assert.assertNull(customerDeleted);
    }



    @Test
    public void testDelete() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("chathuranga tennakoon");
        customer.setCustomerAddress("www.chathurangaonline.com");
        customer.setActive(true);
        customerDao.save(customer);

        //retrieving the saved customer instance
        Customer customerRetrieved = customerDao.findById(customer.getCustomerID());
        Assert.assertNotNull(customerRetrieved);
        Assert.assertEquals(customer.getCustomerID(),customerRetrieved.getCustomerID());

        //delete the instance
        customerDao.delete(customerRetrieved);
        Customer customerDeleted= customerDao.findById(customerRetrieved.getCustomerID());
        Assert.assertNull(customerDeleted);
    }



    @Test
    public void testDeleteWhenReferenceIsNull() throws Exception {
        //delete the instance
        customerDao.delete(null);
    }


    @Test
    public void testDeleteNonExistenceObject() throws Exception {
        //delete the instance
        Customer customer =  new Customer();
        customerDao.delete(customer);
    }


    @Test
    public void testFindCustomerByPassingNull() throws Exception {
        //retrieving the saved customer instance
        Customer customerRetrieved = customerDao.findById(null);
        Assert.assertNull(customerRetrieved);
    }


    @Test
    public void testFindCustomerByNonExistentId() throws Exception {
        //retrieving the saved customer instance
        Customer customerRetrieved = customerDao.findById(100L);
        Assert.assertNull(customerRetrieved);
    }


    @Test
    public void testFindById() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("chathuranga tennakoon");
        customer.setCustomerAddress("www.chathurangaonline.com");
        customer.setActive(true);
        customerDao.save(customer);

        //retrieving the saved customer instance
        Customer customerRetrieved = customerDao.findById(customer.getCustomerID());
        Assert.assertNotNull(customerRetrieved);
        Assert.assertEquals(customer.getCustomerID(),customerRetrieved.getCustomerID());

        //delete the instance
        customerDao.delete(customerRetrieved);
        Customer customerDeleted= customerDao.findById(customerRetrieved.getCustomerID());
        Assert.assertNull(customerDeleted);
    }


    /**
     * <p>
     *     try to find the all active customers when there is no active customer
     * </p>
     */
    @Test
    public void testFindAllActiveCustomersWhenCustomerTableEmpty(){

        List<Customer> customerList = customerDao.getActiveCustomers();
        Assert.assertEquals(0, customerList.size());
    }



    /**
     * <p>
     *     try to find the all active customers when there is no active customer
     * </p>
     */
    @Test
    public void testFindAllActiveCustomers(){

        //creating first active customer
        Customer customer1 = new Customer();
        customer1.setCustomerName("chathuranga tennakoon");
        customer1.setCustomerAddress("www.chathurangaonline.com");
        customer1.setActive(true);
        customerDao.save(customer1);

        //creating second active customer
        Customer customer2 = new Customer();
        customer2.setCustomerName("darshana chathuranga tennakoon");
        customer2.setCustomerAddress("www.fosshub.org");
        customer2.setActive(true);
        customerDao.save(customer2);

        //creating first inactive customer
        Customer customer3 = new Customer();
        customer3.setCustomerName("darshana chathuranga tennakoon");
        customer3.setCustomerAddress("www.fosshub.org");
        customer3.setActive(false);
        customerDao.save(customer3);

        List<Customer> customerList = customerDao.getActiveCustomers();
        Assert.assertEquals(2,customerList.size());

        //delete the customer 1
        customerDao.delete(customer1);
        Customer customerDeleted1= customerDao.findById(customer1.getCustomerID());
        Assert.assertNull(customerDeleted1);

        //delete the customer 2
        customerDao.delete(customer2);
        Customer customerDeleted2= customerDao.findById(customer2.getCustomerID());
        Assert.assertNull(customerDeleted2);


        //delete the customer 3
        customerDao.delete(customer3);
        Customer customerDeleted3= customerDao.findById(customer3.getCustomerID());
        Assert.assertNull(customerDeleted3);
    }



    private CustomerDao getInstance(){
        if(customerDao==null){
            customerDao = new CustomerDaoImpl(Customer.class);
        }
        return  customerDao;
    }
}
