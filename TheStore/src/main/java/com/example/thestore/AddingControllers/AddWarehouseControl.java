package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Transitions;
import com.example.thestore.Methods.UpdateFromDatabase;
import com.example.thestore.SlidesControllers.EmployeeSlidesControl;
import com.example.thestore.SlidesControllers.WarehouseSlidesControl;
import com.example.thestore.TablesFields.WarehouseFields;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddWarehouseControl implements Initializable {


    int order;
    Parent Slide[]=new Parent[2];
    FXMLLoader loader []=new FXMLLoader[2];
    @FXML
    AnchorPane interiar;
    @FXML
    Button PreviousButton;
    @FXML
    Button NextButton;
    WarehouseSlidesControl slideControl;
    WarehouseFields newWarehouse;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            newWarehouse=new WarehouseFields();
            order=0;
            PreviousButton.setDisable(true);

            loader[0]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddWarehouse/First.fxml"));
            loader[1]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddWarehouse/Second.fxml"));


            for(int i=0;i<2;i++) Slide[i]=loader[i].load();

            slideControl=loader[0].getController();
            List<String> managers = DataBase.fetchManagerNamesFromDatabase(true);
            slideControl.Manager.setItems(FXCollections.observableArrayList(managers));

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

    public void cancel(ActionEvent event){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Changes");
        alert.setHeaderText("Discard changes and cancel? Any unsaved changes will be lost.");
        if(alert.showAndWait().get()== ButtonType.OK){
            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
            Efficts.removeSceneBlurry(mainStage);
            stage.close();}
    }

    public void Next(ActionEvent event){
        order++;

        if(order==1) {
            slideControl=loader[0].getController();
            newWarehouse.setWname(slideControl.Name.getText());
            newWarehouse.setCity(slideControl.city.getText());
            newWarehouse.setStreet(slideControl.street.getText());
            newWarehouse.setManager(slideControl.Manager.getValue());

            NextButton.setText("Save");
            NextButton.setStyle("-fx-background-color: #3dbd29;");
        }

        if(order==2){
            slideControl= loader[1].getController();
            newWarehouse.setCapacity(slideControl.capacity.getValue());
            newWarehouse.setWarehouseid(Integer.valueOf(slideControl.ID.getText()));
            if(UPDATE_OPERATION){
                UpdateFromDatabase.updateWarehouse(newWarehouse);
            }
            else DataBase.PinsertWarehouse(newWarehouse);

            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
            Efficts.removeSceneBlurry(mainStage);
            stage.close();
            return;
        }



        PreviousButton.setDisable(false);
        TranslateTransition slideOut = Transitions.createSlideTransition(interiar, 0.5,368, 0);
        if(interiar!=null){
            interiar.getChildren().removeAll();
            interiar.getChildren().setAll(Slide[order]);
        }
        slideOut.play();

    }
    public void Previous(ActionEvent event){
        order--;
        if(order==0){
            NextButton.setText("Next");
            NextButton.setStyle("-fx-background-color: #0d5287;");
            PreviousButton.setDisable(true);
        }
        if(order==0);

        TranslateTransition slideOut = Transitions.createSlideTransition(interiar, 0.5,-368, 0);
        if(interiar!=null){
            interiar.getChildren().removeAll();
            interiar.getChildren().setAll(Slide[order]);
        }
        slideOut.play();

    }

    private Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;

    }
    @FXML
    Label lbl;
    WarehouseFields FieldsForUpdate;
    public void setWarehouseFieldsForUpdate(WarehouseFields Fields){
        lbl.setText("Update Warehouse");

        FieldsForUpdate=Fields;
        WarehouseSlidesControl slideControl2= loader[0].getController();

        slideControl2.Name.setText(Fields.getWname());
        slideControl2.Manager.setValue(Fields.getManager());
        slideControl2.city.setText(Fields.getCity());
        slideControl2.street.setText(Fields.getStreet());

        slideControl2= loader[1].getController();
        slideControl2.capacity.getValueFactory().setValue(Fields.getCapacity());
        slideControl2.ID.setText(String.valueOf(Fields.getWarehouseid()));
        slideControl2.ID.setDisable(true);
    }

    boolean UPDATE_OPERATION=false;
    public void setUpdateOperation(boolean update){
        UPDATE_OPERATION =update;
    }
}
