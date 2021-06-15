package System;

public class CartItem {
    private int user_id;
    private int book_id;
    
    CartItem(int user_id, int book_id){
        this.user_id = user_id;
        this.book_id = book_id;
    }
    
    int getUserID(){
        return this.user_id;
    }
    
    int getBookID(){
        return this.book_id;
    }
}
