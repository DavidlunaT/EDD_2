/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package EDD_2.controllers;

import EDD_2.App;
import EDD_2.models.Board;
import EDD_2.models.Computer;
import EDD_2.models.Person;
import EDD_2.models.Player;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author David
 */
public class GamePvCController implements Initializable {
     @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Text winnerText;

    private int playerTurn = 0;

    ArrayList<Button> buttons;
    

    private Board board;
    
    private Person player;
    
    private Computer computer;
    
    private boolean gameOver;
    /**
     * Initializes the controller class.
     */
    
    //nueva partida
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //creacion de board
        this.board = new Board();
        
        //creacion de jugadores 
        //id = 1 es X
        //id = 2 es O
        if(App.isX){
            Person playerX = new Person(1);
            this.player = playerX;
            
            Computer playerCircle = new Computer(2,board);
            this.computer =  playerCircle;
        }
        else{
            Person playerCircle = new Person(2);
            this.player = playerCircle;
            
            Computer playerX = new Computer(1,board);
            this.computer =  playerX;
        }
        
        
        buttons = new ArrayList<>(Arrays.asList(button1,button2,button3,button4,button5,button6,button7,button8,button9));
        
        buttons.forEach(button ->{
            setupButton(button);
            button.setFocusTraversable(false);
        });
        
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("Tic-Tac-Toe");
    }

    public void resetButton(Button button){
        button.setDisable(false);
        board.clear();
        button.setText("");
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            //setPlayerSymbol(button);

            int pos = buttons.indexOf(button);
            System.out.println(pos);

            button.setDisable(true);
            checkIfGameIsOver();
        });
    }
    public void computerMovesManager(){
       while(!gameOver){
           if(!App.playerTurn){
               //movimiento de Pc
               int posicionOptima = computer.calculateBestMove(board);
               board.setMove( posicionOptima , computer.getId());
               Button SelectedButton = buttons.get(computer.calculateBestMove(board));
               if(!App.isX){
                   SelectedButton.setText("X"); 
                   SelectedButton.setDisable(true);
                   
               }else{
                   SelectedButton.setText("O"); 
                   SelectedButton.setDisable(true);
               } 
               checkIfGameIsOver();
           }
           
           
       }
    }

    public void checkIfGameIsOver(){       
          int winner = board.whoIsWinner(board.x, board.o);         
          if(winner == board.x)
             {winnerText.setText("X WON!");
              disableButtons();}
          else if(winner == board.o)
             {winnerText.setText("O WON!");
              disableButtons();}
          else if(winner == -1)
             {winnerText.setText("TIE");
              disableButtons();}
    }

    private void disableButtons() {
        buttons.forEach(button ->{
            button.setFocusTraversable(false);    
            button.setDisable(true);
        });

    }
}


