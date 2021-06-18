package System;

import java.io.Console;
import java.util.ArrayList;

public class Cart {
    static DatabaseInterface db = Database.newInstance();
    static void addToCart(int user_id){
        BookOperations.displayAvailableBook(user_id);
        Console console = System.console();
        System.out.println("\t\t\tAdd Book to Cart\n");
        int book_id;
        Book book;
        Reader reader;
        CartItem cart_item;
        System.out.print("\t\t\t"+"Enter Book ID: ");
        try{
            book_id = Integer.parseInt(console.readLine());
            if(!BookOperations.checkBookID(book_id) || BookOperations.getCount(book_id) <= 0){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Book ID");
            return;
        }
        book = db.getBookByID(book_id);
        reader = db.getReaderByID(user_id);
        cart_item = new CartItem(reader, book);
        db.storeCartItem(cart_item);
        System.out.println("\n\t\t\tAdded to Cart!!!");
    }
    
    static void removeFromCart(int user_id){
        displayCart(user_id);
        Console console = System.console();
        System.out.println("\t\t\tRemove Book from Cart\n");
        int book_id;
        Book book;
        Reader reader;
        System.out.print("\t\t\t"+"Enter Book ID: ");
        try{
            book_id = Integer.parseInt(console.readLine());
            if(!db.isCartItemAvailable(user_id, book_id)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Book ID");
            return;
        }
        book = db.getBookByID(book_id);
        reader = db.getReaderByID(user_id);
        db.removeCartItem(new CartItem(reader, book));
        System.out.println("\n\t\t\tRemoved from Cart!!!");
    }
    
    static void displayCart(int user_id){
        ArrayList<CartItem> cart = db.getCartList();
        System.out.println("\t\t\t\t\t\tCart Items\n");
        System.out.println("\t\t\t"+"ID"+"\t\t"+"Book Author"+"\t\t"+"Book Title"+"\n");
        for(CartItem item : cart){
            if(item.getUser().getID() == user_id){
                System.out.println("\t\t\t"+item.getBook().getID()+"\t\t"+item.getBook().getBookAuthor()+"\t\t"+item.getBook().getBookTitle()+"\n");
            }
        }
    }
    
    static void clearCart(int user_id){
        ArrayList<CartItem> cart = db.getCartList();
        for(CartItem item : cart){
            if(item.getUser().getID() == user_id){
                db.removeCartItem(item);
            }
        }
    }
}