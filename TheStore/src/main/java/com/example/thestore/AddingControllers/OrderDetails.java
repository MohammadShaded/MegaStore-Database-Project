package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.DeleteFromDatabase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Refreash;
import com.example.thestore.TablesFields.OrderDetailsFields;
import com.example.thestore.TablesFields.OrderFields;
import com.example.thestore.TablesFields.SmallTableFields;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.thestore.Methods.Refreash.populateSmallTableView;

public class OrderDetails implements Initializable  {

    private Object source;
    Integer ID;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        expiryDate.setCellValueFactory(new PropertyValueFactory<>("ExpiryDate"));
        product.setCellValueFactory(new PropertyValueFactory<>("Product"));
        productionDate.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        warehouse.setCellValueFactory(new PropertyValueFactory<>("warhouse"));

        SmallDate.setCellValueFactory(new PropertyValueFactory<>("SmallDate"));
        SmallID.setCellValueFactory(new PropertyValueFactory<>("SmallID"));
        SmallQuantity.setCellValueFactory(new PropertyValueFactory<>("SmallQuantity"));

        newQuant.setEditable(true);

        List<String> ProductsNames = DataBase.fetchProductNamesFromDatabase();
        newProd.setItems(FXCollections.observableArrayList(ProductsNames));

        List<String> warehouseNames = DataBase.fetchWarehouseNamesFromDatabase();
        newWare.setItems(FXCollections.observableArrayList(warehouseNames));

        newProd.setOnAction(event -> updateOffersTable());
        newWare.setOnAction(event -> updateOffersTable());



    }
    String selectedProduct;
    String selectedWarehouse;
    private void updateOffersTable() {
        // Implement logic to update TableView based on selected items
        selectedProduct = newProd.getValue();
        selectedWarehouse = newWare.getValue();
        // Fetch data from the database and update the TableView
        populateSmallTableView(SmallTable,selectedWarehouse,selectedProduct);
    }
    public void AddItem(ActionEvent event){
        SmallTableFields selectedItem = SmallTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            warning.setText("");
            if(newQuant.getValue()<=selectedItem.getSmallQuantity()){
                OrderDetailsFields newDetail = new OrderDetailsFields();
                newDetail.setQuantity(newQuant.getValue());newDetail.setWarhouse(newWare.getValue());
                newDetail.setProductionDate(selectedItem.getSmallDate());
                newDetail.setProduct(newProd.getValue());

                DataBase.PinsertOrderDetils(newDetail, ID);
                Refreash.populateOrderDetailsTableView(Table, ID);
                populateSmallTableView(SmallTable,selectedWarehouse,selectedProduct);

            }
            else {
                warning.setText("You can not select quantity (>"+selectedItem.getSmallQuantity()+")");

            }
        }
        else{
            warning.setText("Select One from the Table");
        }
    }

    public void save(ActionEvent event){
        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.removeSceneBlurry(mainStage);
        stage.close();

    }
    private Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
        source = mainStage.getProperties().get("source");
        if (source != null) {
            ID = (Integer) source;
            LabelNum.setText("Order #"+ID);
            Refreash.populateOrderDetailsTableView(Table,ID);

        }
}
    public void delete(ActionEvent event){
        OrderDetailsFields selectedItem = Table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            try {
                DeleteFromDatabase.deleteOrderDetail(selectedItem,ID);
                Refreash.populateOrderDetailsTableView(Table,ID);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Refreash.populateOrderDetailsTableView(Table, ID);
        populateSmallTableView(SmallTable,selectedWarehouse,selectedProduct);

    }





    @FXML
    Label LabelNum;
    @FXML
    private TableView<OrderDetailsFields> Table;
    @FXML
    private TableColumn<OrderDetailsFields, LocalDate> expiryDate;
    @FXML
    private TableColumn<OrderDetailsFields, String> product;
    @FXML
    private TableColumn<OrderDetailsFields, LocalDate> productionDate;
    @FXML
    private TableColumn<OrderDetailsFields, Integer> quantity;
    @FXML
    private TableColumn<OrderDetailsFields, String> warehouse;



    @FXML
    ChoiceBox<String> newProd;
    @FXML
    Spinner<Integer> newQuant;
    @FXML
    ChoiceBox<String> newWare;
    @FXML
    Label warning;



    @FXML
    private TableView<SmallTableFields> SmallTable;
    @FXML
    private TableColumn<SmallTableFields, LocalDate> SmallDate;
    @FXML
    private TableColumn<SmallTableFields, Integer> SmallID;
    @FXML
    private TableColumn<SmallTableFields, Integer> SmallQuantity;
}
