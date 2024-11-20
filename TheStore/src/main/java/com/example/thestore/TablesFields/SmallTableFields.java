package com.example.thestore.TablesFields;

import java.time.LocalDate;

public class SmallTableFields {
    private Integer smallID;
    private Integer smallQuantity;
    private LocalDate smallDate;

    public Integer getSmallID() {
        return smallID;
    }

    public void setSmallID(Integer smallID) {
        this.smallID = smallID;
    }

    public Integer getSmallQuantity() {
        return smallQuantity;
    }

    public void setSmallQuantity(Integer smallQuantity) {
        this.smallQuantity = smallQuantity;
    }

    public LocalDate getSmallDate() {
        return smallDate;
    }

    public void setSmallDate(LocalDate smallDate) {
        this.smallDate = smallDate;
    }
}
