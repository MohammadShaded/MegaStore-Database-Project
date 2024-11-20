package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddWarehouseControl;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.EmployeeFields;
import com.example.thestore.TablesFields.WarehouseFields;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class WarehouseControl  implements Initializable {


    @FXML
    public ChoiceBox<String> searchManager;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> managers=new ArrayList<String>() ;
        managers.add("");
        managers.addAll(DataBase.fetchManagerNamesFromDatabase(false));
        searchManager.setItems(FXCollections.observableArrayList(managers));
        searchManager.getSelectionModel().selectFirst();


        wname.setCellValueFactory(new PropertyValueFactory<>("wname"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        manager.setCellValueFactory(new PropertyValueFactory<>("manager"));
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        warehouseID.setCellValueFactory(new PropertyValueFactory<>("warehouseid"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        Refreash.populateWarehouseTableView(Table);
        initi();
    }

    public void refreash(ActionEvent event)throws IOException {
        Refreash.populateWarehouseTableView(Table);
    }

    public void delete(ActionEvent event)throws IOException {

        WarehouseFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getWarehouseid();
        DeleteFromDatabase.deleteWarehouse(id);
    }

    public void report(ActionEvent event)throws IOException {
          final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
          final String USER = "shadid";
          final String PASSWORD = "123456";
        Connection con;
        InputStream input;
        JasperDesign    jd;
        JasperReport jr;
        JasperPrint jp;
        OutputStream output;

        try{
            DriverManager.deregisterDriver(new org.postgresql.Driver());
             con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            input=new FileInputStream(new File("src/MyReports/FinancialReport.jrxml"));
            //input = getClass().getResourceAsStream("/FinancialReport.jrxml");
            jd= JRXmlLoader.load(input);
            jr= JasperCompileManager.compileReport(jd);
            jp= JasperFillManager.fillReport(jr,null,con);// second parameter here use to send parameter to the report, if there is specialization
            output=new FileOutputStream(new File("src/MyReports/WarehouseRep.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
            output.close();
            input.close();
            con.close();
        }
        catch (Exception e) {
        e.printStackTrace();
        }

        }
    public void search(ActionEvent event) {

        WarehouseFields fieldsTosearch= FieldsReader.checkWarehouse(this);
        SearchFromDatabase.searchWarehouse(fieldsTosearch,Table);
    }



        @FXML
    private PieChart pieChart;
    private void initi(){
        pieChart.getData().addAll(Refreash.populateWarehousePieChart());
    }
    @FXML
    public CheckBox spinnersCheck;

    @FXML
    public Spinner<Integer> capacityFrom;

    @FXML
    public Spinner<Integer> capacityTo;

    @FXML
    private void handleSpinnersCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = spinnersCheck.isSelected();
        capacityFrom.setDisable(!isChecked);
        capacityTo.setDisable(!isChecked);
    }

    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddWarehouse/AddWarehouse.fxml"));
            Parent popUpRoot = loader.load();
                AddWarehouseControl addWarehouseControl = loader.getController();
                addWarehouseControl.setMainStage(stage);
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
        WarehouseFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getWarehouseid();

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddWarehouse/AddWarehouse.fxml"));
            Parent popUpRoot = loader.load();
            AddWarehouseControl addWarehouseController = loader.getController();
            addWarehouseController.setMainStage(stage);
            addWarehouseController.setWarehouseFieldsForUpdate(selectedItem);
            addWarehouseController.setUpdateOperation(true);
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

    @FXML
    private TableView<WarehouseFields> Table;
    @FXML
    private TableColumn<WarehouseFields, Integer> capacity;
    @FXML
    private TableColumn<WarehouseFields, String> city;
    @FXML
    private TableColumn<WarehouseFields, String> manager;
    @FXML
    private TableColumn<WarehouseFields, String> street;
    @FXML
    private TableColumn<WarehouseFields, Integer> warehouseID;
    @FXML
    private TableColumn<WarehouseFields, String> wname;

    @FXML
    private AnchorPane ContainerPane1;
    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane1.requestFocus();
    }



    @FXML
    public TextField searchCity;

    @FXML
    public TextField searchID;

    @FXML
    public TextField searchName;

    @FXML
    public TextField searchStreet;

}