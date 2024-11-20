package com.example.thestore.TablesFields;

public class CustomerFields {

    private String Name;
    private Integer CustomerID;
    private String Address;
    private String ContactInformation;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactInformation() {
        return ContactInformation;
    }

    public void setContactInformation(String contactInformation) {
        ContactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "CustomerFields{" +
                "Name='" + Name + '\'' +
                ", CustomerID=" + CustomerID +
                ", Address='" + Address + '\'' +
                ", ContactInformation='" + ContactInformation + '\'' +
                '}';
    }
    public CustomerFields(){}
    public CustomerFields(String name, int customerID, String address, String contactInformation) {
        Name = name;
        CustomerID = customerID;
        Address = address;
        ContactInformation = contactInformation;
    }
}
