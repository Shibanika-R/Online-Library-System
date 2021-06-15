package System;

import java.io.Console;
import java.util.ArrayList;

public class DeliveryOperations {
    
    static void displayDeliverList(){
        DatabaseInterface db = Database.newInstance();
        ArrayList<OrderItem> order_list = db.getOrderList();
        ArrayList<Book> book_list = db.getBookList();
        ArrayList<Reader> reader_list = db.getReaderList();
        System.out.println("\n\t\t\t\t\t"+"Delivery Details"+"\n");
        System.out.println("\t\t\t"+"Order ID"+"\t"+"Cust Name"+"\t"+"Phone No."+"\t"+"Address"+"\t\t\t"+"Book Title"+"\n");
        for(OrderItem order : order_list){
            if(order.getStatusID() == 1){
               String book_name = "";
               String user_name = "";
               String phone_no;
               String address;
               for(Book book : book_list){
                   if(book.getID() == order.getBookID()){
                       book_name = book.getBookTitle();
                       break;
                   }
               }
               for(Reader reader : reader_list){
                   if(reader.getID() == order.getUserID()){
                       user_name = reader.getUserName();
                       break;
                   }
               }
               phone_no = db.getReaderPhoneNo(order.getUserID());
               address = db.getReaderAddress(order.getUserID());
               System.out.println("\t\t\t"+order.getOrderID()+"\t\t"+user_name+"\t\t"+phone_no+"\t"+address+"\t\t"+book_name+"\n");
            }
        }
    }
    
    static void displayReturnList(){
        DatabaseInterface db = Database.newInstance();
        ArrayList<OrderItem> order_list = db.getOrderList();
        ArrayList<Book> book_list = db.getBookList();
        ArrayList<Reader> reader_list = db.getReaderList();
        System.out.println("\n\t\t\t\t\t"+"Return Book Details"+"\n");
        System.out.println("\t\t\t"+"Order ID"+"\t"+"Cust Name"+"\t"+"Phone No."+"\t"+"Address"+"\t\t\t"+"Book Title"+"\n");
        for(OrderItem order : order_list){
            if(order.getStatusID() == 3){
               String book_name = "";
               String user_name = "";
               String phone_no;
               String address;
               for(Book book : book_list){
                   if(book.getID() == order.getBookID()){
                       book_name = book.getBookTitle();
                       break;
                   }
               }
               for(Reader reader : reader_list){
                   if(reader.getID() == order.getUserID()){
                       user_name = reader.getUserName();
                       break;
                   }
               }
               phone_no = db.getReaderPhoneNo(order.getUserID());
               address = db.getReaderAddress(order.getUserID());
               System.out.println("\t\t\t"+order.getOrderID()+"\t\t"+user_name+"\t\t"+phone_no+"\t"+address+"\t\t"+book_name+"\n");
            }
        }
    }
    
    static void displayDeliveredList(int user_id){
        DatabaseInterface db = Database.newInstance();
        ArrayList<OrderItem> order_list = db.getOrderList();
        ArrayList<Book> book_list = db.getBookList();
        ArrayList<Reader> reader_list = db.getReaderList();
        System.out.println("\n\t\t\t\t\t"+"Delivered Details"+"\n");
        System.out.println("\t\t\t"+"Order ID"+"\t\t"+"Book Title"+"\n");
        for(OrderItem order : order_list){
            if(order.getStatusID() == 2 && order.getUserID() == user_id){
               String book_name = "";
               for(Book book : book_list){
                   if(book.getID() == order.getBookID()){
                       book_name = book.getBookTitle();
                       break;
                   }
               }
               System.out.println("\t\t\t"+order.getOrderID()+"\t\t"+book_name+"\n");
            }
        }
    }
    
    static void setDeliverStatus(){
        DatabaseInterface db = Database.newInstance();
        Console console = System.console();
        displayDeliverList();
        System.out.println("\n\t\t\tBook Delivery Status\n");
        int order_id;
        System.out.print("\t\t\t"+"Enter Order ID: ");
        try{
            order_id = Integer.parseInt(console.readLine());
            if(!Order.checkOrderID(order_id)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Order ID");
            return;
        }
        db.changeDeliveryStatus(order_id, 2);
        System.out.println("\n\t\t\tBook Delivered!!!");
    }
    
    static void requestReturn(int user_id){
        DatabaseInterface db = Database.newInstance();
        Console console = System.console();
        displayDeliveredList(user_id);
        System.out.println("\n\t\t\tBook Return Request\n");
        int order_id;
        System.out.print("\t\t\t"+"Enter Order ID: ");
        try{
            order_id = Integer.parseInt(console.readLine());
            if(!Order.checkOrderID(order_id) || !Order.compareOrderUser(order_id, user_id)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Order ID");
            return;
        }
        db.changeDeliveryStatus(order_id, 3);
        System.out.println("\n\t\t\tReturn Book Request Raised!!!");
    }
    
    static void setReturnStatus(){
        DatabaseInterface db = Database.newInstance();
        Console console = System.console();
        displayReturnList();
        System.out.println("\n\t\t\t\t\tBook Return Status\n");
        int order_id;
        System.out.print("\t\t\t"+"Enter Order ID: ");
        try{
            order_id = Integer.parseInt(console.readLine());
            if(!Order.checkOrderID(order_id)){
                Exception exception = new Exception();
                throw exception;
            }
        }catch(Exception e){
            System.out.println("\n\t\t\tInvalid Order ID");
            return;
        }
        db.changeDeliveryStatus(order_id, 4);
        System.out.println("\n\t\t\tBook Returned!!!");
    }
}