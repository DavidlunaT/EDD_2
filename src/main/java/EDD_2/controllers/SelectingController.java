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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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
    @FXML
    private ImageView gif;
    @FXML
    private TextField txtPlayerName;
    
    public static String playerName;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        gif.setImage(new Image("/EDD_2/images/3.gif"));
        effectsButtons(confirm);
        effectsButtons(cancel);
        changeImages();
    }

    @FXML
    public void confirmSelected(ActionEvent actionEvent) {
        if(xRadioBtn.isSelected()){
            
            if(iniciarRadioBtn.isSelected()){
                gif.setImage(new Image("/EDD_2/images/4.gif"));
                App.isX = true;
                App.playerTurn = true;
                try {App.setRoot("gamePvC");} catch (IOException ex) {}                
            }else{
                App.isX = true;
                App.playerTurn = false;
                try {App.setRoot("gamePvC");} catch (IOException ex) {}
            }
            
        }
        if(oRadioBtn.isSelected()){
            gif.setImage(new Image("/EDD_2/images/5.gif"));
            if(iniciarRadioBtn.isSelected()){
                App.isX = false;
                App.playerTurn = true;
                try {App.setRoot("gamePvC");} catch (IOException ex) {}
            }else{
                App.isX = false;
                App.playerTurn = false;
                try {App.setRoot("gamePvC");} catch (IOException ex) {}
            }    
            
        }
        if(!txtPlayerName.getText().equals("")){
            playerName = txtPlayerName.getText();
        }else{
            playerName = "guest";
        }
        
        
        
    }

    @FXML
    public void cancelRadioBtn(ActionEvent actionEvent) {
        
    }
    
    public void effectsButtons(Button btn){
        String style = btn.getStyle();
        final String hovered = "-fx-background-color: transparent; -fx-border-color: #01dbd9; -fx-text-fill: #01dbd9;";
        btn.setOnMouseEntered(e -> btn.setStyle(hovered));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }
    
    public void changeImages(){
        icon.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == xRadioBtn) {
                // Cambiar la imagen cuando se selecciona el radioButton1
                gif.setImage(new Image("/EDD_2/images/4.gif"));
            } else if (newValue == oRadioBtn) {
                // Cambiar la imagen cuando se selecciona el radioButton2
                gif.setImage(new Image("/EDD_2/images/5.gif"));
            }
        });
    }
}
