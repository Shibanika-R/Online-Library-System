package System;

import java.util.ArrayList;
import java.util.HashMap;

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
    ArrayList<CartItem> getCartList(int user_id);
    boolean isCartItemAvailable(int user_id, int book_id);
    void removeCartItem(CartItem cart_item);
    ArrayList<OrderItem> getOrderList();
    ArrayList<OrderItem> getOrderList(int user_id);
    void storeOrderItem(ArrayList<OrderItem> order_list);
    ArrayList<DeliveryMan> getDeliveryManList();
    void changeDeliveryStatus(int order_id, int status_id);
    String getStatusByID(int status_id);
    Book getBookByID(int book_id);
    ArrayList<Book> getBooksByTitle(String title);
    ArrayList<Book> getBooksByAuthor(String author);
    HashMap<Integer, String> getBookCopyIDList(ArrayList<CartItem> cart);
    void returnBookUpdate(int order_id);
    void updateOrderDate(int order_id);
    void updateReturnDate(int order_id);
    ArrayList<OrderItem> calculateFine(ArrayList<OrderItem> order_list);
}