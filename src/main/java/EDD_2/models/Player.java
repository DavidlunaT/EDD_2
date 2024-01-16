/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

import java.io.Serializable;

/**
 *
 * @author David
 */
public abstract class  Player  implements Serializable{
    private static final long serialVersionUID = 10L;
    private int id;
    
   public abstract int getId();
}
