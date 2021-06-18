package System;

public class Book {
    private int book_id;
    private String book_author;
    private String book_title;
    private int available_count;
    private int ordered_count;
    
    Book(int book_id, String book_author, String book_title, int available_count, int ordered_count){
        this.book_id = book_id;
        this.book_author = book_author;
        this.book_title = book_title;
        this.available_count = available_count;
        this.ordered_count = ordered_count;
    }
    
    int getID(){
        return this.book_id;
    }
    
    String getBookAuthor(){
        return this.book_author;
    }
    
    String getBookTitle(){
        return this.book_title;
    }
    
    int getOrderedCount(){
        return this.ordered_count;
    }
    
    int getAvailableCount(){
        return this.available_count;
    }
    
    void setBookAuthor(String book_author){
        this.book_author = book_author;
    }
    
    void setBookTitle(String book_title){
        this.book_title = book_title;
    }
    
    void setAvailableCount(int available_count){
        this.available_count = available_count;
    }
    
    void setOrderedCount(int ordered_count){
        this.ordered_count = ordered_count;
    }
}