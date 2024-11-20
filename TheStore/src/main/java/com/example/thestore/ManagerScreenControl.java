package com.example.thestore;

import com.example.thestore.AddingControllers.AddEmployeeControl;
import com.example.thestore.AddingControllers.AddInventoryControl;
import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.DeleteFromDatabase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Refreash;
import com.example.thestore.TablesFields.EmployeeFields;
import com.example.thestore.TablesFields.InventoryFields;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManagerScreenControl implements Initializable {

    String userName;
    String warehouseName;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        City.setCellValueFactory(new PropertyValueFactory<>("city"));
        Street.setCellValueFactory(new PropertyValueFactory<>("street"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email")); // Ensure the case matches with your property name
        Warehouse.setCellValueFactory(new PropertyValueFactory<>("warehouse"));
        HireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        Salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        EmployeeID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Role.setCellValueFactory(new PropertyValueFactory<>("choiceRoll"));
        Birthdate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        Bank.setCellValueFactory(new PropertyValueFactory<>("bankInfo"));
        Password.setCellValueFactory(new PropertyValueFactory<>("password"));


        Refreash.populateEmployeeTableView(Table,false,warehouseName);


        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        inventoryid.setCellValueFactory(new PropertyValueFactory<>("inventoryid"));
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        productionDate.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
        warehouse.setCellValueFactory(new PropertyValueFactory<>("warehouse"));
        expiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));

        Refreash.populateInventoryTableView(TableI,false,warehouseName);

    }

    public void refreash(ActionEvent event)throws IOException {
        Refreash.populateEmployeeTableView(Table,false,warehouseName);

    }
    public void refreashI(ActionEvent event)throws IOException {
        Refreash.populateInventoryTableView(TableI,false,warehouseName);

    }

    @FXML
    Label USER;


    public void settingsPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WarehouseSettings.fxml"));
            Parent popUpRoot = loader.load();
            WarehouseSettings warehouseSettings = loader.getController();
            warehouseSettings.setMainStage(stage);
            warehouseSettings.setSettings(DataBase.getWarehouseSettings(warehouseName));
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

    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.getProperties().put("source", "ManagerScreen");
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddEmployee/AddEmployee.fxml"));
            Parent popUpRoot = loader.load();
            AddEmployeeControl addEmployeeController = loader.getController();
            addEmployeeController.setMainStage(stage);
            addEmployeeController.WarehousNameManagered=warehouseName;
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


    public void openInventoryAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.getProperties().put("source", "ManagerScreen");
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddInventory/AddInventory.fxml"));
            Parent popUpRoot = loader.load();
                AddInventoryControl addInventoryControl = loader.getController();
                addInventoryControl.setMainStage(stage);
            addInventoryControl.WarehousNameManagered=warehouseName;
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
    private CheckBox DateCheck;

    @FXML
    private DatePicker DateFrom;
    @FXML
    private DatePicker DateTo;
    @FXML
    private void handleDateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = DateCheck.isSelected();
        DateFrom.setDisable(!isChecked);
        DateTo.setDisable(!isChecked);
    }

    @FXML
    private CheckBox GenderCheck;

    @FXML
    private RadioButton Male;

    @FXML
    private RadioButton Female;
    @FXML
    private void handleGenderCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = GenderCheck.isSelected();
        Male.setDisable(!isChecked);
        Female.setDisable(!isChecked);
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
    private CheckBox EDateCheck;

    @FXML
    private DatePicker EDateFrom;
    @FXML
    private DatePicker EDateTo;
    @FXML
    private void handleEDateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = EDateCheck.isSelected();
        EDateFrom.setDisable(!isChecked);
        EDateTo.setDisable(!isChecked);
    }


    @FXML
    private CheckBox QuantityCheck;

    @FXML
    private Spinner<Integer> quantityFrom;

    @FXML
    private Spinner<Integer> quantityTo;
    @FXML
    private void handleQCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = QuantityCheck.isSelected();
        quantityFrom.setDisable(!isChecked);
        quantityTo.setDisable(!isChecked);
    }
    public void deleteE(ActionEvent event)throws IOException {

        EmployeeFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getID();
        DeleteFromDatabase.deleteEmployee(id);

    }
    public void deleteI(ActionEvent event)throws IOException {

        InventoryFields selectedItem = TableI.getSelectionModel().getSelectedItem();
        int id =selectedItem.getInventoryid();
        DeleteFromDatabase.deleteInventory(id);

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
    private TableView<EmployeeFields> Table;
    @FXML
    private TableColumn<EmployeeFields, String> Bank;
    @FXML
    private TableColumn<EmployeeFields, LocalDate> Birthdate;
    @FXML
    private TableColumn<EmployeeFields, String> City;
    @FXML
    private TableColumn<EmployeeFields, String> Email;
    @FXML
    private TableColumn<EmployeeFields, Integer> EmployeeID;
    @FXML
    private TableColumn<EmployeeFields, String> FirstName;
    @FXML
    private TableColumn<EmployeeFields, Character> Gender;
    @FXML
    private TableColumn<EmployeeFields, LocalDate> HireDate;
    @FXML
    private TableColumn<EmployeeFields, String> LastName;
    @FXML
    private TableColumn<EmployeeFields, String> Password;
    @FXML
    private TableColumn<EmployeeFields, String> Phone;
    @FXML
    private TableColumn<EmployeeFields, String> Role;
    @FXML
    private TableColumn<EmployeeFields, Integer> Salary;
    @FXML
    private TableColumn<EmployeeFields, String> Street;
    @FXML
    private TableColumn<EmployeeFields, String> Warehouse;




    @FXML
    private TableView<InventoryFields> TableI;

    @FXML
    private TableColumn<InventoryFields, LocalDate> expiryDate;

    @FXML
    private TableColumn<InventoryFields, Integer> inventoryid;

    @FXML
    private TableColumn<InventoryFields, String> location;

    @FXML
    private TableColumn<InventoryFields, String> product;

    @FXML
    private TableColumn<InventoryFields, LocalDate> productionDate;

    @FXML
    private TableColumn<InventoryFields, Integer> quantity;

    @FXML
    private TableColumn<InventoryFields, String> warehouse;

    public void setUser(String User,String Warehousename) {
        userName=User;
        warehouseName=Warehousename;
        USER.setText(userName+" (Manager of \" "+warehouseName+"\")");

    }
}
