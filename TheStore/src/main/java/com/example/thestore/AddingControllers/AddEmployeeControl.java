package com.example.thestore.AddingControllers;

import com.example.thestore.Methods.DataBase;
import com.example.thestore.Methods.Efficts;
import com.example.thestore.Methods.Transitions;
import com.example.thestore.Methods.UpdateFromDatabase;
import com.example.thestore.SlidesControllers.EmployeeSlidesControl;
import com.example.thestore.TablesFields.EmployeeFields;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddEmployeeControl implements Initializable {


    public String WarehousNameManagered;
    int order;
    Parent Slide[]=new Parent[3];
    FXMLLoader loader []=new FXMLLoader[3];
    @FXML
    AnchorPane interiar;
    @FXML
    Button PreviousButton;
    @FXML
    Button NextButton;
    EmployeeSlidesControl slideControl;
    Object source; //this will indicate what is the controller of main stage
    String sourceString;
    EmployeeFields newEmp;

    @FXML
    Label lbl;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            newEmp = new EmployeeFields();
            order=0;
            PreviousButton.setDisable(true);

            loader[0]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddEmployee/First.fxml"));
            loader[1]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddEmployee/Second.fxml"));
            loader[2]=new FXMLLoader(getClass().getResource("/com/example/thestore/AddingPopUps/AddEmployee/Third.fxml"));
            for(int i=0;i<3;i++) Slide[i]=loader[i].load();


            if(interiar!=null){
                interiar.getChildren().removeAll();
                interiar.getChildren().setAll(Slide[0]);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
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



        if(order==1){
            slideControl= loader[0].getController();

            newEmp.setFirstName(slideControl.firstName.getText());
            newEmp.setLastName(slideControl.lastName.getText());
            newEmp.setCity(slideControl.city.getText());
            newEmp.setStreet(slideControl.street.getText());
            newEmp.setBankInfo(slideControl.BankInfo.getText());
        }

        if(order==2){
            slideControl=loader[1].getController();

            NextButton.setText("Save");
            NextButton.setStyle("-fx-background-color: #3dbd29;");


            EmployeeSlidesControl slideControl2= loader[2].getController();
            if (sourceString.equals("ManagerScreen")) {
                // mainStage was created by ManagerScreen
                slideControl2.warehouse.setValue(WarehousNameManagered);
                slideControl2.warehouse.setDisable(true);
            }

            slideControl2.choiceRoll.getItems().addAll("Admin", "Manager", "Accountant","Worker");
            List<String> warehouseNames = DataBase.fetchWarehouseNamesFromDatabase();
            slideControl2.warehouse.setItems(FXCollections.observableArrayList(warehouseNames));

            newEmp.setEmail(slideControl.Email.getText());
            newEmp.setHireDate(slideControl.HireDate.getValue());
            newEmp.setSalary((Integer) slideControl.salary.getValue());
            newEmp.setGender(slideControl.Male.isSelected()?'M' : 'F');
            newEmp.setID(Integer.valueOf(slideControl.ID.getText()));
            newEmp.setPhone(slideControl.phone.getText());


        }

        if(order==3) {
            slideControl=loader[2].getController();



            newEmp.setBirthDate(slideControl.BirthDate.getValue());
            newEmp.setChoiceRoll((String) slideControl.choiceRoll.getValue());
            newEmp.setWarehouse((String) slideControl.warehouse.getValue());
            newEmp.setPassword(slideControl.password.getText());
            if(UPDATE_OPERATION){
                UpdateFromDatabase.updateEmployee(newEmp);
            }
            else
                DataBase.PinsertEmployee(newEmp);


            Stage stage =(Stage)(((Node)event.getSource()).getScene().getWindow());
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
        if(order==1){
            NextButton.setText("Next");
            NextButton.setStyle("-fx-background-color: #0d5287;");
        }
        if(order==0)PreviousButton.setDisable(true);
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

        source = mainStage.getProperties().get("source");
        if (source != null) {
            sourceString = source.toString();
            if (sourceString.equals("EmployeeControl")) {
                // mainStage was created by EmployeeControl
            } else if (sourceString.equals("ManagerScreen")) {
                // mainStage was created by ManagerScreen

            }
        }

    }



    EmployeeFields FieldsForUpdate;
    public void setEmployeeFieldsForUpdate(EmployeeFields Fields){
        lbl.setText("Update Employee");
        FieldsForUpdate=Fields;
        EmployeeSlidesControl slideControl2= loader[0].getController();
        slideControl2.city.setText(Fields.getCity());
        slideControl2.firstName.setText(Fields.getFirstName());
        slideControl2.lastName.setText(Fields.getLastName());
        slideControl2.street.setText(Fields.getStreet());
        slideControl2.BankInfo.setText(Fields.getBankInfo());

        slideControl2= loader[1].getController();


        slideControl2.ID.setText(String.valueOf(Fields.getID()));
        slideControl2.ID.setDisable(true);
        slideControl2.Email.setText(Fields.getEmail());
        slideControl2.HireDate.setValue(Fields.getHireDate());
        slideControl2.salary.getValueFactory().setValue(Fields.getSalary());
        slideControl2.phone.setText(Fields.getPhone());

        Character gender = Fields.getGender();

        if (gender=='M') {
            slideControl2.Male.setSelected(true);
            slideControl2.Female.setSelected(false);
        } else if (gender=='F') {
            slideControl2.Male.setSelected(false);
            slideControl2.Female.setSelected(true);
        } else {
            // Handle other cases or set a default state
            slideControl2.Male.setSelected(false);
            slideControl2.Female.setSelected(false);
            System.out.println(gender);
        }


        slideControl2= loader[2].getController();
        slideControl2.BirthDate.setValue(Fields.getBirthDate());
        slideControl2.choiceRoll.setValue(Fields.getChoiceRoll());
        slideControl2.warehouse.setValue(Fields.getWarehouse());
        slideControl2.password.setText(Fields.getPassword());
    }

    boolean UPDATE_OPERATION=false;
    public void setUpdateOperation(boolean update){
        UPDATE_OPERATION =update;
    }


}


