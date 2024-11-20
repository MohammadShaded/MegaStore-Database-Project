package com.example.thestore.SlidesControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class InventorySlidesControl {

    @FXML
    public TextField ID;
    @FXML
    public ChoiceBox<String>  product;
    @FXML
    public Spinner <Integer> quantity;


    @FXML
    public DatePicker  ProductionDate;

    @FXML
    public ChoiceBox <String> Warehouse;

}
