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

}
    
    
