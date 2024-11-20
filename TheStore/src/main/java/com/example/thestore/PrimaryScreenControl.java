package com.example.thestore;

import com.example.thestore.Methods.DataBase;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class PrimaryScreenControl implements Initializable {


    @FXML
    Label UserName;
    @FXML
    AnchorPane RightLocation;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/EmployeesAdmin.fxml"));
            if(RightLocation!=null){
                RightLocation.getChildren().removeAll();
                RightLocation.getChildren().setAll(fxml);

                 }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void page1(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/EmployeesAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void page2(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/WarehouseAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public void page3(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/InventoryAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public void page4(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/ProductAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void page5(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/OrderAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void page6(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/POrderAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void page7(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/CustomerAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void page8(ActionEvent event) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AdminViews/SupplierAdmin.fxml"));
            RightLocation.getChildren().removeAll();
            RightLocation.getChildren().setAll(fxml);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private Scene scene;  // this is three objects for scene switching
    private Stage stage;
    private Parent root;
    public void switchToScene1(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        stage=(Stage)(((Node)event.getSource()).getScene().getWindow());
        scene=new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Styles/Styling.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


        void setUser (String User){
        UserName.setText(User+" (Admin)");
        }



}
