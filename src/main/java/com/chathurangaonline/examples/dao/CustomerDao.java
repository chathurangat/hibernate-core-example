package com.chathurangaonline.examples.dao;

import com.chathurangaonline.examples.model.Customer;

import java.util.List;

public interface CustomerDao extends GenericDao<Customer,Long> {

    public List<Customer>  getActiveCustomers();

}
