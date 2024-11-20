package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.SlidesControllers.EmployeeSlidesControl;
import com.example.thestore.SlidesControllers.OrderSlidesControl;
import com.example.thestore.TablesFields.OrderFields;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddOrderControl implements Initializable {


    int order;
    Parent Slide[]=new Parent[1];
    FXMLLoader loader []=new FXMLLoader[1];
    @FXML
    AnchorPane interiar;
    @FXML
    Button PreviousButton;
    @FXML
    Button NextButton;
    OrderSlidesControl slideControl;
    OrderFields ONew;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ONew = new OrderFields();
            order = 0;
            PreviousButton.setDisable(true);
            loader[0]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddOrder/First.fxml"));
            for(int i=0;i<1;i++) Slide[i]=loader[i].load();
            slideControl=loader[0].getController();
            NextButton.setText("Save");
            NextButton.setStyle("-fx-background-color: #3dbd29;");


            if(interiar!=null){
                interiar.getChildren().removeAll();
                interiar.getChildren().setAll(Slide[0]);
            }
            OrderSlidesControl slideControl2= loader[0].getController();
            List<String> CustomerNames = DataBase.fetchCustomerNamesFromDatabase();
            slideControl2.Customer.setItems(FXCollections.observableArrayList(CustomerNames));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }



    public void Next(ActionEvent event) {
        order++;
        slideControl= loader[0].getController();

        ONew.setOrderID(Integer.valueOf(slideControl.OrderID.getText()));
        ONew.setOrdernote(slideControl.Ordernote.getText());
        ONew.setOrderdate(java.sql.Timestamp.valueOf(slideControl.Orderdate.getValue().atStartOfDay()));
        ONew.setCustomer((String)slideControl.Customer.getValue());
        DataBase.PinsertOrder(ONew);

        Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
        Efficts.removeSceneBlurry(mainStage);
        stage.close();

        Efficts.makeSceneBlurry(mainStage);
        mainStage.getProperties().put("source", ONew.getOrderID());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddOrder/OrderDetails.fxml"));
            Parent popUpRoot = loader.load();
            OrderDetails addOrderController = loader.getController();
            addOrderController.setMainStage(mainStage);
            Scene popUpScene = new Scene(popUpRoot);
            Stage popUpStage = new Stage();
            popUpStage.setScene(popUpScene);
            popUpStage.initStyle(StageStyle.UNDECORATED);
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.initOwner((Stage) (((Node) event.getSource()).getScene().getWindow()));

            popUpStage.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }



    public void Previous(ActionEvent event) {
    }

    public void cancel(ActionEvent event){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Changes");
        alert.setHeaderText("Discard changes and cancel? Any unsaved changes will be lost.");
        if(alert.showAndWait().get()== ButtonType.OK) {
            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
            Efficts.removeSceneBlurry(mainStage);
            stage.close();
        }

    }
    private Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


}
