package com.example.hplus.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    private String id;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "SALESPERSON_ID", nullable = false, updatable = false)
    private SalesPerson salesPerson;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SalesPerson getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(SalesPerson salesPerson) {
        this.salesPerson = salesPerson;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
