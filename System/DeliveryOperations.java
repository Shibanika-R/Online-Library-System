package System;

import java.io.Console;
import java.util.ArrayList;

public class DeliveryOperations {
    static DatabaseInterface db = Database.newInstance();
    static void displayDeliverList(){
        ArrayList<OrderItem> order_list = db.getOrderList();
        System.out.println("\n\t\t\t\t\t"+"Delivery Details"+"\n");
        System.out.println("\t\t\t"+"Order ID"+"\t"+"Cust Name"+"\t"+"Phone No."+"\t"+"Address"+"\t\t\t"+"Book Title"+"\n");
        for(OrderItem order : order_list){
            if(order.getStatusID() == 1){
               System.out.println("\t\t\t"+order.getOrderID()+"\t"+order.getUser().getUserName()+"\t\t"+order.getUser().getPhoneNumber()+"\t"+order.getUser().getAddress()+"\t\t"+order.getBook().getBookTitle()+"\n");
            }
        }
    }
    
    static void displayReturnList(){
        ArrayList<OrderItem> order_list = db.getOrderList();
        System.out.println("\n\t\t\t\t\t"+"Return Book Details"+"\n");
        System.out.println("\t\t\t"+"Order ID"+"\t"+"Cust Name"+"\t"+"Phone No."+"\t"+"Address"+"\t\t\t"+"Book Title"+"\n");
        for(OrderItem order : order_list){
            if(order.getStatusID() == 3){
               System.out.println("\t\t\t"+order.getOrderID()+"\t"+order.getUser().getUserName()+"\t\t"+order.getUser().getPhoneNumber()+"\t"+order.getUser().getAddress()+"\t\t"+order.getBook().getBookTitle()+"\n");
            }
        }
    }
    
    static void displayDeliveredList(int user_id){
        ArrayList<OrderItem> order_list = db.getOrderList();
        System.out.println("\n\t\t\t\t\t"+"Delivered Details"+"\n");
        System.out.println("\t\t\t"+"Order ID"+"\t\t"+"Book Title"+"\n");
        for(OrderItem order : order_list){
            if(order.getStatusID() == 2 && order.getUser().getID() == user_id){
               System.out.println("\t\t\t"+order.getOrderID()+"\t\t"+order.getBook().getBookTitle()+"\n");
            }
        }
    }
    
    static void setDeliverStatus(){
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
        Console console = System.console();
        ArrayList<OrderItem> order_list = db.getOrderList();
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
        for(OrderItem item : order_list){
            if(item.getOrderID() == order_id){
                db.incrementBookCount(item.getBook().getID());
            }
        }
        System.out.println("\n\t\t\tBook Returned!!!");
    }
}