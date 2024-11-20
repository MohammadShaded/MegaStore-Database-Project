package com.example.thestore.TablesFields;

import java.time.LocalDate;

public class InventoryFields {
    private Integer inventoryid;
    private String product;
    private String warehouse;
    private LocalDate productionDate;
    private LocalDate productionDateTo;

    private LocalDate expiryDate;
    private LocalDate expiryDateTo;

    private Integer quantity;
    private Integer quantityTo;

    public Integer getQuantityTo() {
        return quantityTo;
    }

    public void setQuantityTo(Integer quantityTo) {
        this.quantityTo = quantityTo;
    }

    public LocalDate getProductionDateTo() {
        return productionDateTo;
    }

    public void setProductionDateTo(LocalDate productionDateTo) {
        this.productionDateTo = productionDateTo;
    }

    public LocalDate getExpiryDateTo() {
        return expiryDateTo;
    }

    public void setExpiryDateTo(LocalDate expiryDateTo) {
        this.expiryDateTo = expiryDateTo;
    }

    public InventoryFields(Integer inventoryid, String product, String warehouse, LocalDate productionDate, LocalDate expiryDate, Integer quantity) {
        this.inventoryid = inventoryid;
        this.product = product;
        this.warehouse = warehouse;
        this.productionDate = productionDate;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "InventoryFields{" +
                "inventoryid=" + inventoryid +
                ", product='" + product + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", productionDate=" + productionDate +
                ", expiryDate=" + expiryDate +
                ", quantity=" + quantity +
                '}';
    }
    public InventoryFields() {
    }

    public Integer getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(Integer inventoryid) {
        this.inventoryid = inventoryid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
