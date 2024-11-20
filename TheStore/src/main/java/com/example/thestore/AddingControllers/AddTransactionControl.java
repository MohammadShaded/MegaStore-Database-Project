package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Refreash;
import com.example.thestore.SlidesControllers.CustomerSlidesControl;
import com.example.thestore.SlidesControllers.POrderSlidesControl;
import com.example.thestore.SlidesControllers.TransactionSlidesControl;
import com.example.thestore.TablesFields.CustomerFields;
import com.example.thestore.TablesFields.TransactionFields;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTransactionControl implements Initializable {

    int order;
    Parent Slide[]=new Parent[1];
    FXMLLoader loader =new FXMLLoader();
    @FXML
    AnchorPane interiar;
    @FXML
    Button PreviousButton;
    @FXML
    Button NextButton;
    TransactionSlidesControl slideControl;
    TransactionFields TransNew;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TransNew = new TransactionFields();
            NextButton.setText("Save");
            NextButton.setStyle("-fx-background-color: #3dbd29;");
            PreviousButton.setDisable(true);

            loader=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddTransaction/First.fxml"));


            Slide[0]=loader.load();
            TransactionSlidesControl slideControl2= loader.getController();
            slideControl2.TransType.getItems().addAll("Electricity Bill", "Water Bill", "Internet Bill","Fuel Bill","Sewerage Bill");

            if(interiar!=null){
                interiar.getChildren().removeAll();
                interiar.getChildren().setAll(Slide[0]);

            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    @FXML
    private AnchorPane popUpPane;



    public void Next(ActionEvent event){
        slideControl= loader.getController();
        TransNew.setTransID(Integer.valueOf(slideControl.TransID.getText()));
        TransNew.setTransAmount(Double.valueOf((slideControl.TransAmount.getValue())));
        TransNew.setDescription(slideControl.TransDescriotion.getText());
        TransNew.setTransDate(slideControl.TransDate.getValue());
        TransNew.setTransType(slideControl.TransType.getValue());

        DataBase.PinsertTransaction(TransNew);

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.removeSceneBlurry(mainStage);
        stage.close();
        return;


    }
    public void Previous(ActionEvent event){

    }

    private Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;

    }


    public void cancel(ActionEvent event){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Changes");
        alert.setHeaderText("Discard changes and cancel? Any unsaved changes will be lost.");
        if(alert.showAndWait().get()== ButtonType.OK){
            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
            Efficts.removeSceneBlurry(mainStage);
            stage.close();}
    }

}
