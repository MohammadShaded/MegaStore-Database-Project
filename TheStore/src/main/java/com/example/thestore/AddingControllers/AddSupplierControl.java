package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.UpdateFromDatabase;
import com.example.thestore.SlidesControllers.CustomerSlidesControl;
import com.example.thestore.SlidesControllers.SupplierSlidesControl;
import com.example.thestore.TablesFields.SupplierFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSupplierControl implements Initializable {


        int order;
        Parent Slide[]=new Parent[1];
        FXMLLoader loader =new FXMLLoader();
        @FXML
        AnchorPane interiar;
        @FXML
        Button PreviousButton;
        @FXML
        Button NextButton;
        SupplierSlidesControl slideControl;
        SupplierFields SupNew;
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                SupNew=new SupplierFields();
                NextButton.setText("Save");
                NextButton.setStyle("-fx-background-color: #3dbd29;");
                PreviousButton.setDisable(true);

                loader=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddSupplier/First.fxml"));


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
            SupNew.setContactInformation(slideControl.ContactInformation.getText());
            SupNew.setLocation(slideControl.Address.getText());
            SupNew.setSupplierID(Integer.valueOf(slideControl.SupplierID.getText()));
            SupNew.setName(slideControl.Name.getText());
            if(UPDATE_OPERATION){
                UpdateFromDatabase.updateSupplier(SupNew);
            }
            else  DataBase.PinsertSupplier(SupNew);
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

    @FXML
    Label lbl;
    SupplierFields FieldsForUpdate;
    public void setSupplierFieldsForUpdate(SupplierFields Fields){
        lbl.setText("Update Supplier");

        FieldsForUpdate=Fields;
        SupplierSlidesControl slideControl2= loader.getController();
        slideControl2.SupplierID.setText(String.valueOf(Fields.getSupplierID()));
        slideControl2.SupplierID.setDisable(true);
        slideControl2.Name.setText(Fields.getName());
        slideControl2.Address.setText(Fields.getLocation());
        slideControl2.ContactInformation.setText(Fields.getContactInformation());
    }

    boolean UPDATE_OPERATION=false;
    public void setUpdateOperation(boolean update){
        UPDATE_OPERATION =update;
    }

    }


