package com.example.thestore;
import com.example.thestore.Methods.DataBase;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import com.sun.javafx.application.LauncherImpl;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    private static Stage stage;
    private static Stage stage2;


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage2 = primaryStage;

        // Load and show the splash screen
        Parent splashRoot = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        Scene splashScene = new Scene(splashRoot);
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("MegaStore");
        Image icon =new Image("ICON.png");
        primaryStage.getIcons().add(icon);
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        // Simulate some initialization work
        simulateInitialization(() -> {
            // Load the main scene once the initialization is completed
            loadMainScene();
        });

    }

    private void loadMainScene() {
        try {
            stage=new Stage();
            stage2  .close();
            Parent mainRoot = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Scene mainScene = new Scene(mainRoot);
            stage.setScene(mainScene);
            stage.centerOnScreen();
            stage.setTitle("MegaStore");
            Image icon =new Image("ICON.png");
            stage.getIcons().add(icon);
            mainScene.getStylesheets().add(getClass().getResource("Styles/Styling.css").toExternalForm());

            // Set up any additional configurations for the main scene

            stage.setOnCloseRequest(event -> {
                event.consume();
                exit(stage);
            });

            // Now, show the main scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       //launch();
       //LauncherImpl.launchApplication(HelloApplication.class, LuncherSplash.class, args);
        Application.launch(HelloApplication.class, args);

    }

    //this method for doing the same thing as exit button, but for close X in the above of screen
    public void exit(Stage stage){

        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("YOU ARE ABOUT TO LOGOUT!");
        alert.setContentText("do you want to save before exiting?");

        if(alert.showAndWait().get()== ButtonType.OK){
            stage.close();}
    }

    private void simulateInitialization(Runnable onInitializationComplete) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                // Simulate work for 2 seconds
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // Handle interruption if needed
                }
                return null;
            }
        };

        // Set up a callback for when the task is successfully completed
        task.setOnSucceeded(event -> {
            // Continue with the main scene setup or other actions
            // This will be executed on the JavaFX Application Thread
            System.out.println("Initialization completed!");
            onInitializationComplete.run();
        });

        // Set up a callback for when the task fails
        task.setOnFailed(event -> {
            // Handle task failure if needed
            // This will be executed on the JavaFX Application Thread
            System.err.println("Initialization failed: " + task.getException().getMessage());
        });

        // Run the task in a background thread
        new Thread(task).start();
    }

    // ... (rest of the code remains the same)
}



