package com.example.thestore.TablesFields;

import java.time.LocalDate;

public class OrderDetailsFields {

    private String Product;
    private Integer quantity;
    private String warhouse;
    private LocalDate productionDate;
    private LocalDate ExpiryDate;
    private Integer OrderID;

    public Integer getID() {
        return OrderID;
    }

    public void setID(Integer ID) {
        this.OrderID = ID;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getWarhouse() {
        return warhouse;
    }

    public void setWarhouse(String warhouse) {
        this.warhouse = warhouse;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        ExpiryDate = expiryDate;
    }
}
