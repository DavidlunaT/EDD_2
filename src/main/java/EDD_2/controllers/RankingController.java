/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EDD_2.controllers;

import EDD_2.App;
import EDD_2.models.Person;
import EDD_2.models.Ranking;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author oweny
 */
public class RankingController implements Initializable {

    public ObservableList<Person> ranking;
    
    @FXML
    private TableView<Person> tblRanking;
    @FXML
    private TableColumn colNickname;
    @FXML
    private TableColumn colWins;
    @FXML
    private TableColumn colDefeats;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ranking = FXCollections.observableArrayList();
        this.colNickname.setCellValueFactory(new PropertyValueFactory("nickname"));
        this.colWins.setCellValueFactory(new PropertyValueFactory("contWins"));
        this.colDefeats.setCellValueFactory(new PropertyValueFactory("contDefeats"));
        
        ranking.addAll(Ranking.loadPeople());
        this.tblRanking.setItems(ranking);
    }

    @FXML
    public void switchToLauncherController(ActionEvent e) throws IOException{
        try {
            App.setRoot("launcher");
        } catch (IOException ex) {
        }
    }
    
}
