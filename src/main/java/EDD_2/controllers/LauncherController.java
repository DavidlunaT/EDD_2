package EDD_2.controllers;

import EDD_2.App;
import EDD_2.models.Person;
import EDD_2.models.Ranking;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author oweny
 */
public class LauncherController implements Initializable {

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        effectsButtons(btn1);
        effectsButtons(btn2);
        effectsButtons(btn3);
        
        ArrayList<Person> peopleRegister = Ranking.loadPeople();
        System.out.println("ranking");
        for(Person p: peopleRegister){
            
            System.out.println(p.getNickname() + " " + p.getContWins() + " " + p.getContDefeats());
        }
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
    
    public void effectsButtons(Button btn){
        String style = btn.getStyle();
        final String hovered = "-fx-background-color: transparent; -fx-border-color: #01dbd9; -fx-text-fill: #01dbd9;";
        btn.setOnMouseEntered(e -> btn.setStyle(hovered));
        btn.setOnMouseExited(e -> btn.setStyle(style));
    }
}
