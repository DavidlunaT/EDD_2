/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

import EDD_2.dataStructures.Tree;
import EDD_2.dataStructures.TreeNode;
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
    private int size = 9;

    public MiniMax(Board actualGame) {
        this.currentBoard = actualGame;        
    }

    public void gameTree() {
        Tree<Board> treeGame = new Tree<>(currentBoard);
        List<Tree<Board>> states = createStates(1, currentBoard);
        treeGame.getRootNode().setChildren(states);
        for (Tree<Board> children : states) {
            List<Tree<Board>> oponentStates = createStates(2, children.getRoot());
            children.getRootNode().setChildren(oponentStates);
        }
    }

    public List<Tree<Board>> createStates(int idPlayer, Board b) {
        List<Tree<Board>> states = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (b.getBoard()[i] == 0) {
                int[] arr = new int[size];
                copyArray(b.getBoard(), arr);
                arr[i] = idPlayer; 
                states.add(new Tree(new Board(arr)));
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

    public Board searchMax() {
        List<Tree<Board>> rootTreeGame = treeGame.getRootNode().getChildren();
        for (Tree<Board> tb : rootTreeGame) {
            Board b = tb.getRoot();
            if(b.getUtility() == maxUtility()){
                return b;
            }
        }
        return null;
    }
    
    public int maxUtility(){
        List<Tree<Board>> rootTreeGame = treeGame.getRootNode().getChildren();
        int maxUtility = Integer.MIN_VALUE;
        for (Tree<Board> tb : rootTreeGame) {
            Board b = tb.getRoot();
            maxUtility = Math.max(maxUtility, b.getUtility()); //crear getUtility
        }
        return maxUtility;
    }
}
