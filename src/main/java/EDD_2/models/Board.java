/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int[] board ;
    private List<int[]> winnerPos = Arrays.asList(
                new int[]{0, 1, 2},
                new int[]{0, 3, 6},
                new int[]{0, 4, 8},
                new int[]{1, 4, 7},
                new int[]{2, 4, 6},
                new int[]{2, 5, 8},
                new int[]{3, 4, 5},
                new int[]{6, 7, 8}
        );
    
    //nueva partida
    public Board(){
        board = new int[9]; 
    }
    
    
    //cargar partida previa
    public Board(int[] boardLoaded){
        board = boardLoaded;   
    }
    public Player checkWinner (Player player){
        if(isWinner(player)){
            return player;
        }
        return null;
    }
    public boolean isWinner(Player player){
        ArrayList<Integer> list = getMovesPlayer(player);
        for(int i = 0; i < 8; i++){
            int n1 = winnerPos.get(i)[0];
            int n2 = winnerPos.get(i)[1];
            int n3 = winnerPos.get(i)[2];
            if(list.contains(n1) && list.contains(n2) && list.contains(n3)){
                return true;
            }
        }
        return false;       
        
    }
    private ArrayList<Integer> getMovesPlayer(Player player){
        ArrayList<Integer> list = new ArrayList<>();
        
        int idPlayer = player.getId();
        
        for(int i = 0; i<9 ; i++){
            if(idPlayer == board[i]){
                list.add(i);
            }
        }
        return list;
    }
    public void setMove(Player player, int pos){
        int idPlayer = player.getId();
        if(board[pos] != 0){
            board[pos] = idPlayer;
        }
    }

    public int[] getBoard() {
        return board;
    }
    
    
}
