package com.example.thestore.SlidesControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductSlidesControl  {



    @FXML
    public TextField Brand;

    @FXML
    public TextField Name;

    @FXML
    public TextField ProductID;

    @FXML
    public Spinner<Integer> ShilfDay;
    @FXML
    public Spinner<Integer> ShilfMonth;
    @FXML
    public Spinner<Integer> ShilfYear;
    @FXML
    public Spinner<Integer> Price;
    @FXML
    public ChoiceBox<String> Manufacturer;
}
