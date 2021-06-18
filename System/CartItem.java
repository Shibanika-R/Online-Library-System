package System;

public class CartItem {
    private Reader user;
    private Book book;
    
    CartItem(Reader user, Book book){
        this.user = user;
        this.book = book;
    }
    
    Reader getUser(){
        return this.user;
    }
    
    Book getBook(){
        return this.book;
    }
}
