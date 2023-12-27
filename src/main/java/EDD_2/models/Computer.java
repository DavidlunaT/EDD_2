package EDD_2.models;

import EDD_2.dataStructures.Tree;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Computer extends Player {
    
    public int id;
    public Tree<Board> IA;
    


    @Override
    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void calculateBestMove(Board currentBoard){
        
    }
    
    public void miniMax(){
        
    }
    
    public void createTree(Board currentBoard){
        IA = new Tree(currentBoard);
        ArrayList<Tree<Board>> estados = new ArrayList();
        for(int i = 0; i<currentBoard.getBoard().length;i++){
            if(currentBoard.getBoard()[i] !=0){
                int[] arr2 = new int[9];
                copyArray(currentBoard.getBoard(),arr2);
                arr2[i] = 1; //playerid
                estados.add(new Tree(new Board(arr2)));
            }
        }
    }
    
    public static void copyArray(int[] source, int[] destination) {
        System.arraycopy(source, 0, destination, 0, source.length);
    }
    
    
}
