package System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database implements DatabaseInterface{
    private static Database instance_Ref;
     Connection con = null;
     private Database(){
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost/library","root","");  
        }catch(Exception e){ System.out.println(e);} 
    }
    
    public static Database newInstance(){
        if(instance_Ref == null)
            instance_Ref = new Database();
        return instance_Ref;
    }

    public ArrayList<Librarian> getLibrarianList() {
        ArrayList<Librarian> librarian_list = new ArrayList<Librarian>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from librarian");  
            while(rs.next())  
                librarian_list.add(new Librarian(rs.getInt("id"), rs.getString("name")));
        }catch(Exception e){ System.out.println(e);}
        return librarian_list;
    }

    public ArrayList<Reader> getReaderList() {
        ArrayList<Reader> reader_list = new ArrayList<Reader>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from reader");  
            while(rs.next())  
                reader_list.add(new Reader(rs.getInt("id"), rs.getString("name")));
        }catch(Exception e){ System.out.println(e);}
        return reader_list;
    }

    public String getLibrarianPassword(String user_name) {
        String password = "";
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from librarian");  
            while(rs.next()){
                if(rs.getString("name").equals(user_name)){
                    password = rs.getString("password");
                    break;
                }
            }
        }catch(Exception e){ System.out.println(e);}
        return password;
    }

    public String getReaderPassword(String user_name) {
        String password = "";
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from reader");  
            while(rs.next()){
                if(rs.getString("name").equals(user_name)){
                    password = rs.getString("password");
                    break;
                }
            }
        }catch(Exception e){ System.out.println(e);}
        return password;
    }

    public int getTotalNumberOfReader() {
        int count = 0;
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from reader");  
            while(rs.next()){
                ++count;
            }
        }catch(Exception e){ System.out.println(e);}
        return count;
    }

    public void storeReader(Reader reader, String address, String phone_number, String password) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("INSERT INTO reader VALUES ("+reader.getID()+", \'"+reader.getUserName()+"\', \'"+password+"\')");
            stmt.executeUpdate("INSERT INTO reader_details VALUES ("+reader.getID()+", \'"+address+"\', \'"+phone_number+"\')");
        }catch(Exception e){ System.out.println(e);}
    }

    public ArrayList<Book> getBookList() {
        ArrayList<Book> book_list = new ArrayList<Book>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select book.id, book.author, book.title, book_count.availcount, book_count.ordercount from book inner join book_count on book.id = book_count.id"); 
            
            while(rs.next())  
                book_list.add(new Book(rs.getInt("book.id"), rs.getString("book.author"), rs.getString("book.title"), rs.getInt("book_count.availcount"), rs.getInt("book_count.ordercount")));
        }catch(Exception e){ System.out.println(e);}
        return book_list;
    }

    public void storeBook(Book book) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("INSERT INTO book VALUES ("+book.getID()+", \'"+book.getBookAuthor()+"\', \'"+book.getBookTitle()+"\')");
            stmt.executeUpdate("INSERT INTO book_count VALUES ("+book.getID()+", "+book.getAvailableCount()+", "+book.getOrderedCount()+")");
        }catch(Exception e){ System.out.println(e);}
    }

    public void deleteBook(int book_id) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("delete from book_count where id = "+book_id);
            stmt.executeUpdate("delete from book where id = "+book_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public void updateBook(Book book) {
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE book_count SET availcount = "+book.getAvailableCount()+" WHERE id ="+book.getID());
            if(!"".equals(book.getBookAuthor()))
                stmt.executeUpdate("UPDATE book SET author = "+book.getBookAuthor()+" WHERE id ="+book.getID());
            if(!"".equals(book.getBookTitle()))
                stmt.executeUpdate("UPDATE book SET title = "+book.getBookTitle()+" WHERE id ="+book.getID());
        }catch(Exception e){ System.out.println(e);}
    }

    public void decrementBookCount(int book_id) {
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE book_count SET availcount = availcount-1, ordercount = ordercount-1 WHERE id ="+book_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public void incrementBookCount(int book_id) {
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE book_count SET availcount = availcount+1, ordercount = ordercount-1 WHERE id ="+book_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public void storeCartItem(CartItem cart_item) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("INSERT INTO cart VALUES ("+cart_item.getUserID()+", "+cart_item.getBookID()+")");
        }catch(Exception e){ System.out.println(e);}
    }

    public ArrayList<CartItem> getCartList() {
        ArrayList<CartItem> cart = new ArrayList<CartItem>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from cart");  
            while(rs.next())  
                cart.add(new CartItem(rs.getInt("user_id"), rs.getInt("book_id")));
        }catch(Exception e){ System.out.println(e);}
        return cart;
    }

    public boolean isCartItemAvailable(int user_id, int book_id) {
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from cart");  
            while(rs.next())  {
                if(rs.getInt("user_id") == user_id && rs.getInt("book_id") == book_id)
                    return true;
            }
        }catch(Exception e){ System.out.println(e);}
        return false;
    }

    public void removeCartItem(int user_id, int book_id) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("delete from cart where user_id = "+user_id+" and book_id ="+book_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public ArrayList<OrderItem> getOrderList() {
        ArrayList<OrderItem> order_list = new ArrayList<OrderItem>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from book_order");  
            while(rs.next())  
                order_list.add(new OrderItem(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("book_id"), rs.getInt("status_id")));
        }catch(Exception e){ System.out.println(e);}
        return order_list;
    }

    public void storeOrderItem(OrderItem order_item) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("INSERT INTO book_order VALUES ("+order_item.getOrderID()+", "+order_item.getUserID()+", "+order_item.getBookID()+", "+order_item.getStatusID()+")");
        }catch(Exception e){ System.out.println(e);}
    }

    public ArrayList<DeliveryMan> getDeliveryManList() {
        ArrayList<DeliveryMan> delivery_man_list = new ArrayList<DeliveryMan>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from deliveryman");  
            while(rs.next())  
                delivery_man_list.add(new DeliveryMan(rs.getInt("id"), rs.getString("name")));
        }catch(Exception e){ System.out.println(e);}
        return delivery_man_list;
    }

    public String getDeliveryManPassword(String user_name) {
        String password = "";
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from deliveryman");  
            while(rs.next()){
                if(rs.getString("name").equals(user_name)){
                    password = rs.getString("password");
                    break;
                }
            }
        }catch(Exception e){ System.out.println(e);}
        return password;
    }

    public String getReaderPhoneNo(int user_id) {
        String phoneno = "";
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from reader_details");  
            while(rs.next()){
                if(rs.getInt("id") == user_id){
                    phoneno = rs.getString("phoneno");
                    break;
                }
            }
        }catch(Exception e){ System.out.println(e);}
        return phoneno;
    }

    public String getReaderAddress(int user_id) {
        String address = "";
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from reader_details");  
            while(rs.next()){
                if(rs.getInt("id") == user_id){
                    address = rs.getString("address");
                    break;
                }
            }
        }catch(Exception e){ System.out.println(e);}
        return address;
    }

    public void changeDeliveryStatus(int order_id, int status_id) {
        ArrayList<OrderItem> order_list = getOrderList();
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE book_order SET status_id = "+status_id+" WHERE id ="+order_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public String getStatusByID(int status_id) {
        String status = "";
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select process from status where id = "+status_id); 
            rs.next();
            status = rs.getString("process");
        }catch(Exception e){ System.out.println(e);}
        return status;
    }
}