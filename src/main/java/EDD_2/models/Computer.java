package EDD_2.models;

import EDD_2.dataStructures.Tree;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Computer extends Player {
    

    private int id;
    
    public Computer(int id){
        this.id = id;
    }
    
    @Override
    public int getId(){
        return id;
    }

    public int getMove(Board board){
        return 0;
    }
    
    public void calculateBestMove(){
        
    }


}
