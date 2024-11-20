package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddEmployeeControl;
import com.example.thestore.AddingControllers.AddInventoryControl;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.CustomerFields;
import com.example.thestore.TablesFields.InventoryFields;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryControl implements Initializable {

    @FXML
    public ChoiceBox<String>searchProduct;
    @FXML
    public ChoiceBox<String>searchWarehouse;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> ProductsNames=new ArrayList<String>() ;
        ProductsNames.add("");
        ProductsNames.addAll(DataBase.fetchProductNamesFromDatabase());
        searchProduct.setItems(FXCollections.observableArrayList(ProductsNames));
        searchProduct.getSelectionModel().selectFirst();

        List<String> WarehouseNames=new ArrayList<String>() ;
        WarehouseNames.add("");
        WarehouseNames.addAll(DataBase.fetchWarehouseNamesFromDatabase());
        searchWarehouse.setItems(FXCollections.observableArrayList(WarehouseNames));
        searchWarehouse.getSelectionModel().selectFirst();


        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        inventoryid.setCellValueFactory(new PropertyValueFactory<>("inventoryid"));
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        //location.setCellValueFactory(new PropertyValueFactory<>("location"));
        productionDate.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
        warehouse.setCellValueFactory(new PropertyValueFactory<>("warehouse"));
        expiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        Refreash.populateInventoryTableView(Table,true,"");
    }

    public void refreash(ActionEvent event)throws IOException {
        Refreash.populateInventoryTableView(Table,true,"");
    }
    public void search(ActionEvent event)throws IOException {
        InventoryFields fieldsTosearch= FieldsReader.checkInventory(this);
        SearchFromDatabase.searchInventory(fieldsTosearch,Table);
    }
    @FXML
    private AnchorPane ContainerPane;
    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane.requestFocus();
    }

    @FXML
    public CheckBox spinnersCheck;

    @FXML
    public Spinner<Integer> quantityFrom;

    @FXML
    public Spinner<Integer> quantityTo;
    @FXML
    private void handleSpinnersCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = spinnersCheck.isSelected();
        quantityFrom.setDisable(!isChecked);
        quantityTo.setDisable(!isChecked);
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
    public CheckBox EDateCheck;

    @FXML
    public DatePicker EDateFrom;
    @FXML
    public DatePicker EDateTo;
    @FXML
    private void handleEDateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = EDateCheck.isSelected();
        EDateFrom.setDisable(!isChecked);
        EDateTo.setDisable(!isChecked);
    }


    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);
        stage.getProperties().put("source", "InventoryControl");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddInventory/AddInventory.fxml"));
            Parent popUpRoot = loader.load();
                AddInventoryControl addInventoryController = loader.getController();
                addInventoryController.setMainStage(stage);
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
        InventoryFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getInventoryid();

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);
        stage.getProperties().put("source", "InventoryControl");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddInventory/AddInventory.fxml"));
            Parent popUpRoot = loader.load();
            AddInventoryControl addInventoryController = loader.getController();
            addInventoryController.setMainStage(stage);
            addInventoryController.setInventoryFieldsForUpdate(selectedItem);
            addInventoryController.setUpdateOperation(true);
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


    public void report(ActionEvent event)throws IOException {
        final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USER = "shadid";
        final String PASSWORD = "123456";
        Connection con;
        InputStream input;
        JasperDesign jd;
        JasperReport jr;
        JasperPrint jp;
        OutputStream output;

        try{
            DriverManager.deregisterDriver(new org.postgresql.Driver());
            con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            input=new FileInputStream(new File("src/MyReports/ProductQuantity.jrxml"));
            //input = getClass().getResourceAsStream("/FinancialReport.jrxml");
            jd= JRXmlLoader.load(input);
            jr= JasperCompileManager.compileReport(jd);
            jp= JasperFillManager.fillReport(jr,null,con);// second parameter here use to send parameter to the report, if there is specialization
            output=new FileOutputStream(new File("src/MyReports/InventoryReport.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
            output.close();
            input.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void delete(ActionEvent event)throws IOException {

        InventoryFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getInventoryid();
        DeleteFromDatabase.deleteInventory(id);

    }

    @FXML
    public TextField searchIID;

    @FXML
    private TableView<InventoryFields> Table;

    @FXML
    private TableColumn<InventoryFields, LocalDate> expiryDate;

    @FXML
    private TableColumn<InventoryFields, Integer> inventoryid;



    @FXML
    private TableColumn<InventoryFields, String> product;

    @FXML
    private TableColumn<InventoryFields, LocalDate> productionDate;

    @FXML
    private TableColumn<InventoryFields, Integer> quantity;

    @FXML
    private TableColumn<InventoryFields, String> warehouse;

}