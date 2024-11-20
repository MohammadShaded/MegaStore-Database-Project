package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddCustomerControl;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.CustomerFields;
import com.example.thestore.TablesFields.EmployeeFields;
import com.example.thestore.TablesFields.OrderFields;
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

public class CustomerControl implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        ContactInformation.setCellValueFactory(new PropertyValueFactory<>("ContactInformation"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));

        Refreash.populateCustomerTableView(Table);
        initi();
    }
    @FXML
    private AnchorPane ContainerPane;
    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane.requestFocus();
    }

    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddCustomer/AddCustomer.fxml"));
            Parent popUpRoot = loader.load();
                AddCustomerControl addCustomerController = loader.getController();
                addCustomerController.setMainStage(stage);
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
        Refreash.populateCustomerTableView(Table);
    }

    public void delete(ActionEvent event)throws IOException {

        CustomerFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getCustomerID();
        DeleteFromDatabase.deleteCustomer(id);
    }

    public void update(ActionEvent event)throws IOException {
        CustomerFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getCustomerID();

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddCustomer/AddCustomer.fxml"));
            Parent popUpRoot = loader.load();
                AddCustomerControl addCustomerController = loader.getController();
                addCustomerController.setMainStage(stage);
                addCustomerController.setCustomerFieldsForUpdate(GetFieldsByID.getCustomerFields(selectedItem.getCustomerID()));
                addCustomerController.setUpdateOperation(true);
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

    public void search(ActionEvent event)throws IOException {
        CustomerFields fieldsTosearch= FieldsReader.checkCustomer(this);
        SearchFromDatabase.searchCustomer(fieldsTosearch,Table);
    }


        @FXML
    private PieChart pieChart;
    private void initi(){
        pieChart.setTitle("Most purchased");
        pieChart.getData().addAll(Refreash.populateCustomerPieChart());
    }
        @FXML
    private TableView<CustomerFields> Table;

    @FXML
    private TableColumn<CustomerFields, Integer> CustomerID;

    @FXML
    private TableColumn<CustomerFields, String> Name;

    @FXML
    private TableColumn<CustomerFields, String> ContactInformation;

    @FXML
    private TableColumn<CustomerFields, String> Address;




    @FXML
    public TextField searchAddress;

    @FXML
    public TextField searchID;

    @FXML
    public TextField searchInfo;

    @FXML
    public TextField searchName;


}