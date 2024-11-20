package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddEmployeeControl;
import com.example.thestore.AddingControllers.AddOrderControl;
import com.example.thestore.AddingControllers.OrderDetails;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.CustomerFields;
import com.example.thestore.TablesFields.OrderFields;
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
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderControl implements Initializable {
    @FXML
    public TextField searchOID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> CustomerNames=new ArrayList<String>() ;
        CustomerNames.add("");
        CustomerNames.addAll(DataBase.fetchCustomerNamesFromDatabase());
        searchCustomer.setItems(FXCollections.observableArrayList(CustomerNames));
        searchCustomer.getSelectionModel().selectFirst();

        OrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        Customer.setCellValueFactory(new PropertyValueFactory<>("Customer"));
        Orderdate.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        Ordernote.setCellValueFactory(new PropertyValueFactory<>("Ordernote"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        Refreash.populateOrderTableView(Table);
    }

    @FXML
    private AnchorPane ContainerPane;
    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane.requestFocus();
    }

    @FXML
    public CheckBox DateCheck;
    @FXML
    public DatePicker DateFrom;
    @FXML
    public DatePicker DateTo;
    @FXML
    private void handleDateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = DateCheck.isSelected();
        DateFrom.setDisable(!isChecked);
        DateTo.setDisable(!isChecked);
    }

    @FXML
    public CheckBox spinnersCheck;

    @FXML
    public Spinner<Integer> totalFrom;

    @FXML
    public Spinner<Integer> totalTo;
    @FXML
    private void handleSpinnersCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = spinnersCheck.isSelected();
        totalFrom.setDisable(!isChecked);
        totalTo.setDisable(!isChecked);
    }

    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddOrder/AddOrder.fxml"));
            Parent popUpRoot = loader.load();
                AddOrderControl addOrderController = loader.getController();
                addOrderController.setMainStage(stage);
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
    public void delete(ActionEvent event)throws IOException {

        OrderFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getOrderID();
        DeleteFromDatabase.deleteOrder(id);

    }
    public void search(ActionEvent event)throws IOException {
        OrderFields fieldsTosearch= FieldsReader.checkOrder(this);
        SearchFromDatabase.searchOrder(fieldsTosearch,Table);
    }
    public void ShowDetails(ActionEvent event)throws IOException {

        OrderFields selectedItem = Table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);
        stage.getProperties().put("source", selectedItem.getOrderID());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddOrder/OrderDetails.fxml"));
                Parent popUpRoot = loader.load();
                OrderDetails addOrderController = loader.getController();
                addOrderController.setMainStage(stage);
                Stage popUpStage = new Stage();
                Scene popUpScene = new Scene(popUpRoot);
                popUpStage.setScene(popUpScene);
                popUpStage.initStyle(StageStyle.UNDECORATED);
                popUpStage.initModality(Modality.APPLICATION_MODAL);
                popUpStage.initOwner((Stage) (((Node) event.getSource()).getScene().getWindow()));

                popUpStage.show();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    public void refreash(ActionEvent event)throws IOException {
        Refreash.populateOrderTableView(Table);

    }
    @FXML
    private TableView<OrderFields> Table;
    @FXML
    private TableColumn<OrderFields, Integer> OrderID;

    @FXML
    private TableColumn<OrderFields, String> Customer;

    @FXML
    private TableColumn<OrderFields, Timestamp> Orderdate;

    @FXML
    private TableColumn<OrderFields, String> Ordernote;

    @FXML
    private TableColumn<OrderFields, Double> Total;



    @FXML
    public ChoiceBox <String>searchCustomer;


}