package com.example.thestore;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.TablesFields.WarehouseFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WarehouseSettings implements Initializable {
int warehouseID;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    TextField Name;
    @FXML
    TextField city;
    @FXML
    TextField street;
    @FXML
    Spinner<Integer> capacity;

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


    public void save(ActionEvent event) throws SQLException {

        settings.setWname(Name.getText());
        settings.setCapacity(capacity.getValue());
        settings.setStreet(street.getText());
        settings.setCity(city.getText());
        DataBase.updateWarehouse(settings,warehouseID);
        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.removeSceneBlurry(mainStage);
        stage.close();
    }

    WarehouseFields settings;
    public void setSettings(WarehouseFields warehouseSettings) {
        settings=warehouseSettings;
        Name.setText(settings.getWname());
        city.setText(settings.getCity());
        street.setText(settings.getStreet());
        capacity.getValueFactory().setValue(settings.getCapacity());
        warehouseID=warehouseSettings.getWarehouseid();
    }
}
