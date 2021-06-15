package System;

public class Reader{
    private int reader_id;
    private String user_name;
    
    Reader(int reader_id, String user_name){
        this.reader_id = reader_id;
        this.user_name = user_name;
    }
    
    int getID(){
        return this.reader_id;
    }

    String getUserName(){
        return this.user_name;
    }
    
}