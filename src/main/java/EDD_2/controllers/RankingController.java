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
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author oweny
 */
public class RankingController implements Initializable {

    public ObservableList<Person> ranking;
    PriorityQueue<Person> rCola;
    
    @FXML
    private TableView<Person> tblRanking;
    @FXML
    private TableColumn colNickname;
    @FXML
    private TableColumn colWins;
    @FXML
    private TableColumn colDefeats;
    
    @FXML
    private Button home;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

        rCola = new PriorityQueue<>(new Comparator<Person>(){
            @Override
            public int compare(Person p1, Person p2){
                return p2.getContWins() - p1.getContWins();
                //return (p2.getContWins() / p2.getContDefeats()) - (p1.getContWins() / p1.getContDefeats());
            }
        });
        ranking = FXCollections.observableArrayList();
        this.colNickname.setCellValueFactory(new PropertyValueFactory("nickname"));
        this.colWins.setCellValueFactory(new PropertyValueFactory("contWins"));
        this.colDefeats.setCellValueFactory(new PropertyValueFactory("contDefeats"));
        
        for(Person p: Ranking.loadPeople()){
            rCola.offer(p);
        }
        while(!rCola.isEmpty()){
            ranking.add(rCola.poll());
        }
        
        //ranking.addAll(rCola);
        
        this.tblRanking.setItems(ranking);
        cambiarColorEncabezado(colNickname, Color.WHITE);
        cambiarColorEncabezado(colWins, Color.WHITE);
        cambiarColorEncabezado(colDefeats, Color.WHITE);

    }



    @FXML
    private void backHome(ActionEvent event) {
        setScene("launcher");
    }
    
    public void setScene(String sceneName){
        try {App.setRoot(sceneName);} catch (IOException ex) {}
    }
    
    private void cambiarColorEncabezado(TableColumn<Person, String> columna, Color color) {
        Label label = new Label(columna.getText());
        columna.setText("");
        label.setTextFill(color);  
        columna.setGraphic(new HBox(label));       
    }
}
