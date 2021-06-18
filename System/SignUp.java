package System;

import java.io.Console;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SignUp {
    
    static Console console=System.console();

    void getUserDetails(){
        DatabaseInterface db = Database.newInstance();
        int reader_id = db.getTotalNumberOfUser()+1;
        String user_name = getUsername();
        String password = getPassword();
        String address = getAddress();
        String phone_number = getContactNumber();
        
        db.storeReader(new Reader(reader_id, user_name, address, phone_number), password);
        
        System.out.println("\t\t\tSuccessfully Account created!!!");
        System.out.println("\t\t\tLogin into your account:)\n\n");
    }
    
    String getUsername(){
        String user_name;
        DatabaseInterface db = Database.newInstance();
        
        while(true){
            int found = 0;
            
            System.out.print("\t\t\tEnter username:");
            user_name = console.readLine();
            
            ArrayList<Reader> reader_list = db.getReaderList();
            for(Reader reader: reader_list){
                if(reader.getUserName().equals(user_name)){
                    found = 1;
                    break;
                }
            }
            
            if(!"".equals(user_name) && found == 0)
                break;
            System.out.println("\t\t\tAlreay Username exists!!!\n");
        }
        return user_name;
    }
    
    String getPassword(){
        String password;
        String retype_password;
        while(true){
            System.out.print("\t\t\tEnter Password:");
            password = String.valueOf(console.readPassword());
        
            System.out.print("\t\t\tRetype Password:");
            retype_password = String.valueOf(console.readPassword());
            
            if(!"".equals(password) && password.equals(retype_password)){
                break;
            }
            System.out.println("\t\t\tPassword not matching!!!\n");
        }
        return password;
    }

    String getAddress() {
        String address;
        while(true){
            System.out.print("\t\t\tEnter address:");
            address = console.readLine();
            if(!"".equals(address)) break;
            System.out.println("\t\t\tAddress cannot be empty\n");
        }
        return address;
    }
    
    
    String getContactNumber() {
        String contact_number;
        while(true){
            System.out.print("\t\t\tEnter contact number:");
            contact_number = console.readLine();
            
            if (Pattern.matches("[0-9]+", contact_number) == true && contact_number.length() == 10) 
                    break;   
            System.out.println("\t\t\tInavlid Mobile number:(\n");
        }
        return contact_number;
    }
}