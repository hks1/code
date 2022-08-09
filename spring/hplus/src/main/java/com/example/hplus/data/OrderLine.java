package com.example.hplus.data;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_LINES")
public class OrderLine {
    /*
    CREATE TABLE ORDER_LINES(
    ORDER_LINE_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    ORDER_ID VARCHAR(16),
    PRODUCT_ID VARCHAR(32),
    QUANTITY INT
);
     */
    @Id
    @Column(name = "ORDER_LINE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false, updatable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false)
    private Product product;

    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
