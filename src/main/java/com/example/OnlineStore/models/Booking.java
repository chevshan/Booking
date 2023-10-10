package com.example.OnlineStore.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_name")
    @NotNull(message = "Order name should not be empty!")
    private String orderName;

    @Column(name = "price")
    @NotNull(message = "Price should not be empty!")
    private int price;

    @Column(name = "count")
    @NotNull(message = "Count should not be empty!")
    private int count;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "ordertime")
    private LocalDateTime orderTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
