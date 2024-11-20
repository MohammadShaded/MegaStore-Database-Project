package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.DeleteFromDatabase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Refreash;
import com.example.thestore.TablesFields.OrderDetailsFields;
import com.example.thestore.TablesFields.SmallTableFields;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.thestore.Methods.Refreash.populateSmallTableView;

public class POrderDetails implements Initializable{


        private Object source;
        Integer ID;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {


            expiryDate.setCellValueFactory(new PropertyValueFactory<>("ExpiryDate"));
            product.setCellValueFactory(new PropertyValueFactory<>("Product"));
            productionDate.setCellValueFactory(new PropertyValueFactory<>("productionDate"));
            quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            warehouse.setCellValueFactory(new PropertyValueFactory<>("warhouse"));


            newQuant.setEditable(true);

            List<String> ProductsNames = DataBase.fetchProductNamesFromDatabase();
            newProd.setItems(FXCollections.observableArrayList(ProductsNames));

            List<String> warehouseNames = DataBase.fetchWarehouseNamesFromDatabase();
            newWare.setItems(FXCollections.observableArrayList(warehouseNames));


        }

        String selectedProduct;
        String selectedWarehouse;

        public void AddItem(ActionEvent event) {

            OrderDetailsFields newDetail = new OrderDetailsFields();
            newDetail.setQuantity(newQuant.getValue());
            newDetail.setWarhouse(newWare.getValue());
            newDetail.setProductionDate(newDate.getValue());
            newDetail.setProduct(newProd.getValue());

            DataBase.PinsertPOrderDetils(newDetail, ID);
            Refreash.populatePOrderDetailsTableView(Table, ID);

        }
    public void delete(ActionEvent event) {
        OrderDetailsFields selectedItem = Table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
            try {
                DeleteFromDatabase.deletePOrderDetail(selectedItem,ID);
                Refreash.populatePOrderDetailsTableView(Table,ID);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

        public void save(ActionEvent event) {
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Efficts.removeSceneBlurry(mainStage);
            stage.close();

        }

        private Stage mainStage;

        public void setMainStage(Stage mainStage) {
            this.mainStage = mainStage;
            source = mainStage.getProperties().get("source");
            if (source != null) {
                ID = (Integer) source;
                LabelNum.setText("Purchase Order #" + ID);
                Refreash.populatePOrderDetailsTableView(Table, ID);

            }
        }


        public void newProduct(ActionEvent event) throws IOException {

            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Efficts.makeSceneBlurry(stage);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddProduct/AddProduct.fxml"));
                Parent popUpRoot = loader.load();
                AddProductControl addProductController = loader.getController();
                addProductController.setMainStage(stage);
                Stage popUpStage = new Stage();
                Scene popUpScene = new Scene(popUpRoot);
                popUpStage.setScene(popUpScene);
                popUpStage.initStyle(StageStyle.UNDECORATED);
                popUpStage.initModality(Modality.APPLICATION_MODAL);
                popUpStage.initOwner((Stage) (((Node) event.getSource()).getScene().getWindow()));

                popUpStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        System.out.println("jjjjjjjjjjjjj");
                        List<String> ProductsNames = DataBase.fetchProductNamesFromDatabase();
                        newProd.setItems(FXCollections.observableArrayList(ProductsNames));
                    }
                });

                popUpStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        DatePicker newDate;

    }
