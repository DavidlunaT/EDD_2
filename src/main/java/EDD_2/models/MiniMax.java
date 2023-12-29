/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

import EDD_2.dataStructures.Tree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author gabsy
 */
public class MiniMax {

    private Board currentBoard;
    private Tree<Board> treeGame;    

    public MiniMax(Board actualGame) {
        this.currentBoard = actualGame;        
    }

    public void gameTree() {
        Tree<Board> treeGame = new Tree<>(currentBoard);
        List<Tree<Board>> states = createStates(1,currentBoard);
        treeGame.getRootNode().setChildren(states);
        for (Tree<Board> children : states) {
            List<Tree<Board>> oponentStates = createStates(2,children.getRoot());
            children.getRootNode().setChildren(oponentStates);
        }
    }
    

    public List<Tree<Board>> createStates(int idPlayer, Board b) {
        List<Tree<Board>> states = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (b.getBoard()[i] == 0) {
                int[] arr2 = new int[9];
                copyArray(b.getBoard(), arr2);
                arr2[i] = idPlayer; //playerid jugador (computadora)
                states.add(new Tree(new Board(arr2)));
            }
        }
        return states;
    }

    public static void copyArray(int[] source, int[] destination) {
        System.arraycopy(source, 0, destination, 0, source.length);
    }
    
    
    
    public void setMin(){       
        List<Tree<Board>> rootChildren = treeGame.getRootNode().getChildren();
        for(Tree<Board> rootChild : rootChildren){
            List<Tree<Board>> childChildren = rootChild.getRootNode().getChildren();
            List<Integer> utilities = childrenUtilities(childChildren);
            int min = Collections.min(utilities);     
            rootChild.getRoot().setUtility(min);
        }
    }
    
    public List<Integer> childrenUtilities(List<Tree<Board>> children){
        List<Integer> utilities = new ArrayList<>();
        for(Tree<Board> c : children){
            int utility = c.getRoot().getUtility();
            utilities.add(utility);
        }      
        return utilities;
    }
    
    public Board searchMax(){
        //retornamos el board con la mayor utilidad (de los hijos del root)
        Board bestBoard = new Board();
        return bestBoard;
    }
}
