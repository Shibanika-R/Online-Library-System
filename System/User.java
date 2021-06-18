package System;

public class User {
    int id;
    String user_name;
    
    User(int id, String user_name){
        this.id = id;
        this.user_name = user_name;
    }
    
    int getID(){
        return this.id;
    }
    
    String getUserName(){
        return this.user_name;
    }
}
