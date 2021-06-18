package System;

import java.io.Console;
import java.util.ArrayList;

public class Login {
    static Console console=System.console();
    String user_name;
    String password;
    int user_ID = -1;
    
    boolean authenticateReader(){
        System.out.print("\t\t\tEnter username:");
        user_name = console.readLine();
        
        System.out.print("\t\t\tEnter Password:");
        password = String.valueOf(console.readPassword());
        
        DatabaseInterface db = Database.newInstance();
        
        ArrayList<Reader> reader_list = db.getReaderList();
        for(Reader reader: reader_list){
            if(reader.getUserName().equals(user_name) && db.getUserPassword(user_name).equals(password)){
                user_ID = reader.getID();
            }
        }
        
        if(user_ID == -1){
            System.out.println("\t\t\tInvalid UserName or Password");
            System.out.println("\t\t\tTry again!!!\n");
            return false;
        }
        return true;
    }
    
    boolean authenticateLibrarian(){
        
        System.out.print("\t\t\tEnter username:");
        user_name = console.readLine();
        
        System.out.print("\t\t\tEnter Password:");
        password = String.valueOf(console.readPassword());
        
        DatabaseInterface db = Database.newInstance();
        
        ArrayList<Librarian> librarian_list = db.getLibrarianList();
        for(Librarian librarian: librarian_list){
            if(librarian.getUserName().equals(user_name) && db.getUserPassword(user_name).equals(password)){
                user_ID = librarian.getID();
            }
        }
        
        if(user_ID == -1){
            System.out.println("\t\t\tInvalid UserName or Password");
            System.out.println("\t\t\tTry again!!!\n");
            return false;
        }
        return true;
    }
    
    boolean authenticateDeliveryMan(){
        System.out.print("\t\t\tEnter username:");
        user_name = console.readLine();
        
        System.out.print("\t\t\tEnter Password:");
        password = String.valueOf(console.readPassword());
        
        DatabaseInterface db = Database.newInstance();
        
        ArrayList<DeliveryMan> delivery_man_list = db.getDeliveryManList();
        for(DeliveryMan delivery_man: delivery_man_list){
            if(delivery_man.getUserName().equals(user_name) && db.getUserPassword(user_name).equals(password)){
                user_ID = delivery_man.getID();
            }
        }
        
        if(user_ID == -1){
            System.out.println("\t\t\tInvalid UserName or Password");
            System.out.println("\t\t\tTry again!!!\n");
            return false;
        }
        return true;
    }
}