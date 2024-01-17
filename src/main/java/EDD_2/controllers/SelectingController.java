/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EDD_2.controllers;

import EDD_2.App;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


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
    @FXML
    private RadioButton YesBtn;
    @FXML
    private RadioButton NoBtn;
    @FXML
    private Label startLabel;
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
    
    private static void warningMiniWindow(String warning) {
        Stage primaryStage = new Stage();
        Label label = new Label(warning);
        StackPane root = new StackPane();
        setStyleLabel("18", label);        
        root.setStyle("-fx-background-color: black;");
        root.getChildren().add(label);
        Scene scene = new Scene(root, 300, 100);
        primaryStage.setTitle("Mensaje");
        primaryStage.setScene(scene);
        createThread(primaryStage);
        primaryStage.show();
    }
    
    public static void setStyleLabel(String size, Label label){
        label.setStyle("-fx-font-family: 'Courier New';" +
                "-fx-font-weight: bold;" +
                "-fx-font-size:"+size+";" +
                "-fx-text-fill: #eee000;");
    }
    
    public static void createThread(Stage primaryStage){
        Thread thread = new Thread(() -> 
           {try 
                {Thread.sleep(5000);} 
            catch (InterruptedException e) 
               {e.printStackTrace();}
            Platform.runLater(() -> 
                {primaryStage.close();});
           });
        thread.start();
    }

        
    @FXML
    public void confirmSelected(ActionEvent actionEvent) {
        boolean XorO = !xRadioBtn.isSelected() && !oRadioBtn.isSelected();
        boolean YorN = !YesBtn.isSelected() && !NoBtn.isSelected();
        boolean missingOption = XorO || YorN;    
        
        if(missingOption)
           {warningMiniWindow("Choose an option");}  
        
        if(xRadioBtn.isSelected())
               {setForX();}   
        if(oRadioBtn.isSelected())
               {setForO();}        
        setFirstPlayer();
        System.out.println(playerName);
        setPlayerName();
        System.out.println(playerName);
        setScene("gamePvC");
    }
    
    public void setPlayerName(){
        if(txtPlayerName.getText() != null)
           {playerName = txtPlayerName.getText();}
        else
            {playerName = "guest"; }
    }
    
    public void setForO(){
        gif.setImage(new Image("/EDD_2/images/5.gif"));
        App.isX = false;
    }
    
    public void setForX(){
        gif.setImage(new Image("/EDD_2/images/4.gif"));
        App.isX = true;
    }
    public void setFirstPlayer(){
        if(YesBtn.isSelected())
            {App.playerTurn = true;}   
        else if(NoBtn.isSelected())
           {App.playerTurn = false;}
    }
    
    
    @FXML
    public void cancelRadioBtn(ActionEvent actionEvent) {
        setScene("launcher");
    }
    
    public void setScene(String sceneName){
        try {App.setRoot(sceneName);} catch (IOException ex) {}
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

    @FXML
    private void setLabelX(ActionEvent event) {
        startLabel.setText("X starts?");
    }

    @FXML
    private void setLabelO(ActionEvent event) {
        startLabel.setText("O starts?");
    }
}
