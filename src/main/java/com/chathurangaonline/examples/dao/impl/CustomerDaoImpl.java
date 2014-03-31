package com.chathurangaonline.examples.dao.impl;

import com.chathurangaonline.examples.dao.CustomerDao;
import com.chathurangaonline.examples.model.Customer;

public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao{


    public CustomerDaoImpl(Class<Customer> entityClass) {
        super(entityClass);
    }

}
