package System;

import java.util.ArrayList;

public interface DatabaseInterface {
    ArrayList<Librarian> getLibrarianList();
    ArrayList<Reader> getReaderList();
    String getLibrarianPassword(String user_name);
    String getReaderPassword(String user_name);
    int getTotalNumberOfReader();
    void storeReader(Reader reader, String address, String phone_number ,String password);
    ArrayList<Book> getBookList();
    void storeBook(Book book);
    void deleteBook(int book_id);
    void updateBook(Book book_new);
    void decrementBookCount(int book_id);
    void incrementBookCount(int book_id);
    void storeCartItem(CartItem cart_item);
    ArrayList<CartItem> getCartList();
    boolean isCartItemAvailable(int user_id, int book_id);
    void removeCartItem(int user_id, int book_id);
    ArrayList<OrderItem> getOrderList();
    void storeOrderItem(OrderItem order_item);
    ArrayList<DeliveryMan> getDeliveryManList();
    String getDeliveryManPassword(String user_name);
    String getReaderPhoneNo(int user_id);
    String getReaderAddress(int user_id);
    void changeDeliveryStatus(int order_id, int status_id);
    String getStatusByID(int status_id);
}