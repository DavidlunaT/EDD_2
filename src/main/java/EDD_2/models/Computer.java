package EDD_2.models;

import EDD_2.dataStructures.Tree;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Computer extends Player {
    

    private int id;

 
    public Tree<Board> IA;

    
    public Computer(int id){
        this.id = id;
    }

    
    public void calculateBestMove(){
        
    }
    
    @Override
    public int getId(){
        return id;
    }

    public void calculateBestMove(Board currentBoard){
        
    }


}
