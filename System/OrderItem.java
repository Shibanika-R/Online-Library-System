package System;

public class OrderItem {
    private int order_id;
    private int user_id;
    private int book_id;
    private int status_id;
    
    OrderItem(int order_id, int user_id, int book_id, int status_id){
        this.order_id = order_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.status_id = status_id;
    }
    
    int getOrderID(){
        return this.order_id;
    }
    
    int getUserID(){
        return this.user_id;
    }
    
    int getBookID(){
        return this.book_id;
    }
    
    int getStatusID(){
        return this.status_id;
    }
}
