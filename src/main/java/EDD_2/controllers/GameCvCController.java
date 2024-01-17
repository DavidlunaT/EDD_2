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
import EDD_2.models.Ranking;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author David
 */
public class GameCvCController implements Initializable {
    
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

    private Computer computer1;

    private Computer computer2;

    private boolean gameOver;

    /**
     * Initializes the controller class.
     */
    //nueva partida
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        App.isX = true;
        //creacion de board
        this.board = new Board();
       
            Computer playerCircle = new Computer(2, board);
            this.computer2 = playerCircle;

            Computer playerX = new Computer(1, board);

            this.computer1 = playerX;
        

        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            button.setFocusTraversable(true);
        });
        this.changeFillOfButtons();
        winnerText.setOnMouseClicked(event -> {
            // Llamar al método que deseas ejecutar
            start();
        });


    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        
    }

    @FXML
    public void switchToLauncherController(ActionEvent e) throws IOException {
        try {
            App.setRoot("launcher");
        } catch (IOException ex) {
        }
    }

    //
    public void resetButton(Button button) {

        board.clear();

        gameOver = false;
          while(!gameOver){
            ComputersMovesManager();
        }
        
    }

    public void ComputersMovesManager() {

        if (App.isX ==  true) {
            //computer1 turn
             Platform.runLater(() -> {
            winnerText.setText("X Turn...");
            });
            //Sleep
            sleepThread(1000);
            computerMove(computer1, computer2);
            
            
            App.isX = false;
            
        }
        else{
            System.out.println("O turn");
             //computer2 turn
            winnerText.setText("O Turn...");
            //Sleep
            sleepThread(1000);
            computerMove(computer2, computer1);
            App.isX = true;
            
        }
    }
    private void sleepThread(int milliseconds) {
        
            try {
                // Pausar el hilo durante el tiempo especificado
                Thread.sleep(milliseconds);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    

    //Computadora ejecuta su movimiento
    public void computerMove(Computer computer, Computer computerOp) {
        if (!gameOver) {
            //movimiento de Pc     
            int posicionOptima = 0;
            
            posicionOptima = computer.calculateBestMove(board, computer.getId(), computerOp.getId());
                                 
            board.setMove(posicionOptima, computer.getId());
            Button SelectedButton = buttons.get(posicionOptima);
            if (App.isX) {
                SelectedButton.setText("O");
               

            } else {
                SelectedButton.setText("X");

            }
        }
        checkIfGameIsOver();

    }

    //checkea si es game se acabo
    public void checkIfGameIsOver() {
        int winner = board.whoIsWinner(board.x, board.o);
        if (winner == board.x) {
            winnerText.setText("X WON!");
            

            gameOver = true;
        } else if (winner == board.o) {
            winnerText.setText("O WON!");
            
            gameOver = true;
        } else if (winner == -1) {
            winnerText.setText("TIE");
            
            gameOver = true;
        }

    }



    public void changeFillOfButtons() {
        changeTextColor(button1);
        changeTextColor(button2);
        changeTextColor(button3);
        changeTextColor(button4);
        changeTextColor(button5);
        changeTextColor(button6);
        changeTextColor(button7);
        changeTextColor(button8);
        changeTextColor(button9);
    }

    public void changeTextColor(Button btn) {
        btn.setStyle("-fx-text-fill: #eee000; "
                + "-fx-font-family: 'Courier New'; "
                + "-fx-font-size: 45; -fx-font-weight: bold; "
                + "-fx-background-color: transparent; "
                + "-fx-border-color: #eee000;");
    }

    public void start() {
        while (!gameOver) {
            ComputersMovesManager();
        }
    }
}
