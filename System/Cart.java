package System;

import java.io.Console;
import java.util.ArrayList;

public class Cart {
    static void addToCart(int user_id){
        DatabaseInterface db = Database.newInstance();
        BookOperations.displayAvailableBook(user_id);
        Console console = System.console();
        System.out.println("\t\t\tAdd Book to Cart\n");
        int book_id;
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
        CartItem cart_item = new CartItem(user_id, book_id);
        db.storeCartItem(cart_item);
        db.decrementBookCount(book_id);
        System.out.println("\n\t\t\tAdded to Cart!!!");
    }
    
    static void removeFromCart(int user_id){
        DatabaseInterface db = Database.newInstance();
        displayCart(user_id);
        Console console = System.console();
        System.out.println("\t\t\tRemove Book from Cart\n");
        int book_id;
        System.out.print("\t\t\t"+"Enter Book ID: ");
        try{
            book_id = Integer.parseInt(console.readLine());
            if(!BookOperations.checkBookID(book_id)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Book ID");
            return;
        }
        db.removeCartItem(user_id, book_id);
        db.incrementBookCount(book_id);
        System.out.println("\n\t\t\tRemoved from Cart!!!");
    }
    
    static void displayCart(int user_id){
        DatabaseInterface db = Database.newInstance();
        ArrayList<CartItem> cart = db.getCartList();
        System.out.println("\t\t\t\t\t\tCart Items\n");
        System.out.println("\t\t\t"+"ID"+"\t\t"+"Book Author"+"\t\t"+"Book Title"+"\n");
        for(CartItem item : cart){
            if(item.getUserID() == user_id){
                BookOperations.displayBookByID(item.getBookID());
            }
        }
    }
    
    static void clearCart(int user_id){
        DatabaseInterface db = Database.newInstance();
        ArrayList<CartItem> cart = db.getCartList();
        for(CartItem item : cart){
            if(item.getUserID() == user_id){
                db.removeCartItem(user_id, item.getBookID());
            }
        }
    }
}