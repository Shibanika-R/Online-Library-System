package System;

import java.util.ArrayList;

public interface DatabaseInterface {
    ArrayList<Librarian> getLibrarianList();
    ArrayList<Reader> getReaderList();
    void storeReader(Reader reader ,String password);
    ArrayList<Book> getBookList();
    void storeBook(Book book);
    String getUserPassword(String user_name);
    
    Reader getReaderByID(int user_id);
    int getTotalNumberOfUser();
    void deleteBook(int book);
    void updateBook(Book book_new);
    void decrementBookCount(int book_id);
    void incrementBookCount(int book_id);
    void storeCartItem(CartItem cart_item);
    ArrayList<CartItem> getCartList();
    boolean isCartItemAvailable(int user_id, int book_id);
    void removeCartItem(CartItem cart_item);
    ArrayList<OrderItem> getOrderList();
    void storeOrderItem(ArrayList<OrderItem> order_list);
    ArrayList<DeliveryMan> getDeliveryManList();
    
    void changeDeliveryStatus(int order_id, int status_id);
    String getStatusByID(int status_id);
    
    
    Book getBookByID(int book_id);
}