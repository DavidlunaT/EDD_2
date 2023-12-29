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
import static java.lang.Thread.sleep;
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
        System.out.println("initializer");
        firstMove();
        winnerText.setText("Your Turn");
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("Tic-Tac-Toe");
    }
    
    //
    public void resetButton(Button button){
        button.setDisable(false);
        board.clear();
        button.setText("");
        firstMove();
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            //Setea movimiento de player
            if(App.isX){
                button.setText("X");
            }else{
                button.setText("O");
            }
            board.setMove(buttons.indexOf(button),player.getId());
            
            button.setDisable(true);
//            try {
//                //luego setea movimiento de Pc
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
            checkIfGameIsOver();
            winnerText.setText("...");
            computerMove();
            winnerText.setText("Your Turn");
            checkIfGameIsOver();
            //visualizar el board
            for(int i = 0; i < 9; i++){
                System.out.print(board.getBoard()[i]);
            }
            System.out.println("");
            
        });
    }
    public void firstMove(){
        
        if(!App.playerTurn){
             //computer turn
             winnerText.setText("...");
             computerMove();
             winnerText.setText("Your Turn");
             
         }
    }
    
    //Computadora ejecuta su movimiento
    public void computerMove(){
      
        //movimiento de Pc
        int posicionOptima = computer.calculateBestMove(board,computer.getId(),player.getId());
        board.setMove( posicionOptima , computer.getId());
        Button SelectedButton = buttons.get(posicionOptima);
        if(App.isX){
            SelectedButton.setText("O"); 
            SelectedButton.setDisable(true);

        }else{
            SelectedButton.setText("X"); 
            SelectedButton.setDisable(true);
            } 
        
          
    }
    //checkea si es game se acabo
    public void checkIfGameIsOver(){       
          int winner = board.whoIsWinner(board.x, board.o);         
          if(winner == board.x)
                {winnerText.setText("X WON!");
                disableButtons();
                gameOver = true;}
          else if(winner == board.o)
                {winnerText.setText("O WON!");
                disableButtons();
                gameOver = true;}
          else if(winner == -1)
                {winnerText.setText("TIE");
                disableButtons();
                gameOver = true;}
    }

    //desabilita todo los botones
    private void disableButtons() {
        buttons.forEach(button ->{
            button.setFocusTraversable(false);    
            button.setDisable(true);
        });

    }
}


