package com.example.thestore;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenControl implements Initializable {
    @FXML
    private ProgressBar progressBar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        progressBar.setProgress(0.0);

        // Create a KeyValue to gradually increase the progress from 0.0 to 1.0 over 3 seconds
        KeyValue keyValue = new KeyValue(progressBar.progressProperty(), 1);

        // Use a KeyFrame to define the duration and value change
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), keyValue);

        // Create a Timeline with the KeyFrame
        Timeline timeline = new Timeline(keyFrame);

        // Play the Timeline
        timeline.play();
    }


    public void updateProgressBar(double progress) {
        progressBar.setProgress(progress);
    }

}