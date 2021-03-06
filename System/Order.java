package System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Order {
    static DatabaseInterface db = Database.newInstance();
    
    static void placeOrder(int user_id){
        ArrayList<CartItem> cart = db.getCartList(user_id);
        ArrayList<OrderItem> order_list = new ArrayList<OrderItem>();
        HashMap<Integer, String> book_copy_list = db.getBookCopyIDList(cart);
        if(cart.isEmpty()){
            System.out.println("\t\t\tEmpty Cart - No order Placed");
            return;
        }
        Random random = new Random();
        int order_id = random.nextInt() & Integer.MAX_VALUE;
        for(CartItem item : cart){
            if(null != book_copy_list.get(item.getBook().getID())){
                order_list.add(new OrderItem(order_id, book_copy_list.get(item.getBook().getID()) , item.getUser(), item.getBook(), 1));
                System.out.println("\t\t\t"+item.getBook().getID()+" - Book Order Placed");
            }
            else
                System.out.println("\t\t\t"+item.getBook().getID()+" - Book Unavailable");
        }
        db.storeOrderItem(order_list);
        Cart.clearCart(user_id);
    }
    
    static void displayOrderDetails(int user_id){
        ArrayList<OrderItem> order_list = db.getOrderList(user_id);
        if(order_list.isEmpty()){
            System.out.println("\n\t\t\t\t\t\tNo Order Placed\n");
            return;
        }
        System.out.println("\t\t\t"+"Order ID"+"\t\t"+"Book ID"+"\t\t"+"Status"+"\t\t"+"Fine"+"\t"+"Due Date"+"\t\t"+"Book Title"+"\n");
        for(OrderItem item : order_list){
            if(item.getUser().getID() == user_id){
                Book book = item.getBook();
                System.out.println("\t\t\t"+item.getOrderID()+"\t\t"+item.getBook().getID()+"\t\t"+db.getStatusByID(item.getStatusID())+"\t"+item.getFine()+"\t"+item.getDueDate()+"\t\t"+book.getBookTitle()+"\n");
            }
        }
    }
    
    static void displayAllUserOrderDetails(){
        ArrayList<Reader> reader_list = db.getReaderList();
        for(Reader reader : reader_list){
            System.out.println("\t\t\tUser ID :- "+reader.getID());
            displayOrderDetails(reader.getID());
        }
    }
    
    static boolean checkOrderID(int order_id){
        ArrayList<OrderItem> order_list = db.getOrderList();
        for(OrderItem item : order_list){
            if(order_id == item.getOrderID()){
                return true;
            }
        }
        return false;
    }
    
    static boolean compareOrderUser(int order_id, int user_id){
        ArrayList<OrderItem> order_list = db.getOrderList();
        for(OrderItem item : order_list){
            if(order_id == item.getOrderID() && user_id == item.getUser().getID()){
                return true;
            }
        }
        return false;
    }
}