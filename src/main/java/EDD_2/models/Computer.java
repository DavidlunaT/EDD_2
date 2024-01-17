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
    
//    public int bloquearJugadaGanadora(int[] tablero, int simboloOponente) {
//        // Verificar filas
//        for (int i = 0; i < 9; i += 3) {
//            if (tablero[i] == simboloOponente && tablero[i + 1] == simboloOponente && tablero[i + 2] == 0) {
//                return i + 2;
//            }
//            if (tablero[i] == simboloOponente && tablero[i + 2] == simboloOponente && tablero[i + 1] == 0) {
//                return i + 1;
//            }
//            if (tablero[i + 1] == simboloOponente && tablero[i + 2] == simboloOponente && tablero[i] == 0) {
//                return i;
//            }
//        }
//
//        // Verificar columnas
//        for (int i = 0; i < 3; i++) {
//            if (tablero[i] == simboloOponente && tablero[i + 3] == simboloOponente && tablero[i + 6] == 0) {
//                return i + 6;
//            }
//            if (tablero[i] == simboloOponente && tablero[i + 6] == simboloOponente && tablero[i + 3] == 0) {
//                return i + 3;
//            }
//            if (tablero[i + 3] == simboloOponente && tablero[i + 6] == simboloOponente && tablero[i] == 0) {
//                return i;
//            }
//        }
//
//        // Verificar diagonales
//        if (tablero[0] == simboloOponente && tablero[4] == simboloOponente && tablero[8] == 0) {
//            return 8;
//        }
//        if (tablero[0] == simboloOponente && tablero[8] == simboloOponente && tablero[4] == 0) {
//            return 4;
//        }
//        if (tablero[4] == simboloOponente && tablero[8] == simboloOponente && tablero[0] == 0) {
//            return 0;
//        }
//
//        if (tablero[2] == simboloOponente && tablero[4] == simboloOponente && tablero[6] == 0) {
//            return 6;
//        }
//        if (tablero[2] == simboloOponente && tablero[6] == simboloOponente && tablero[4] == 0) {
//            return 4;
//        }
//        if (tablero[4] == simboloOponente && tablero[6] == simboloOponente && tablero[2] == 0) {
//            return 2;
//        }
//
//        return -1; // No es necesario bloquear
//    }

}
