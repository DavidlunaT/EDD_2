package EDD_2.controllers;

import EDD_2.models.Board;
import EDD_2.models.Person;
import EDD_2.models.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameController implements Initializable {

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
    
    private Player playerX;
    
    private Player playerCircle;

    //nueva partida
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //creacion de board
        this.board = new Board();
        
        //creacion de jugadores
        
        Player playerX = new Person(1);
        this.playerX = playerX;
        
        Player playerCircle = new Person(2);
        this.playerCircle =  playerCircle;
        
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
            setPlayerSymbol(button);
            
            button.setDisable(true);
            checkIfGameIsOver();
        });
    }
    
    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){
            
            board.setMove(buttons.indexOf(button),playerX.getId());    

            button.setText("X"); //CAMBIAR POR BUTTON.SETGRAPHIC
            
            //for each para imprimir elemntos del array
            for(int a: board.arrays()){
                System.out.print(a);
            }
            System.out.println("");
            playerTurn = 1;
        } else{
            
            board.setMove(buttons.indexOf(button),playerCircle.getId());   
            button.setText("O"); //CAMBIAR POR BUTTON.SETGRAPHIC
            for(int a: board.arrays()){
                System.out.print(a);
            }
            System.out.println("");
            playerTurn = 0;
        }
    }
   

    public void checkIfGameIsOver(){
        if(board.isWinner(1)){
            winnerText.setText("X WON!");
            buttons.forEach(button ->{
            button.setFocusTraversable(false);    
            button.setDisable(true);
            
        });
            
        }
        if(board.isWinner(2)){
            winnerText.setText("O WON!");
            buttons.forEach(button ->{
            button.setFocusTraversable(false);
            button.setDisable(true);
            
        });
            
        }
    }
}
