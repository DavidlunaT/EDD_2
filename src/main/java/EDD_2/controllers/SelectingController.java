/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EDD_2.controllers;

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
    }

    public void confirmSelected(ActionEvent actionEvent) {
    }

    public void cancelRadioBtn(ActionEvent actionEvent) {
    }
}
