package System;

public class Librarian{
    private int librarian_id;
    private String user_name;
    
    Librarian(int librarian_id, String user_name){
        this.librarian_id = librarian_id;
        this.user_name = user_name;
    }
    
    int getID(){
        return this.librarian_id;
    }
    
    String getUserName(){
        return this.user_name;
    }
    
}