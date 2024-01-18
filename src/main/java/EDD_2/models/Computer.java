package EDD_2.models;

import EDD_2.dataStructures.Tree;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author David
 */
public class Computer extends Player implements Serializable{
    private static final long serialVersionUID = 10L;
    private int id;
    private MiniMax minimax;
    private Comparator<Board> cmpBoards;
    
    public Computer(int id, Board b){
        this.id = id;
        this.minimax = new MiniMax(b,this);
    }
    
    @Override
    public int getId(){
        return id;
    }
 
    public int calculateBestMove(Board b, int idTurn, int idOponent){
        minimax.setCurrentBoard(b);
        minimax.initializeUtility(idTurn,idOponent);
        return minimax.searchMaxMove();
    }
    
    public int bloquearJugadaGanadora(int[] tablero, int simboloOponente) {
        // Verificar filas
        for (int i = 0; i < 9; i += 3) {
            if (tablero[i] == simboloOponente && tablero[i + 1] == simboloOponente && tablero[i + 2] == 0) {
                return i + 2;
            }
            if (tablero[i] == simboloOponente && tablero[i + 2] == simboloOponente && tablero[i + 1] == 0) {
                return i + 1;
            }
            if (tablero[i + 1] == simboloOponente && tablero[i + 2] == simboloOponente && tablero[i] == 0) {
                return i;
            }
        }

        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i] == simboloOponente && tablero[i + 3] == simboloOponente && tablero[i + 6] == 0) {
                return i + 6;
            }
            if (tablero[i] == simboloOponente && tablero[i + 6] == simboloOponente && tablero[i + 3] == 0) {
                return i + 3;
            }
            if (tablero[i + 3] == simboloOponente && tablero[i + 6] == simboloOponente && tablero[i] == 0) {
                return i;
            }
        }

        // Verificar diagonales
        if (tablero[0] == simboloOponente && tablero[4] == simboloOponente && tablero[8] == 0) {
            return 8;
        }
        if (tablero[0] == simboloOponente && tablero[8] == simboloOponente && tablero[4] == 0) {
            return 4;
        }
        if (tablero[4] == simboloOponente && tablero[8] == simboloOponente && tablero[0] == 0) {
            return 0;
        }

        if (tablero[2] == simboloOponente && tablero[4] == simboloOponente && tablero[6] == 0) {
            return 6;
        }
        if (tablero[2] == simboloOponente && tablero[6] == simboloOponente && tablero[4] == 0) {
            return 4;
        }
        if (tablero[4] == simboloOponente && tablero[6] == simboloOponente && tablero[2] == 0) {
            return 2;
        }

        return -1; // No es necesario bloquear
    }
    
    public int obtenerJugadaGanadora(int[] tablero, int miSimbolo) {
        // Verificar filas
        for (int i = 0; i < 9; i += 3) {
            if (tablero[i] == miSimbolo && tablero[i + 1] == miSimbolo && tablero[i + 2] == ' ') {
                return i + 2;
            }
            if (tablero[i] == miSimbolo && tablero[i + 2] == miSimbolo && tablero[i + 1] == ' ') {
                return i + 1;
            }
            if (tablero[i + 1] == miSimbolo && tablero[i + 2] == miSimbolo && tablero[i] == ' ') {
                return i;
            }
        }

        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i] == miSimbolo && tablero[i + 3] == miSimbolo && tablero[i + 6] == ' ') {
                return i + 6;
            }
            if (tablero[i] == miSimbolo && tablero[i + 6] == miSimbolo && tablero[i + 3] == ' ') {
                return i + 3;
            }
            if (tablero[i + 3] == miSimbolo && tablero[i + 6] == miSimbolo && tablero[i] == ' ') {
                return i;
            }
        }

        // Verificar diagonales
        if (tablero[0] == miSimbolo && tablero[4] == miSimbolo && tablero[8] == ' ') {
            return 8;
        }
        if (tablero[0] == miSimbolo && tablero[8] == miSimbolo && tablero[4] == ' ') {
            return 4;
        }
        if (tablero[4] == miSimbolo && tablero[8] == miSimbolo && tablero[0] == ' ') {
            return 0;
        }

        if (tablero[2] == miSimbolo && tablero[4] == miSimbolo && tablero[6] == ' ') {
            return 6;
        }
        if (tablero[2] == miSimbolo && tablero[6] == miSimbolo && tablero[4] == ' ') {
            return 4;
        }
        if (tablero[4] == miSimbolo && tablero[6] == miSimbolo && tablero[2] == ' ') {
            return 2;
        }

        return -1; // No estás a punto de ganar.
    }
}
