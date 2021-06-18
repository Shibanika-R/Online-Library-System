package System;


import java.io.Console;
import java.util.ArrayList;

public class BookOperations {
    static DatabaseInterface db = Database.newInstance();
    static ArrayList<Book> book_list = db.getBookList();
    
    static void displayAvailableBook(int user_id){
        System.out.println("\t\t\t\t\t\tBook Details\n");
        System.out.println("\t\t\t"+"ID"+"\t\t"+"Book Author"+"\t\t"+"Book Title"+"\n");
        for(Book book : book_list){
            if(book.getAvailableCount() > 0 && !db.isCartItemAvailable(user_id, book.getID()))
                System.out.println("\t\t\t"+book.getID()+"\t\t"+book.getBookAuthor()+"\t\t"+book.getBookTitle()+"\n");
        }
    }
    
    static void displayBookDetails(){
        System.out.println("\t\t\t\t\t\tBook Details\n");
        System.out.println("\t\t\t"+"ID"+"\t"+"Count"+"\t\t"+"Book Author"+"\t\t"+"Book Title"+"\n");
        for(Book book : book_list){
            System.out.println("\t\t\t"+book.getID()+"\t"+book.getAvailableCount()+"\t\t"+book.getBookAuthor()+"\t\t"+book.getBookTitle()+"\n");
        }
    }
    
    
    static void displayBookByID(int book_id){
        Book book = db.getBookByID(book_id);
        System.out.println("\t\t\t"+book.getID()+"\t\t"+book.getBookAuthor()+"\t\t"+book.getBookTitle()+"\n");
    }
    
    static void addBookDetails(){
        Console console = System.console();
        System.out.println("\t\t\tAdd Book Details\n");
        int book_id = 0;
        String book_author;
        String book_title;
        int available_count;
        for(Book book : book_list){
            book_id = book.getID();
        }
        book_id++;
        try{
            System.out.print("\t\t\t"+"Enter Book Author: ");
            book_author = console.readLine();
            System.out.print("\t\t\t"+"Enter Book Title: ");
            book_title = console.readLine();
            System.out.print("\t\t\t"+"Enter Book Count: ");
            available_count = Integer.parseInt(console.readLine());
            if("".equals(book_author) || "".equals(book_title)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Details");
            return;
        }
        Book book = new Book(book_id, book_author, book_title, available_count, 0);
        db.storeBook(book);
        book_list.add(book);
        System.out.println("\n\t\t\tBook Added Successfully!!!");
    }
    
    static void removeBookDetails(){
        displayBookDetails();
        Console console = System.console();
        System.out.println("\t\t\tRemove Book\n");
        int book_id;
        System.out.print("\t\t\t"+"Enter Book ID: ");
        try{
            book_id = Integer.parseInt(console.readLine());
            if(!checkBookID(book_id)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Book ID");
            return;
        }
        db.deleteBook(book_id);
        book_list = db.getBookList();
        System.out.println("\n\t\t\tBook Removed Successfully!!!");
    }
    
    static void modifyBookDetails(){
        displayBookDetails();
        Console console = System.console();
        int book_id;
        String book_author;
        String book_title;
        int available_count;
        System.out.print("\t\t\t"+"Enter Book ID: ");
        try{
            book_id = Integer.parseInt(console.readLine());
            if(!checkBookID(book_id)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Book ID");
            return;
        }
        System.out.println("\t\t\tModify Book Details\n");
        System.out.print("\t\t\t"+"Enter Book Author: ");
        book_author = console.readLine();
        System.out.print("\t\t\t"+"Enter Book Title:  ");
        book_title = console.readLine();
        System.out.print("\t\t\t"+"Enter Book Count: ");
        available_count = Integer.parseInt(console.readLine());
        db.updateBook(new Book(book_id, book_author, book_title, available_count, 0));
        book_list = db.getBookList();
        System.out.println("\n\t\t\tUpdated Successfully");
    }
    
    static boolean checkBookID(int book_id){
        ArrayList<Book> book_list = db.getBookList();
        for(Book book : book_list){
            if(book_id == book.getID()){
                return true;
            }
        }
        return false;
    }
    
    static int getCount(int book_id){
        for(Book book : book_list){
            if(book.getID() == book_id)
                return book.getAvailableCount();
        }
        return -1;
    }
}