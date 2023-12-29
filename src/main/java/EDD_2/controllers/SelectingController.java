/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EDD_2.controllers;

import EDD_2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author David
 */
public class SelectingController implements Initializable {

    public RadioButton iniciarRadioBtn;
    public RadioButton oRadioBtn;
    public ToggleGroup icon;
    public RadioButton xRadioBtn;
    public Button confirm;
    public Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        xRadioBtn.setSelected(true);
        iniciarRadioBtn.setSelected(true);
    }

    public void confirmSelected(ActionEvent actionEvent) {
        if(xRadioBtn.isSelected()){
            if(iniciarRadioBtn.isSelected()){
                App.isX = true;
                App.playerTurn = true;
                try {App.setRoot("gamePvC");} catch (IOException ex) {}                
            }
            App.isX = true;
            App.playerTurn = false;
            try {App.setRoot("gamePvC");} catch (IOException ex) {}
        }
        if(iniciarRadioBtn.isSelected()){
                App.isX = false;
                App.playerTurn = true;
                try {App.setRoot("gamePvC");} catch (IOException ex) {}
        }
        
        App.isX = false;
        App.playerTurn = false;
        try {App.setRoot("gamePvC");} catch (IOException ex) {}
        
    }

    public void cancelRadioBtn(ActionEvent actionEvent) {
        
    }
}
