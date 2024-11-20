package com.example.thestore;

import com.example.thestore.AddingControllers.AddOrderControl;
import com.example.thestore.AddingControllers.AddTransactionControl;
import com.example.thestore.AddingControllers.OrderDetails;
import com.example.thestore.AddingControllers.POrderDetails;
import com.example.thestore.Methods.DeleteFromDatabase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Refreash;
import com.example.thestore.TablesFields.OrderFields;
import com.example.thestore.TablesFields.POrderFields;
import com.example.thestore.TablesFields.TransactionFields;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AccountantScreenControl implements Initializable {


    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrderID.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        Customer.setCellValueFactory(new PropertyValueFactory<>("Customer"));
        Orderdate.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        Ordernote.setCellValueFactory(new PropertyValueFactory<>("Ordernote"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        Refreash.populateOrderTableView(Table);

        POrderID.setCellValueFactory(new PropertyValueFactory<>("POrderID"));
        Supplier.setCellValueFactory(new PropertyValueFactory<>("Supplier"));
        Orderdatep.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        Totalp.setCellValueFactory(new PropertyValueFactory<>("Total"));
        Refreash.populatePOrderTableView(Tablep);


        TransAmount.setCellValueFactory(new PropertyValueFactory<>("TransAmount"));
        TransDate.setCellValueFactory(new PropertyValueFactory<>("TransDate"));
        TransDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        TransID.setCellValueFactory(new PropertyValueFactory<>("TransID"));
        TransType.setCellValueFactory(new PropertyValueFactory<>("TransType"));

        Refreash.populateTransTableView(TableT);

        ChoiceTransType.getItems().addAll("Electricity Bill", "Water Bill", "Internet Bill","Fuel Bill","Sewerage Bill");
    }


    public void refreashT(ActionEvent event)throws IOException {
        Refreash.populateTransTableView(TableT);
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

        }



    }

    public void openAddPopUpT(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddTransaction/AddTransaction.fxml"));
            Parent popUpRoot = loader.load();
            AddTransactionControl addTransactionControl = loader.getController();
            addTransactionControl.setMainStage(stage);
            Stage popUpStage=new Stage();
            Scene popUpScene=new Scene(popUpRoot);
            popUpStage.setScene(popUpScene);
            popUpStage.initStyle(StageStyle.UNDECORATED);
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.initOwner((Stage)(((Node)event.getSource()).getScene().getWindow()));

            popUpStage.show();
        }
        catch(Exception e){

        }



    }


    public void ShowODetails(ActionEvent event)throws IOException {

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

    public void ShowPDetails(ActionEvent event)throws IOException {

        POrderFields selectedItem = Tablep.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
            Efficts.makeSceneBlurry(stage);
            stage.getProperties().put("source", selectedItem.getPOrderID());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddPOrder/POrderDetails.fxml"));
                Parent popUpRoot = loader.load();
                POrderDetails addPOrderController = loader.getController();
                addPOrderController.setMainStage(stage);
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


    private Scene scene;  // this is three objects for scene switching
    private Stage stage;
    private Parent root;
    public void switchToScene1(ActionEvent event) throws IOException {
        root= FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        stage=(Stage)(((Node)event.getSource()).getScene().getWindow());
        scene=new Scene(root);
        scene.getStylesheets().add(getClass().getResource("Styles/Styling.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    private CheckBox spinnersCheck;

    @FXML
    private Spinner<Integer> salaryFrom;

    @FXML
    private Spinner<Integer> salaryTo;
    @FXML
    private void handleSpinnersCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = spinnersCheck.isSelected();
        salaryFrom.setDisable(!isChecked);
        salaryTo.setDisable(!isChecked);
    }

    @FXML
    private CheckBox ODateCheck;

    @FXML
    private DatePicker ODateFrom;
    @FXML
    private DatePicker ODateTo;
    @FXML
    private void handleODateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = ODateCheck.isSelected();
        ODateFrom.setDisable(!isChecked);
        ODateTo.setDisable(!isChecked);
    }

    @FXML
    private CheckBox PDateCheck;

    @FXML
    private DatePicker PDateFrom;
    @FXML
    private DatePicker PDateTo;
    @FXML
    private void handlePDateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = PDateCheck.isSelected();
        PDateFrom.setDisable(!isChecked);
        PDateTo.setDisable(!isChecked);
    }

    @FXML
    private CheckBox TDateCheck;

    @FXML
    private DatePicker TDateFrom;
    @FXML
    private DatePicker TDateTo;
    @FXML
    private void handleTDateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = TDateCheck.isSelected();
        TDateFrom.setDisable(!isChecked);
        TDateTo.setDisable(!isChecked);
    }


    @FXML
    private CheckBox PriceCheck;

    @FXML
    private Spinner<Integer> PriceFrom;

    @FXML
    private Spinner<Integer> PriceTo;
    @FXML
    private void handlePCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = PriceCheck.isSelected();
        PriceFrom.setDisable(!isChecked);
        PriceTo.setDisable(!isChecked);
    }

    @FXML
    private CheckBox PPriceCheck;

    @FXML
    private Spinner<Integer> PtotalFrom;

    @FXML
    private Spinner<Integer> PtotalTo;
    @FXML
    private void handlePPriceCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = PPriceCheck.isSelected();
        PtotalFrom.setDisable(!isChecked);
        PtotalTo.setDisable(!isChecked);
    }

    @FXML
    private CheckBox OPriceCheck;

    @FXML
    private Spinner<Integer> totalFrom;

    @FXML
    private Spinner<Integer> totalTo;
    @FXML
    private void handleOPriceCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = OPriceCheck.isSelected();
        totalFrom.setDisable(!isChecked);
        totalTo.setDisable(!isChecked);
    }

    @FXML
    private CheckBox AmountCheck;

    @FXML
    private Spinner<Integer> AmountFrom;

    @FXML
    private Spinner<Integer> AmountTo;
    @FXML
    private void handleACheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = OPriceCheck.isSelected();
        totalFrom.setDisable(!isChecked);
        totalTo.setDisable(!isChecked);
    }


    public void refreashp(ActionEvent event)throws IOException {
        Refreash.populatePOrderTableView(Tablep);

    }
    @FXML
    private TableView<POrderFields> Tablep;
    @FXML
    private TableColumn<POrderFields, Integer> POrderID;
    @FXML
    private TableColumn<POrderFields, String> Supplier;
    @FXML
    private TableColumn<POrderFields, Timestamp> Orderdatep;
    @FXML
    private TableColumn<POrderFields, Double> Totalp;

    @FXML
    void deleteO(ActionEvent event) {
        OrderFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getOrderID();
        DeleteFromDatabase.deleteOrder(id);
    }

    @FXML
    void deleteP(ActionEvent event) {
        POrderFields selectedItem = Tablep.getSelectionModel().getSelectedItem();
        int id =selectedItem.getPOrderID();
        DeleteFromDatabase.deletePOrder(id);
    }

    @FXML
    void deleteT(ActionEvent event) {
        TransactionFields selectedItem = TableT.getSelectionModel().getSelectedItem();
        int id =selectedItem.getTransID();
        DeleteFromDatabase.deleteTransaction(id);
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
    private ChoiceBox<String>ChoiceTransType;
    @FXML
    private TableView<TransactionFields> TableT;
    @FXML
    private TableColumn<TransactionFields, Double> TransAmount;

    @FXML
    private TableColumn<TransactionFields, LocalDate> TransDate;

    @FXML
    private TableColumn<TransactionFields, String> TransDescription;

    @FXML
    private TableColumn<TransactionFields, Integer> TransID;

    @FXML
    private TableColumn<TransactionFields, String> TransType;


    @FXML
    Label User;

    public void setUser(String userName) {
        User.setText(userName+" (Accountant)");
    }
}
