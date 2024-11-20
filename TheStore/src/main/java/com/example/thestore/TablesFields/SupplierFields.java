package com.example.thestore.TablesFields;

public class SupplierFields {

    private String Name;
    private Integer SupplierID;
    private String Location;
    private String ContactInformation;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(Integer supplierID) {
        SupplierID = supplierID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getContactInformation() {
        return ContactInformation;
    }

    public void setContactInformation(String contactInformation) {
        ContactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "SupplierFields{" +
                "Name='" + Name + '\'' +
                ", SupplierID=" + SupplierID +
                ", Location='" + Location + '\'' +
                ", ContactInformation='" + ContactInformation + '\'' +
                '}';
    }
    public SupplierFields(){}
    public SupplierFields(String name, Integer supplierID, String location, String contactInformation) {
        Name = name;
        SupplierID = supplierID;
        Location = location;
        ContactInformation = contactInformation;
    }
}
