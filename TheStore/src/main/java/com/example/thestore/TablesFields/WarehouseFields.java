package com.example.thestore.TablesFields;

public class WarehouseFields {

    Integer warehouseid;
    String wname;
    String city;
    String street;
    Integer capacity;
    String manager;

    Integer capacityTo;

    public Integer getCapacityTo() {
        return capacityTo;
    }

    public void setCapacityTo(Integer capacityTo) {
        this.capacityTo = capacityTo;
    }

    public Integer getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(Integer warehouseid) {
        this.warehouseid = warehouseid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public WarehouseFields() {
    }

    public WarehouseFields(Integer warehouseid, String wname, String city, String street, Integer capacity, String manager) {
        this.warehouseid = warehouseid;
        this.wname = wname;
        this.city = city;
        this.street = street;
        this.capacity = capacity;
        this.manager = manager;
    }
}
