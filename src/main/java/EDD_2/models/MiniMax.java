/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

import EDD_2.dataStructures.Tree;
import java.util.ArrayList;

/**
 *
 * @author gabsy
 */
public class MiniMax {
    private Board currentBoard;
    
    public MiniMax(Board actualGame){
        this.currentBoard = actualGame;
    }
    
    public void gameTree(){
        Tree<Board> IA = new Tree<>(currentBoard);       
        ArrayList<Tree<Board>> states = new ArrayList();
        for(int i = 0; i<currentBoard.getBoard().length;i++){
            if(currentBoard.getBoard()[i] !=0){
                int[] arr2 = new int[9];
                copyArray(currentBoard.getBoard(),arr2);
                arr2[i] = 1; //playerid
                states.add(new Tree(new Board(arr2)));
            }
        }
    }
    
    public static void copyArray(int[] source, int[] destination) {
        System.arraycopy(source, 0, destination, 0, source.length);
    }
}
