package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddEmployeeControl;
import com.example.thestore.AddingControllers.AddSupplierControl;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.CustomerFields;
import com.example.thestore.TablesFields.OrderFields;
import com.example.thestore.TablesFields.SupplierFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierControl implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        SupplierID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        ContactInformation.setCellValueFactory(new PropertyValueFactory<>("ContactInformation"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));

        Refreash.populateSupplierTableView(Table);
        initi();
    }

    @FXML
    private PieChart pieChart;
    private void initi(){


        pieChart.setTitle("Most Selling");
        pieChart.getData().addAll(Refreash.populateSupplierPieChart());

    }

    @FXML
    private AnchorPane ContainerPane;
    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane.requestFocus();
    }


    public void delete(ActionEvent event)throws IOException {

        SupplierFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getSupplierID();
        DeleteFromDatabase.deleteSupplier(id);

    }


    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddSupplier/AddSupplier.fxml"));
            Parent popUpRoot = loader.load();
                AddSupplierControl addSupplierController = loader.getController();
                addSupplierController.setMainStage(stage);
            Stage popUpStage=new Stage();
            Scene popUpScene=new Scene(popUpRoot);
            popUpStage.setScene(popUpScene);
            popUpStage.initStyle(StageStyle.UNDECORATED);
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.initOwner((Stage)(((Node)event.getSource()).getScene().getWindow()));

            popUpStage.show();
        }
        catch(Exception e){
            e.printStackTrace();

        }

    }
    public void update(ActionEvent event)throws IOException {
        SupplierFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getSupplierID();

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddSupplier/AddSupplier.fxml"));
            Parent popUpRoot = loader.load();
            AddSupplierControl addSupplierController = loader.getController();
            addSupplierController.setMainStage(stage);
            addSupplierController.setSupplierFieldsForUpdate(selectedItem);
            addSupplierController.setUpdateOperation(true);
            Stage popUpStage=new Stage();
            Scene popUpScene=new Scene(popUpRoot);
            popUpStage.setScene(popUpScene);
            popUpStage.initStyle(StageStyle.UNDECORATED);
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.initOwner((Stage)(((Node)event.getSource()).getScene().getWindow()));

            popUpStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }



    public void refreash(ActionEvent event)throws IOException {
        Refreash.populateSupplierTableView(Table);

    }

    public void search(ActionEvent event)throws IOException {
        SupplierFields fieldsTosearch= FieldsReader.checkSupplier(this);
        SearchFromDatabase.searchSupplier(fieldsTosearch,Table);
    }

    @FXML
    private TableView<SupplierFields> Table;

    @FXML
    private TableColumn<SupplierFields, Integer> SupplierID;

    @FXML
    private TableColumn<SupplierFields, String> Name;

    @FXML
    private TableColumn<SupplierFields, String> ContactInformation;

    @FXML
    private TableColumn<SupplierFields, String> Location;


    @FXML
    public TextField searchID;

    @FXML
    public TextField searchInfo;

    @FXML
    public TextField searchName;
   @FXML
    public TextField searchLocation;

}
