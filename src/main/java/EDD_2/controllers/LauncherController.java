package EDD_2.controllers;

import EDD_2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author oweny
 */
public class LauncherController implements Initializable {

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void switchToGameController(ActionEvent e) throws IOException{
        try {
            App.setRoot("game");
        } catch (IOException ex) {
        }
    }
    
    @FXML
    public void switchToSelectingController(ActionEvent e) throws IOException{
        try {App.setRoot("selecting");} catch (IOException ex) {}
    }
    
}
