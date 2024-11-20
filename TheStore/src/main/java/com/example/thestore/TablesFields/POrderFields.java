package com.example.thestore.TablesFields;

import java.sql.Timestamp;

public class POrderFields {
    private Timestamp Orderdate;
    private String Supplier;
    private Integer POrderID;
    private Double Total;
    private Timestamp OrderdateTo;
    private Double TotalTo;

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

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public Integer getPOrderID() {
        return POrderID;
    }

    public void setPOrderID(Integer POrderID) {
        this.POrderID = POrderID;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "POrderFilds{" +
                "Orderdate=" + Orderdate +
                ", SupplierID=" + Supplier +
                ", POrderID=" + POrderID +
                ", Total=" + Total +
                '}';
    }
    public POrderFields(){}

    public POrderFields(Timestamp orderdate, String supplier, Integer POrderID, Double total) {
        Orderdate = orderdate;
        Supplier = supplier;
        this.POrderID = POrderID;
        Total = total;
    }
}
