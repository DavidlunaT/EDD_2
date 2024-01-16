/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD_2.models;

import EDD_2.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Ranking {

    PriorityQueue<Person> ranking = new PriorityQueue<>((p1, p2) -> (p1.getContWins() / p1.getContDefeats()) - (p2.getContWins() / p2.getContDefeats()));
    ArrayList<Person> peopleRegister = new ArrayList<>();
    
    public static void escribirEnArchivo(List<Person> contacts) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(App.path+"EDD_2/"+"files/people.ser"))) {
            oos.writeObject(contacts);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Person> loadPeople() {
        ArrayList<Person> people = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(App.path+"EDD_2/"+"files/people.ser"))) {
            people = (ArrayList<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return people;
    }
    
    
}
