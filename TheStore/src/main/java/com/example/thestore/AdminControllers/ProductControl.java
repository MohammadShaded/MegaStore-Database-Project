package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddEmployeeControl;
import com.example.thestore.AddingControllers.AddProductControl;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.CustomerFields;
import com.example.thestore.TablesFields.OrderFields;
import com.example.thestore.TablesFields.ProductFields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.net.URL;
import java.time.Period;
import java.util.ResourceBundle;

public class ProductControl implements Initializable {
    @FXML
    public ChoiceBox<String> choiceRoll;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (choiceRoll != null) {
                // Populate the ChoiceBox with items
                choiceRoll.getItems().addAll("","local", "Saudi arabia", "Turkey","Egypt","Jordan");
                // Set an initial selection if needed
                choiceRoll.getSelectionModel().selectFirst();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        productid.setCellValueFactory(new PropertyValueFactory<>("productid"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        pname.setCellValueFactory(new PropertyValueFactory<>("pname"));
        manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        shelfLife.setCellValueFactory(new PropertyValueFactory<>("shelfLife"));
        Refreash.populateProductTableView(Table);

        initi();
    }

    private void initi(){
        pieChart.getData().addAll(Refreash.populatePieChart());
    }


    @FXML
    void refreash(ActionEvent event) {
        Refreash.populateProductTableView(Table);
    }
    @FXML
    public void search(ActionEvent event)throws IOException {
        ProductFields fieldsTosearch= FieldsReader.checkProduct(this);
        SearchFromDatabase.searchProduct(fieldsTosearch,Table);
    }
    @FXML
    private AnchorPane ContainerPane;
    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane.requestFocus();
    }




    @FXML
    public CheckBox PriceCheck;

    @FXML
    public Spinner<Integer> PriceFrom;

    @FXML
    public Spinner<Integer> PriceTo;
    @FXML
    private void handlePriceCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = PriceCheck.isSelected();
        PriceFrom.setDisable(!isChecked);
        PriceTo.setDisable(!isChecked);
    }

    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddProduct/AddProduct.fxml"));
            Parent popUpRoot = loader.load();
                AddProductControl addProductController = loader.getController();
                addProductController.setMainStage(stage);
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
        ProductFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getProductid();

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddProduct/AddProduct.fxml"));
            Parent popUpRoot = loader.load();
            AddProductControl addProductController = loader.getController();
            addProductController.setMainStage(stage);
            addProductController.setProductFieldsForUpdate(selectedItem);
            addProductController.setUpdateOperation(true);
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

        ProductFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getProductid();
        DeleteFromDatabase.deleteProduct(id);

    }
    @FXML
    private TableView<ProductFields> Table;

    @FXML
    private TableColumn<ProductFields,String> brand;


    @FXML
    private TableColumn<ProductFields, String> manufacturer;

    @FXML
    private TableColumn<ProductFields, String> pname;

    @FXML
    private TableColumn<ProductFields, Double> price;

    @FXML
    private TableColumn<ProductFields, Integer> productid;

    @FXML
    private TableColumn<ProductFields, String> shelfLife;




    @FXML
    public TextField searchBrand;

    @FXML
    public TextField searchID;

    @FXML
    public TextField searchName;
    @FXML
    private PieChart pieChart;
}
