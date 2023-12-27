/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

/**
 *
 * @author David
 */
public class Computer extends Player {
    
    private int id;
    
    public Computer(int id){
        this.id = id;
    }
    
    public void calculateBestMove(){
        
    }
    
    @Override
    public int getId(){
        return id;
    }
}
