package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Transitions;
import com.example.thestore.Methods.UpdateFromDatabase;
import com.example.thestore.SlidesControllers.InventorySlidesControl;
import com.example.thestore.SlidesControllers.ProductSlidesControl;
import com.example.thestore.TablesFields.ProductFields;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductControl implements Initializable {


    int order;
    Parent Slide[]=new Parent[2];
    FXMLLoader loader []=new FXMLLoader[2];
    @FXML
    AnchorPane interiar;
    @FXML
    Button PreviousButton;
    @FXML
    Button NextButton;
    ProductSlidesControl slideControl;
    ProductFields product;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            product=new ProductFields();
            order=0;
            PreviousButton.setDisable(true);

            loader[0]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddProduct/First.fxml"));
            loader[1]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddProduct/Second.fxml"));

            for(int i=0;i<2;i++) Slide[i]=loader[i].load();


            if(interiar!=null){
                interiar.getChildren().removeAll();
                interiar.getChildren().setAll(Slide[0]);

            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        ProductSlidesControl slideControl2=loader[1].getController();
        slideControl2.Manufacturer.getItems().addAll("local", "Saudi arabia", "Turkey","Egypt","Jordan");

    }
    @FXML
    private AnchorPane popUpPane;

    public void cancel(ActionEvent event){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Changes");
        alert.setHeaderText("Discard changes and cancel? Any unsaved changes will be lost.");
        if(alert.showAndWait().get()== ButtonType.OK){
            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
            Efficts.removeSceneBlurry(mainStage);
            stage.close();}
    }

    public void Next(ActionEvent event){
        order++;

        if(order==1) {
            slideControl=loader[0].getController();
            NextButton.setText("Save");
            NextButton.setStyle("-fx-background-color: #3dbd29;");

            product.setProductid(Integer.valueOf(slideControl.ProductID.getText()));
            product.setPname(slideControl.Name.getText());
            product.setBrand(slideControl.Brand.getText());
            product.setShelfLife(
                    "'"+slideControl.ShilfYear.getValue()+" year "+
                    slideControl.ShilfMonth.getValue()+" months "+
                    slideControl.ShilfDay.getValue()+" days'");


        }

        if(order==2){
            slideControl= loader[1].getController();

            product.setManufacturer((String) slideControl.Manufacturer.getValue());
            product.setPrice((slideControl.Price.getValue()));
            if(UPDATE_OPERATION){
                UpdateFromDatabase.updateProduct(product);
            }
            else DataBase.PinsertProduct(product);            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
            Efficts.removeSceneBlurry(mainStage);
            stage.close();
            return;
        }



        PreviousButton.setDisable(false);
        TranslateTransition slideOut = Transitions.createSlideTransition(interiar, 0.5,368, 0);
        if(interiar!=null){
            interiar.getChildren().removeAll();
            interiar.getChildren().setAll(Slide[order]);
        }
        slideOut.play();

    }
    public void Previous(ActionEvent event){
        order--;
        if(order==0){
            NextButton.setText("Next");
            NextButton.setStyle("-fx-background-color: #0d5287;");
            PreviousButton.setDisable(true);
        }
        TranslateTransition slideOut = Transitions.createSlideTransition(interiar, 0.5,-368, 0);
        if(interiar!=null){
            interiar.getChildren().removeAll();
            interiar.getChildren().setAll(Slide[order]);
        }
        slideOut.play();

    }
    private Stage mainStage;
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;

    }


    ProductFields FieldsForUpdate;
    @FXML
    Label lbl;
    public void setProductFieldsForUpdate(ProductFields Fields){
        lbl.setText("Update Inventory");

        int shelfLife []=new int[3];
        FieldsForUpdate=Fields;
        ProductSlidesControl slideControl2= loader[0].getController();
        slideControl2.ProductID.setText(String.valueOf(Fields.getProductid()));
        slideControl2.ProductID.setDisable(true);
        slideControl2.Name.setText(Fields.getPname());
        slideControl2.Brand.setText(Fields.getBrand());
        shelfLife=DataBase.shelfParser(Fields.getShelfLife());
        slideControl2.ShilfDay.getValueFactory().setValue(shelfLife[0]);
        slideControl2.ShilfMonth.getValueFactory().setValue(shelfLife[1]);
        slideControl2.ShilfYear.getValueFactory().setValue(shelfLife[2]);

        slideControl2= loader[1].getController();
        slideControl2.Manufacturer.setValue(Fields.getManufacturer());
        slideControl2.Price.getValueFactory().setValue(Fields.getPrice());



    }

    boolean UPDATE_OPERATION=false;
    public void setUpdateOperation(boolean update){
        UPDATE_OPERATION =update;
    }
}
