package com.example.thestore.SlidesControllers;

import com.example.thestore.Methods.DataBase;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WarehouseSlidesControl implements Initializable {
    @FXML
    public TextField Name;
    @FXML
    public TextField city;
    @FXML
    public TextField street;
    @FXML
    public TextField ID;

    @FXML
    public ChoiceBox <String>Manager;
    @FXML
    public Spinner<Integer> capacity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
