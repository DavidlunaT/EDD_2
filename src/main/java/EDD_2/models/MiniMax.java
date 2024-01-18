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

    private Player playerTurn;
    private Board currentBoard;
    private Tree<Board> treeGame;
    private int size = 9;
    private int id;

    public MiniMax(Board actualGame, Player p) {
        this.currentBoard = actualGame;
        this.playerTurn = p;
    }

    public void initializeUtility(int idTurn, int idOponent) {
        gameTree(idTurn, idOponent); //Crear el arbol y lo llena los 2 niveles
        setMin(); //Setea la utilidad de los boards hijos
    }
    
    //Crea el arbol completo de los 2 siguientes turnos a partir del board actual
    public void gameTree(int idTurn, int idOponent) {
        treeGame = new Tree<>(currentBoard);
        List<Tree<Board>> states = createStates(idTurn, currentBoard);
        treeGame.getRootNode().setChildren(states);
        for (Tree<Board> child : states) {
            List<Tree<Board>> oponentStates = createStates(idOponent, child.getRoot());
            child.getRootNode().setChildren(oponentStates);
        }
    }
    
    //Crea todos los posibles estados del board de la tabla actual
    public List<Tree<Board>> createStates(int idPlayer, Board b) {
        List<Tree<Board>> states = new ArrayList<>();
        for (int i = 0; i < size; i++) {
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

    //calcula la utilidad a todas las hojas y setea la minima al padre. 
    public void setMin() {
        List<Tree<Board>> rootChildren = treeGame.getRootNode().getChildren();
        for (Tree<Board> rootChild : rootChildren) {
            List<Tree<Board>> childChildren = rootChild.getRootNode().getChildren();
            
            List<Integer> utilities = childrenUtilities(childChildren);
            System.out.println(utilities);
            int min = Integer.MIN_VALUE;
            
            if(!utilities.isEmpty()){
                min = Collections.min(utilities);
            }
            rootChild.getRoot().setUtility(min);
        }
    }

    public List<Integer> childrenUtilities(List<Tree<Board>> children) {
        List<Integer> utilities = new ArrayList<>();
        for (Tree<Board> c : children) {
            //Verificar----------
            int utility = c.getRoot().utility(playerTurn);
            //-------------------
            utilities.add(utility);
        }
        return utilities;
    }

    //saca el max de las utilidades de los hijos del root
    public Board searchMax() {
        List<Tree<Board>> rootTreeGame = treeGame.getRootNode().getChildren();
        for (Tree<Board> tb : rootTreeGame) {
            Board b = tb.getRoot();
            if (b.getUtility() == maxUtility()) {
                return b;
            }
        }
        return null;
    }

    public int searchMaxMove() {
        Board b = searchMax();
        if (b != null) {
            for (int i = 0; i < b.getBoard().length; i++) {
                if (currentBoard.getBoard()[i] != b.getBoard()[i]) {
                    return i;
                }
            }    
        }
        return -1;
    }
    
    

    public int maxUtility() {
        List<Tree<Board>> rootTreeGame = treeGame.getRootNode().getChildren();
        int maxUtility = Integer.MIN_VALUE;
        for (Tree<Board> tb : rootTreeGame) {
            Board b = tb.getRoot();
            maxUtility = Math.max(maxUtility, b.getUtility());
        }
        return maxUtility;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }
    
    
}
