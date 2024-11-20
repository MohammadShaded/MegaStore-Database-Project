package com.example.thestore.Methods;

import javafx.scene.effect.BoxBlur;
import javafx.stage.Stage;

public class Efficts {
    public static void makeSceneBlurry(Stage primaryStage) {
        // Create a BoxBlur effect with the desired blur radius
        BoxBlur boxBlur = new BoxBlur(5, 5, 3);
        primaryStage.setOpacity(0.9);


        // Apply the BoxBlur effect to the scene
        primaryStage.getScene().getRoot().setEffect(boxBlur);
    }
    public static void removeSceneBlurry(Stage primaryStage) {
        // Remove the BoxBlur effect to make the scene clear
        primaryStage.getScene().getRoot().setEffect(null);
        primaryStage.setOpacity(1);

    }


}
