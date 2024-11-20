package com.example.thestore.AdminControllers;

import com.example.thestore.AddingControllers.AddPOrderControl;
import com.example.thestore.AddingControllers.POrderDetails;
import com.example.thestore.Methods.*;
import com.example.thestore.TablesFields.OrderFields;
import com.example.thestore.TablesFields.POrderFields;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class POrderControl implements Initializable {
    @FXML
    public ChoiceBox <String> searchSupplier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> SupplierNames=new ArrayList<String>() ;
        SupplierNames.add("");
        SupplierNames.addAll(DataBase.fetchSupplierNamesFromDatabase());
        searchSupplier.setItems(FXCollections.observableArrayList(SupplierNames));
        searchSupplier.getSelectionModel().selectFirst();

        POrderID.setCellValueFactory(new PropertyValueFactory<>("POrderID"));
        Supplier.setCellValueFactory(new PropertyValueFactory<>("Supplier"));
        Orderdate.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        Total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        Refreash.populatePOrderTableView(Table);
    }

    public void ShowDetails(ActionEvent event)throws IOException {

        POrderFields selectedItem = Table.getSelectionModel().getSelectedItem();

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



    public void delete(ActionEvent event)throws IOException {

        POrderFields selectedItem = Table.getSelectionModel().getSelectedItem();
        int id =selectedItem.getPOrderID();
        DeleteFromDatabase.deletePOrder(id);

    }
    @FXML
    private AnchorPane ContainerPane;
    @FXML
    private void handleTextFieldsContainerClicked(MouseEvent event) {
        // Check if any TextField has focus and remove the focus
        ContainerPane.requestFocus();
    }
    @FXML
    public CheckBox PDateCheck;

    @FXML
    public DatePicker PDateFrom;
    @FXML
    public DatePicker PDateTo;
    @FXML
    private void handlePDateCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = PDateCheck.isSelected();
        PDateFrom.setDisable(!isChecked);
        PDateTo.setDisable(!isChecked);
    }

    @FXML
    public CheckBox PspinnersCheck;

    @FXML
    public Spinner<Integer> PtotalFrom;

    @FXML
    public Spinner<Integer> PtotalTo;
    @FXML
    private void handleSpinnersCheckAction() {
        // Toggle the disabled state of the spinners based on the checkbox state
        boolean isChecked = PspinnersCheck.isSelected();
        PtotalFrom.setDisable(!isChecked);
        PtotalTo.setDisable(!isChecked);
    }

    public void openAddPopUp(ActionEvent event)throws IOException {

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.makeSceneBlurry(stage);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddPOrder/AddPOrder.fxml"));
            Parent popUpRoot = loader.load();
                AddPOrderControl addPOrderController = loader.getController();
                addPOrderController.setMainStage(stage);
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
        Refreash.populatePOrderTableView(Table);

    }
    public void search(ActionEvent event)throws IOException {
        POrderFields fieldsTosearch= FieldsReader.checkPOrder(this);
        SearchFromDatabase.searchPOrder(fieldsTosearch,Table);
    }
    @FXML
    private TableView<POrderFields> Table;
    @FXML
    private TableColumn<POrderFields, Integer> POrderID;
    @FXML
    private TableColumn<POrderFields, String> Supplier;
    @FXML
    private TableColumn<POrderFields, Timestamp> Orderdate;


    @FXML
    public TextField searchPID;
    @FXML
    private TableColumn<POrderFields, Double> Total;
}

