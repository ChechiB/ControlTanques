package com.javafx.girsyt.main;

import com.jfoenix.controls.JFXMasonryPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/views/panelGeneralUI.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main (String[] args){
        //Metodo de application
        launch(args);
    }
}
