package com.example.thestore.SlidesControllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OrderSlidesControl {
    @FXML
    public TextField OrderID ;
    @FXML
    public TextArea Ordernote;
    @FXML
    public DatePicker Orderdate;
    @FXML
    public ChoiceBox Customer;
}
