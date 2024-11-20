package com.example.thestore.SlidesControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TransactionSlidesControl {

    @FXML
    public Spinner<Integer> TransAmount;

    @FXML
    public DatePicker TransDate;

    @FXML
    public TextArea TransDescriotion;

    @FXML
    public TextField TransID;

    @FXML
    public ChoiceBox<String> TransType;

}
