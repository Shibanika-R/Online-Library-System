package System;

public class OrderItem {
    private int order_id;
    private String book_copy_id;
    private Reader reader;
    private Book book;
    private int status_id;
    private double fine;
    private String due_date;
    
    OrderItem(int order_id, String book_copy_id, Reader reader, Book book, int status_id){
        this.order_id = order_id;
        this.reader = reader;
        this.book = book;
        this.status_id = status_id;
        this.book_copy_id = book_copy_id;
    }
    
    void setFine(double fine){
        this.fine = fine;
    }
    
    void setDueDate(String due_date){
        this.due_date = due_date;
    }
    
    int getOrderID(){
        return this.order_id;
    }
    
    String getBookCopyID(){
        return this.book_copy_id;
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
    
    double getFine(){
        return this.fine;
    }
    
    String getDueDate(){
        return this.due_date;
    }
}