package com.chathurangaonline.examples.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="customers")
public class Customer implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name")
    private String customerName;

    @Column(name = "address")
    private String customerAddress;

    public Customer() {}

    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public Long getCustomerID() {
        return id;
    }
    public void setCustomerID(Long customerID) {
        this.id = customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}