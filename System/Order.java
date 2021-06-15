package System;

import java.util.ArrayList;

public class Order {
    static int getOrderCount(int user_id){
        DatabaseInterface db = Database.newInstance();
        ArrayList<OrderItem> order_list = db.getOrderList();
        int order_id = 0;
        for(OrderItem item : order_list){
                order_id = item.getOrderID();
        }
        return order_id;
    }
    
    static void placeOrder(int user_id){
        DatabaseInterface db = Database.newInstance();
        ArrayList<CartItem> cart = db.getCartList();
        int order_id = getOrderCount(user_id)+1;
        for(CartItem item : cart){
            if(item.getUserID() == user_id){
                db.storeOrderItem(new OrderItem(order_id, user_id, item.getBookID(), 1));
                db.decrementBookCount(item.getBookID());
            }
        }
        Cart.clearCart(user_id);
        System.out.println("\t\t\tOrder Placed!!!");
    }
    
    static void displayOrderDetails(int user_id){
        DatabaseInterface db = Database.newInstance();
        ArrayList<OrderItem> order_list = db.getOrderList();
        System.out.println("\t\t\t\t\t\tOrdered Items\n");
        System.out.println("\t\t\t"+"Order ID"+"\t"+"Book ID"+"\t\t"+"Status"+"\t\t\t"+"Book Title"+"\n");
        for(OrderItem item : order_list){
            if(item.getUserID() == user_id){
                Book book = BookOperations.getBookByID(item.getBookID());
                System.out.println("\t\t\t"+item.getOrderID()+"\t\t"+item.getBookID()+"\t\t"+db.getStatusByID(item.getStatusID())+"\t\t"+book.getBookTitle()+"\n");
            }
        }
    }
    
    static void displayAllUserOrderDetails(){
        DatabaseInterface db = Database.newInstance();
        ArrayList<Reader> reader_list = db.getReaderList();
        for(Reader reader : reader_list){
            System.out.println("\t\t\tUser ID : "+reader.getID());
            displayOrderDetails(reader.getID());
        }
    }
}