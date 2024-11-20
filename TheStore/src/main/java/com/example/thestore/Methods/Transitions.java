package com.example.thestore.Methods;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Transitions {


    public static TranslateTransition createSlideTransition(AnchorPane node,double time,double fromX, double toX) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(time), node);
        translateTransition.setFromX(fromX);
        translateTransition.setToX(toX);
        return translateTransition;
    }

    public static FadeTransition createFadeTransition(AnchorPane node, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), node);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }
}
