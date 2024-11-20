package com.example.thestore.TablesFields;

import javafx.stage.Stage;

import java.time.LocalDate;

public class TransactionFields {

    private Integer TransID;
    private String TransType;
    private Double TransAmount;
    private LocalDate TransDate;
    private String Description;

    public Integer getTransID() {
        return TransID;
    }

    public void setTransID(Integer transID) {
        TransID = transID;
    }

    public String getTransType() {
        return TransType;
    }

    public void setTransType(String transType) {
        TransType = transType;
    }

    public Double getTransAmount() {
        return TransAmount;
    }

    public void setTransAmount(Double transAmount) {
        TransAmount = transAmount;
    }

    public LocalDate getTransDate() {
        return TransDate;
    }

    public void setTransDate(LocalDate transDate) {
        TransDate = transDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
