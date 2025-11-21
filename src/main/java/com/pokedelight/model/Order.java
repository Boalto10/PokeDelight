package com.pokedelight.model;
import java.util.List;
import java.sql.Timestamp;

public class Order {
    private int id;
    private String customerName;
    private String address;
    private double total;
    private Timestamp createdAt;
    private List<OrderItem> items;

    public Order() {}
    // getters and setters
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String n){this.customerName=n;}
    public String getAddress(){return address;} public void setAddress(String a){this.address=a;}
    public double getTotal(){return total;} public void setTotal(double t){this.total=t;}
    public Timestamp getCreatedAt(){return createdAt;} public void setCreatedAt(Timestamp t){this.createdAt=t;}
    public List<OrderItem> getItems(){return items;} public void setItems(List<OrderItem> items){this.items=items;}
}
