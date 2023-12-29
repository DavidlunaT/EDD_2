package EDD_2.models;

import EDD_2.dataStructures.Tree;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Computer extends Player {
    private int id;
    private MiniMax minimax;
    
    public Computer(int id, Board b){
        this.id = id;
        this.minimax = new MiniMax(b);
    }
    
    @Override
    public int getId(){
        return id;
    }
 
    public int calculateBestMove(Board b){
        minimax.setCurrentBoard(b);

        return minimax.searchMaxMove();


    }


}
