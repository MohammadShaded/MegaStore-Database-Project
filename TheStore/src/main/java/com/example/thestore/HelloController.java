package com.example.thestore;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Transitions;
import com.example.thestore.TablesFields.EmployeeFields;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.io.IOException;
import java.util.ResourceBundle;

public class HelloController  implements Initializable {

    @FXML
    AnchorPane RightLogo;
    private String UserName;
    private String warehouse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateTransition slideOut = Transitions.createSlideTransition(RightLogo, 1,-400, 0);
        slideOut.play();
    }


    private Scene scene;  // this is three objects for scene switching
    private Stage stage;
    private Parent root;
    @FXML
    private Label label;
    @FXML
    TextField email ;
    @FXML
    PasswordField password ;


    public void switchToScene2(ActionEvent event) throws IOException{
        String Email = email.getText();
        String passwordd = password.getText();

        if (Email.equals("")||(password.equals("")&&Email.equals(""))){
            label.setText("Enter both your email and password to log in.");
            label.setStyle("-fx-text-fill: red;");
            return;
        }

        EmployeeFields emails = DataBase.fetchemailFromDatabase(Email);
        UserName=emails.getFirstName()+" "+emails.getLastName();
        warehouse=emails.getWarehouse();
        if (emails != null && emails.getEmail() != null && emails.getEmail().equals(Email) ){
            if ((emails.getPassword()).equals(passwordd)){

                if ((emails.getChoiceRoll()).equals("Manager")){
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("ManagerScreen.fxml"));
                    root=loader.load();
                    ManagerScreenControl managerScreenControl =loader.getController();
                    managerScreenControl.setUser(UserName,warehouse);
                    stage=(Stage)(((Node)event.getSource()).getScene().getWindow());
                    stage.close();
                    scene=new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();

                }
                else if (emails.getChoiceRoll().equals("Admin")) {

                    FXMLLoader loader=new FXMLLoader(getClass().getResource("PrimaryScreen.fxml"));
                    root=loader.load();
                    PrimaryScreenControl primaryScreenControl =loader.getController();
                    primaryScreenControl.setUser(UserName);
                    stage=(Stage)(((Node)event.getSource()).getScene().getWindow());
                    stage.close();
                    scene=new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();

                }
                else if (emails.getChoiceRoll().equals("Accountant")) {
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("AccountantScreen.fxml"));
                    root=loader.load();
                    AccountantScreenControl accountantScreenControl =loader.getController();
                    accountantScreenControl.setUser(UserName);
                    stage=(Stage)(((Node)event.getSource()).getScene().getWindow());
                    stage.close();
                    scene=new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();


                }
                else if (emails.getChoiceRoll().equals("Worker")) {
                    label.setText("Workers are not allowed to enter the system.");
                    label.setStyle("-fx-text-fill: red;");
                    return;
                }

            }

            else {
                label.setText("Incorrect password. Please try again.");
                label.setStyle("-fx-text-fill: red;");
            }
        }
        else {
            label.setText("Email not found.");
            label.setStyle("-fx-text-fill: red;");
        }

//        root = FXMLLoader.load(getClass().getResource("PrimaryScreen.fxml"));//edit from "hello-two-view.fxml" to "PrimaryScreen.fxml"
//        stage=(Stage)(((Node)event.getSource()).getScene().getWindow());
//        stage.close();
//        scene=new Scene(root);
//        //scene.getStylesheets().add(getClass().getResource("PrimaryScreenStyle.css").toExternalForm());//edit from "Styling2.css" to "PrimaryScreenStyle.css"
//        stage.setScene(scene);
//        //String ss=email.getText();
//        //welcomMessage.setText("Hello,"+ss);
//        stage.centerOnScreen();
//        stage.show();

    }


    //this exit method for exiting from stage
    @FXML
    private Button exitBut;
    @FXML
    private AnchorPane log2;
    public void exit(ActionEvent event){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("YOU ARE ABOUT TO LOGOUT!");
        alert.setContentText("do you want to save before exiting?");

        if(alert.showAndWait().get()== ButtonType.OK){
            stage=(Stage) log2.getScene().getWindow();
            stage.close();}
    }

}

