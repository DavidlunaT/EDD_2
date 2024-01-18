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
        ArrayList<Person> peopleRegister = Ranking.loadPeople();
        //creacion de jugadores 
        //id = 1 es X
        //id = 2 es O
        if (App.isX) {
            Person playerX = new Person(1);
            if (!SelectingController.playerName.equals("guest")) {
                playerX.setNickname(SelectingController.playerName);
                //System.out.println(playerX.getNickname());
                if (peopleRegister.contains(playerX)) {
                    playerX.setContWins(peopleRegister.get(peopleRegister.indexOf(playerX)).getContWins());
                    playerX.setContDefeats(peopleRegister.get(peopleRegister.indexOf(playerX)).getContDefeats());
                }
            }
            this.player = playerX;

            Computer playerCircle = new Computer(2, board);
            this.computer = playerCircle;
        } else {
            Person playerCircle = new Person(2);
            if (!SelectingController.playerName.equals("guest")) {
                playerCircle.setNickname(SelectingController.playerName);
                if (peopleRegister.contains(playerCircle)) {
                    playerCircle.setContWins(peopleRegister.get(peopleRegister.indexOf(playerCircle)).getContWins());
                    playerCircle.setContDefeats(peopleRegister.get(peopleRegister.indexOf(playerCircle)).getContDefeats());
                }
            }
            this.player = playerCircle;

            Computer playerX = new Computer(1, board);

            this.computer = playerX;
        }

        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

        buttons.forEach(button -> {
            setupButton(button);
            button.setFocusTraversable(false);
        });
        System.out.println("initializer");
        firstMove();
        winnerText.setText("Your Turn");
        this.changeFillOfButtons();
    }

    @FXML
    void restartGame(ActionEvent event) {
        buttons.forEach(this::resetButton);
        winnerText.setText("Tic-Tac-Toe");
    }

    @FXML
    public void switchToLauncherController(ActionEvent e) throws IOException {
        recoverPerson();
        try {
            App.setRoot("launcher");
        } catch (IOException ex) {
        }
    }

    public void recoverPerson() {
        if (!player.getNickname().equals("")) {
            ArrayList<Person> peopleRegister = Ranking.loadPeople();
            Person nPerson = new Person(player.getNickname(), player.getContWins(), player.getContDefeats());
            if (peopleRegister.contains(nPerson)) {
                peopleRegister.remove(nPerson);
                peopleRegister.add(nPerson);
            } else {
                peopleRegister.add(nPerson);
            }
            Ranking.escribirEnArchivo(peopleRegister);
        }

    }

    //
    public void resetButton(Button button) {
        button.setDisable(false);
        board.clear();
        button.setText("");
        gameOver = false;
        firstMove();
    }

    private void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            //Setea movimiento de player
            if (App.isX) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            board.setMove(buttons.indexOf(button), player.getId());

            button.setDisable(true);
//            try {
//                //luego setea movimiento de Pc
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
            checkIfGameIsOver();
            if (!gameOver) {
                winnerText.setText("...");
                computerMove();
            }
            winnerText.setText("Your Turn");
            checkIfGameIsOver();
            if (gameOver) {
                if (!player.getNickname().equals("guest")) {
                    if (App.isX) {
                        System.out.println("contador de victorias previo a acabar el juego:" + player.getContWins());
                        player.setContWins(player.getContWins() + 1);
                    } else {
                        player.setContDefeats(player.getContDefeats() + 1);
                    }
                }
            }
            
            //visualizar el board
            for (int i = 0; i < 9; i++) {
                System.out.print(board.getBoard()[i]);
                if (i == 2 || i == 5 || i == 8) {
                    System.out.println("");
                }
            }
            System.out.println("");

        });
    }

    public void firstMove() {

        if (!App.playerTurn) {
            //computer turn
            winnerText.setText("...");
            computerMove();
            winnerText.setText("Your Turn");

        }
    }

    //Computadora ejecuta su movimiento
    public void computerMove() {
        if (!gameOver) {
            //movimiento de Pc     
            int posicionOptima = 0;
            posicionOptima = computer.calculateBestMove(board, computer.getId(), player.getId());
                                 
            board.setMove(posicionOptima, computer.getId());
            Button SelectedButton = buttons.get(posicionOptima);
            if (App.isX) {
                SelectedButton.setText("O");
                SelectedButton.setDisable(true);

            } else {
                SelectedButton.setText("X");
                SelectedButton.setDisable(true);
            }
        }
        checkIfGameIsOver();

    }

    //checkea si es game se acabo
    public void checkIfGameIsOver() {
        int winner = board.whoIsWinner(board.x, board.o);
        if (winner == board.x) {
            winnerText.setText("X WON!");
            disableButtons();

            gameOver = true;
        } else if (winner == board.o) {
            winnerText.setText("O WON!");
            disableButtons();
            gameOver = true;
        } else if (winner == -1) {
            winnerText.setText("TIE");
            disableButtons();
            gameOver = true;
        }

    }

    //desabilita todo los botones
    private void disableButtons() {
        buttons.forEach(button -> {
            button.setFocusTraversable(false);
            button.setDisable(true);
        });

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

}
