/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private int utility;
    private int[] board ;
    public int size = 9;
    public int x = 1;
    public int o = 2;
    
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
    
    public int whoIsWinner(int idX, int idO){        
        ArrayList<Integer> listX = getMovesPlayer(idX);
        ArrayList<Integer> list0 = getMovesPlayer(idO);
        
        for(int i = 0; i < 8; i++){
            int n1 = winnerPos.get(i)[0];
            int n2 = winnerPos.get(i)[1];
            int n3 = winnerPos.get(i)[2];           
            boolean isXwinner = listX.contains(n1) && listX.contains(n2) && listX.contains(n3);
            boolean isOwinner = list0.contains(n1) && list0.contains(n2) && list0.contains(n3);  
            boolean isTie = isFull() && (!isXwinner) && (!isOwinner);
            if(isXwinner){return idX;} 
            else if(isOwinner){return idO;}
            else if(isTie){return -1;}
        }
        return 0;
    }
    
    private ArrayList<Integer> getMovesPlayer(int id){
        ArrayList<Integer> list = new ArrayList<>();        
        int idPlayer = id;       
        for(int i = 0; i<size; i++){
            if(idPlayer == board[i]){
                list.add(i);
            }
        }
        return list;
    }
    
    public void setMove(int pos, int id){
        board[pos] = id;
    }
   
    public void clear(){
        for(int i= 0; i<size; i++ ){
            board[i] = 0;
        }
    }

    public int utility(Player player){
        int id = player.getId();
        int pX = 0;
        int pC = 0;
         ArrayList<Integer> xMovements = getMovesPlayer(1);
         ArrayList<Integer> oMovements = getMovesPlayer(2);
        for(int i = 0; i < 8; i++){
            int n1 = winnerPos.get(i)[0];
            int n2 = winnerPos.get(i)[1];
            int n3 = winnerPos.get(i)[2];
            if(!oMovements.contains(n1)&&!oMovements.contains(n2)&&!oMovements.contains(n3)){
                pX++;
            } 
            if(!xMovements.contains(n1)&&!xMovements.contains(n2)&&!xMovements.contains(n3)){
                pC++;
            }
        }if(id == 1){
            return pX - pC;
        }
        return pC - pX;
    }
    

    public int[] getBoard() {
        return board;
    }

    public int getUtility() {
        return utility;
    }


    public void setBoard(int[] board) {
        this.board = board;
    }
    
    public void setUtility (Player player){
        this.utility = utility(player);
    }

    
    public void setUtility(int utility){
        this.utility = utility;
    }
    
    public boolean isFull(){
        int cont = 0;
        for(int i: board){
            if(i != 0){
                cont++;
            }
        }
        return cont == size;
    }
}
