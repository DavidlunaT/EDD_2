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
public class Person extends Player implements Serializable{
   
   private static final long serialVersionUID = 10L;
   private int id;
   private String nickname;
   private int contWins;
   private int contDefeats;
    
   public Person(int id){
       this.id = id;
       this.nickname = "";
       this.contWins = 0;
       this.contDefeats = 0;
   }

    public Person(String nickname, int contWins, int contDefeats) {
        this.nickname = nickname;
        this.contWins = contWins;
        this.contDefeats = contDefeats;
    }
   
   

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getContWins() {
        return contWins;
    }

    public void setContWins(int contWins) {
        this.contWins = contWins;
    }

    public int getContDefeats() {
        return contDefeats;
    }

    public void setContDefeats(int contDefeats) {
        this.contDefeats = contDefeats;
    }
   
   @Override
   public int getId(){
       return id;
   }
   
   @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if (obj != null &&  obj instanceof Person){
            Person other = (Person) obj;
            return nickname.equals(other.nickname);
        }
        return false;
    }
       
  
}
