package com.example.thestore.TablesFields;

import java.sql.Timestamp;
import java.time.LocalDate;

public class OrderFields {
    private Timestamp Orderdate;
    private Timestamp OrderdateTo;
    private String Customer;
    private Integer OrderID;
    private Double Total;
    private Double TotalTo;

    private String Ordernote;

    public Timestamp getOrderdateTo() {
        return OrderdateTo;
    }

    public void setOrderdateTo(Timestamp orderdateTo) {
        OrderdateTo = orderdateTo;
    }

    public Double getTotalTo() {
        return TotalTo;
    }

    public void setTotalTo(Double totalTo) {
        TotalTo = totalTo;
    }

    public Timestamp getOrderdate() {
        return Orderdate;
    }

    public void setOrderdate(Timestamp orderdate) {
        Orderdate = orderdate;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public String getOrdernote() {
        return Ordernote;
    }

    public void setOrdernote(String ordernote) {
        Ordernote = ordernote;
    }

    @Override
    public String toString() {
        return "OrderFields{" +
                "Orderdate='" + Orderdate + '\'' +
                ", CustomerID=" + Customer +
                ", OrderID='" + OrderID + '\'' +
                ", Total='" + Total + '\'' +
                ", Ordernote='" + Ordernote + '\'' +
                '}';
    }

    public OrderFields(){}

    public OrderFields(Timestamp orderdate, String customer, Integer orderID, Double total, String ordernote) {
        Orderdate = orderdate;
        Customer = customer;
        OrderID = orderID;
        Total = total;
        Ordernote = ordernote;
    }
}
