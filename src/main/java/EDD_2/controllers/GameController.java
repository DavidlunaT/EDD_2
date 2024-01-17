package EDD_2.controllers;

import EDD_2.App;
import EDD_2.models.Board;
import EDD_2.models.Person;
import EDD_2.models.Player;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    @FXML
    private Button restart;
    @FXML
    private Button home;


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
            changeTextColor(button);
        });
        
       this.changeFillOfButtons();
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
            int pos = buttons.indexOf(button);
            System.out.println(pos);

            button.setDisable(true);
            checkIfGameIsOver();
        });
    }
    
    public void setPlayerSymbol(Button button){
        if(playerTurn % 2 == 0){            
            board.setMove(buttons.indexOf(button),playerX.getId());    
            button.setText("X"); //CAMBIAR POR BUTTON.SETGRAPHIC
            playerTurn = 1;
        } else{         
            board.setMove(buttons.indexOf(button),playerCircle.getId());   
            button.setText("O"); //CAMBIAR POR BUTTON.SETGRAPHIC
            playerTurn = 0;
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
    
    public void changeFillOfButtons(){
        changeTextColor(button1); 
        changeTextColor(button2); 
        changeTextColor(button3); 
        changeTextColor(button4); 
        changeTextColor(button5); 
    }
    
    public void changeTextColor(Button btn){
        btn.setStyle("-fx-text-fill: #eee000; -fx-font-family: 'Courier New'; -fx-font-size: 45; -fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: #eee000;");      
    }

    @FXML
    private void backHome(ActionEvent event) {
        try {App.setRoot("launcher");} catch (IOException ex) {}
    }
}
