package com.mario;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneController {
    final int BOARD_WIDTH = 1268,BOARD_HEIGHT=708;   
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Background bg;
    public void switchToScene1(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene2(ActionEvent event) throws IOException{
        // get a handle to the stage
        // Stage stage = (Stage) closeButton.getScene().getWindow();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        bg = new Background(BOARD_WIDTH, BOARD_HEIGHT);
        stage.close();

        

    }
    @FXML private javafx.scene.control.Button closeButton;

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();

    }
}
