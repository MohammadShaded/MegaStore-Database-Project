package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddEmployeeControl;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.EmployeeFields;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeControl implements Initializable {

    @FXML
    public ChoiceBox choiceRoll;
    @FXML
    AnchorPane ContainerPane;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (choiceRoll != null) {
            // Populate the ChoiceBox with items
            choiceRoll.getItems().addAll("","Admin", "Accountant", "Manager","Worker");

            // Set an initial selection if needed
            choiceRoll.getSelectionModel().selectFirst();
            }
            }
        catch (Exception e){
            System.out.println(e);
        }


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


        Refreash.populateEmployeeTableView(Table,true,"");



    }


    public void delete(ActionEvent event)throws IOException {

        EmployeeFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getID();
        DeleteFromDatabase.deleteEmployee(id);

    }


    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane.requestFocus();
    }

    @FXML
    public CheckBox spinnersCheck;

    @FXML
    public Spinner<Integer> salaryFrom;

    @FXML
    public Spinner<Integer> salaryTo;
    @FXML
    private void handleSpinnersCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = spinnersCheck.isSelected();
        salaryFrom.setDisable(!isChecked);
        salaryTo.setDisable(!isChecked);
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
    public CheckBox GenderCheck;

    @FXML
    public RadioButton Male;

    @FXML
    public RadioButton Female;
    @FXML
    private void handleGenderCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = GenderCheck.isSelected();
        Male.setDisable(!isChecked);
        Female.setDisable(!isChecked);
    }



    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.getProperties().put("source", "EmployeeControl");
        Efficts.makeSceneBlurry(stage);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddEmployee/AddEmployee.fxml"));
            Parent popUpRoot = loader.load();
                AddEmployeeControl addEmployeeController = loader.getController();
                addEmployeeController.setMainStage(stage);
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
        EmployeeFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getID();
        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        stage.getProperties().put("source", "EmployeeControl");
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddEmployee/AddEmployee.fxml"));
            Parent popUpRoot = loader.load();
            AddEmployeeControl addEmployeeController = loader.getController();
            addEmployeeController.setMainStage(stage);
            addEmployeeController.setEmployeeFieldsForUpdate(selectedItem);
            addEmployeeController.setUpdateOperation(true);
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


    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "shadid";
    private static final String PASSWORD = "123456";


    public void refreash(ActionEvent event)throws IOException {
        Refreash.populateEmployeeTableView(Table,true,"");
    }

    public void search(ActionEvent event)throws IOException {
        EmployeeFields fieldsTosearch= FieldsReader.checkEmployee(this);
        SearchFromDatabase.searchEmployees(fieldsTosearch,Table);
    }

    @FXML
    public TextField searchCity;

    @FXML
    public TextField searchEmail;

    @FXML
    public TextField searchFierstName;

    @FXML
    public TextField searchID;

    @FXML
    public TextField searchLastName;

    @FXML
    public TextField searchPhone;

    @FXML
    public TextField searchStreet;

    @FXML
    public TextField searchWarehouse;





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




}
