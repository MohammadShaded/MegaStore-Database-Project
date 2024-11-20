package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Transitions;
import com.example.thestore.Methods.UpdateFromDatabase;
import com.example.thestore.SlidesControllers.EmployeeSlidesControl;
import com.example.thestore.SlidesControllers.InventorySlidesControl;
import com.example.thestore.TablesFields.InventoryFields;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddInventoryControl implements Initializable {
    int order;
    Parent Slide[]=new Parent[2];
    FXMLLoader loader []=new FXMLLoader[2];
    @FXML
    AnchorPane interiar;
    @FXML
    Button PreviousButton;
    @FXML
    Button NextButton;
    InventorySlidesControl slideControl;
    Object source; //this will indicate what is the controller of main stage
    String sourceString;
    public String WarehousNameManagered;
    InventoryFields newInventory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            newInventory=new InventoryFields();
            order=0;
            PreviousButton.setDisable(true);

            loader[0] = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddInventory/First.fxml"));
            loader[1] = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddInventory/Second.fxml"));
             for(int i=0;i<2;i++) Slide[i]=loader[i].load();

            InventorySlidesControl slideControl2=loader[0].getController();
            List<String> ProductsNames = DataBase.fetchProductNamesFromDatabase();
            slideControl2.product.setItems(FXCollections.observableArrayList(ProductsNames));

             if(interiar!=null){
                interiar.getChildren().removeAll();
                interiar.getChildren().setAll(Slide[0]);
            }





        }
        catch (Exception e){

            e.printStackTrace();
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
            newInventory.setInventoryid(Integer.valueOf(slideControl.ID.getText()));
            newInventory.setProduct(slideControl.product.getValue());
            newInventory.setQuantity(slideControl.quantity.getValue());

            InventorySlidesControl slideControl2=loader[1].getController();
            List<String> warehouseNames = DataBase.fetchWarehouseNamesFromDatabase();
            slideControl2.Warehouse.setItems(FXCollections.observableArrayList(warehouseNames));

            if (sourceString.equals("ManagerScreen")) {
                slideControl2.Warehouse.setValue(WarehousNameManagered);
                slideControl2.Warehouse.setDisable(true);
            }

            NextButton.setText("Save");
            NextButton.setStyle("-fx-background-color: #3dbd29;");
        }

        if(order==2){
            slideControl= loader[1].getController();
            newInventory.setWarehouse(slideControl.Warehouse.getValue());
            newInventory.setProductionDate(slideControl.ProductionDate.getValue());
            if(UPDATE_OPERATION){
                UpdateFromDatabase.updateInventory(newInventory);
            }
            else DataBase.PinsertInventory(newInventory);
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
        source = mainStage.getProperties().get("source");
        if (source != null) {
            sourceString = source.toString();
            if (sourceString.equals("InventoryControl")) {
                // mainStage was created by EmployeeControl
            } else if (sourceString.equals("ManagerScreen")) {
                // mainStage was created by ManagerScreen

            }
        }
    }

    @FXML
    Label lbl;
    InventoryFields FieldsForUpdate;
    public void setInventoryFieldsForUpdate(InventoryFields Fields){
        lbl.setText("Update Inventory");

        FieldsForUpdate=Fields;
        InventorySlidesControl slideControl2= loader[0].getController();
        slideControl2.ID.setText(String.valueOf(Fields.getInventoryid()));
        slideControl2.ID.setDisable(true);
        slideControl2.product.setValue(Fields.getProduct());
        slideControl2.quantity.getValueFactory().setValue(Fields.getQuantity());
        slideControl2= loader[1].getController();
        slideControl2.Warehouse.setValue(Fields.getWarehouse());
        slideControl2.ProductionDate.setValue(Fields.getProductionDate());

    }

    boolean UPDATE_OPERATION=false;
    public void setUpdateOperation(boolean update){
        UPDATE_OPERATION =update;
    }

}
