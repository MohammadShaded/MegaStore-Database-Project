package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Transitions;
import com.example.thestore.Methods.UpdateFromDatabase;
import com.example.thestore.SlidesControllers.CustomerSlidesControl;
import com.example.thestore.SlidesControllers.EmployeeSlidesControl;
import com.example.thestore.SlidesControllers.OrderSlidesControl;
import com.example.thestore.TablesFields.CustomerFields;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerControl implements Initializable {


    int order;
    Parent Slide[]=new Parent[1];
    FXMLLoader loader =new FXMLLoader();
    @FXML
    AnchorPane interiar;
    @FXML
    Button PreviousButton;
    @FXML
    Button NextButton;
    CustomerSlidesControl slideControl;
    CustomerFields CusNew;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            CusNew = new CustomerFields();
            NextButton.setText("Save");
            NextButton.setStyle("-fx-background-color: #3dbd29;");
            PreviousButton.setDisable(true);

            loader=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddCustomer/First.fxml"));

            Slide[0]=loader.load();



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
        CusNew.setContactInformation(slideControl.ContactInformation.getText());
        CusNew.setAddress(slideControl.Address.getText());
        CusNew.setCustomerID(Integer.valueOf(slideControl.CustomerID.getText()));
        CusNew.setName(slideControl.Name.getText());

        if(UPDATE_OPERATION){
            UpdateFromDatabase.updateCustomer(CusNew);
        }
        else DataBase.PinsertCustomer(CusNew);

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


    CustomerFields FieldsForUpdate;
    public void setCustomerFieldsForUpdate(CustomerFields Fields){
        FieldsForUpdate=Fields;
        CustomerSlidesControl slideControl2= loader.getController();
        slideControl2.CustomerID.setText(String.valueOf(Fields.getCustomerID()));
        slideControl2.CustomerID.setDisable(true);
        slideControl2.Name.setText(Fields.getName());
        slideControl2.Address.setText(Fields.getAddress());
        slideControl2.ContactInformation.setText(Fields.getContactInformation());
    }

    boolean UPDATE_OPERATION=false;
    public void setUpdateOperation(boolean update){
        UPDATE_OPERATION =update;
    }

}
