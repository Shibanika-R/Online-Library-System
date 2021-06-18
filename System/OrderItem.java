package System;

public class OrderItem {
    private int order_id;
    private Reader reader;
    private Book book;
    private int status_id;
    
    OrderItem(int order_id, Reader reader, Book book, int status_id){
        this.order_id = order_id;
        this.reader = reader;
        this.book = book;
        this.status_id = status_id;
    }
    
    int getOrderID(){
        return this.order_id;
    }
    
    Reader getUser(){
        return this.reader;
    }
    
    Book getBook(){
        return this.book;
    }
    
    int getStatusID(){
        return this.status_id;
    }
}
