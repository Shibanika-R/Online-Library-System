package System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
            ResultSet rs=stmt.executeQuery("select id, name from user where role = 'librarian'");  
            while(rs.next())  
                librarian_list.add(new Librarian(rs.getInt("id"), rs.getString("name")));
        }catch(Exception e){ System.out.println(e);}
        return librarian_list;
    }

    public ArrayList<Reader> getReaderList() {
        ArrayList<Reader> reader_list = new ArrayList<Reader>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from user where role = 'reader'");  
            while(rs.next())  
                reader_list.add(new Reader(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("phoneno")));
        }catch(Exception e){ System.out.println(e);}
        return reader_list;
    }
    
    public ArrayList<DeliveryMan> getDeliveryManList() {
        ArrayList<DeliveryMan> delivery_man_list = new ArrayList<DeliveryMan>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select id, name from user where role = 'deliveryman'");  
            while(rs.next())  
                delivery_man_list.add(new DeliveryMan(rs.getInt("id"), rs.getString("name")));
        }catch(Exception e){ System.out.println(e);}
        return delivery_man_list;
    }
    
    public String getUserPassword(String user_name) {
        String password = "";
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select name, password from user where name = \'"+user_name+"\'");  
            while(rs.next()){
                if(rs.getString("name").equals(user_name)){
                    password = rs.getString("password");
                    break;
                }
            }
        }catch(Exception e){ System.out.println(e);}
        return password;
    }
    
    public int getTotalNumberOfUser() {
        int count = 0;
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select count(id) from user");  
            if(rs.next()) count = rs.getInt(1);
        }catch(Exception e){ System.out.println(e);}
        return count;
    }

    public void storeReader(Reader reader, String password) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("INSERT INTO user VALUES ("+reader.getID()+", '"+reader.getUserName()+"', '"+password+"', 'reader', '"+reader.address+"','"+reader.phone_number+"')");
        }catch(Exception e){ System.out.println(e);}
    }

    public ArrayList<Book> getBookList() {
        ArrayList<Book> book_list = new ArrayList<Book>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select id, author, title, available_count, ordered_count from book"); 
            
            while(rs.next())  
                book_list.add(new Book(rs.getInt("id"), rs.getString("author"), rs.getString("title"), rs.getInt("available_count"), rs.getInt("ordered_count")));
        }catch(Exception e){ System.out.println(e);}
        return book_list;
    }

    public void storeBook(Book book) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("INSERT INTO book VALUES ("+book.getID()+", \'"+book.getBookAuthor()+"\', \'"+book.getBookTitle()+"\', "+book.getAvailableCount()+", "+book.getOrderedCount()+")");
            for(int i = 0; i < book.getAvailableCount(); i++)
            stmt.executeUpdate("INSERT INTO book_copies VALUES ('"+generateBookCopiesID()+"', '"+book.getID()+"', 'Available')");
            
        }catch(Exception e){ System.out.println(e);}
    }
    
    public static long generateBookCopiesID(){
        Random rnd = new Random();
        char [] digits = new char[10];
        digits[0] = (char) (rnd.nextInt(9) + '1');
        for(int i=1; i<digits.length; i++) {
            digits[i] = (char) (rnd.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }
    
    public void deleteBook(int book_id) {
        try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate("update book set status = \"Inactive\", available_count = 0 where id = "+book_id+" and ordered_count = 0");
            stmt.executeUpdate("delete from book_copies where book_id = "+book_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public void updateBook(Book book) {
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE book SET available_count = "+book.getAvailableCount()+" WHERE id ="+book.getID());
            if(!"".equals(book.getBookAuthor()))
                stmt.executeUpdate("UPDATE book SET author = '"+book.getBookAuthor()+"' WHERE id ="+book.getID());
            if(!"".equals(book.getBookTitle()))
                stmt.executeUpdate("UPDATE book SET title = '"+book.getBookTitle()+"' WHERE id ="+book.getID());
        }catch(Exception e){ System.out.println(e);}
    }
    
    public ArrayList<Book> getBooksByTitle(String title){
        ArrayList<Book> book_list = new ArrayList<Book>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select id, author, title, available_count, ordered_count from book where title like '%"+title+"%'"); 
            
            while(rs.next())  
                book_list.add(new Book(rs.getInt("id"), rs.getString("author"), rs.getString("title"), rs.getInt("available_count"), rs.getInt("ordered_count")));
        }catch(Exception e){ System.out.println(e);}
        return book_list;
    }
    
    public ArrayList<Book> getBooksByAuthor(String author){
        ArrayList<Book> book_list = new ArrayList<Book>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select id, author, title, available_count, ordered_count from book where author like '%"+author+"%'"); 
            
            while(rs.next())  
                book_list.add(new Book(rs.getInt("id"), rs.getString("author"), rs.getString("title"), rs.getInt("available_count"), rs.getInt("ordered_count")));
        }catch(Exception e){ System.out.println(e);}
        return book_list;
    }
    
    public void decrementBookCount(int book_id) {
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE book SET available_count = available_count-1, ordered_count = ordered_count+1 WHERE id ="+book_id);
            
        }catch(Exception e){ System.out.println(e);}
    }

    public void incrementBookCount(int book_id) {
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE book SET available_count = available_count+1, ordered_count = ordered_count-1 WHERE id ="+book_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public void storeCartItem(CartItem cart_item) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("INSERT INTO cart VALUES ("+cart_item.getUser().getID()+", "+cart_item.getBook().getID()+")");
        }catch(Exception e){ System.out.println(e);}
    }

    public ArrayList<CartItem> getCartList(int user_id) {
        ArrayList<CartItem> cart = new ArrayList<CartItem>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from cart where user_id = "+user_id);  
            while(rs.next())  
                cart.add(new CartItem(getReaderByID(rs.getInt("user_id")), getBookByID(rs.getInt("book_id"))));
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

    public void removeCartItem(CartItem cart_item) {
        try{
            Statement stmt=con.createStatement(); 
            stmt.executeUpdate("delete from cart where user_id = "+cart_item.getUser().getID()+" and book_id ="+cart_item.getBook().getID());
        }catch(Exception e){ System.out.println(e);}
    }

    public ArrayList<OrderItem> getOrderList() {
        ArrayList<OrderItem> order_list = new ArrayList<OrderItem>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from orders");  
            while(rs.next())  {
                OrderItem order = new OrderItem(rs.getInt("id"), rs.getString("copies_id"),  getReaderByID(rs.getInt("user_id")), getBookByID(rs.getInt("book_id")), rs.getInt("status_id"));
                if(order.getStatusID() != 1)  order.setDueDate(rs.getDate("due_date").toString());
                else order.setDueDate("Not Set  ");
                order_list.add(order);
            }
            order_list = calculateFine(order_list);
        }catch(Exception e){ System.out.println(e);}
        return order_list;
    }
    
    public ArrayList<OrderItem> getOrderList(int user_id){
        ArrayList<OrderItem> order_list = new ArrayList<OrderItem>();
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from orders where user_id = "+user_id);  
            while(rs.next())  {
                OrderItem order = new OrderItem(rs.getInt("id"), rs.getString("copies_id"),  getReaderByID(rs.getInt("user_id")), getBookByID(rs.getInt("book_id")), rs.getInt("status_id"));
                if(order.getStatusID() != 1)  order.setDueDate(rs.getDate("due_date").toString());
                else order.setDueDate("Not Set  ");
                order_list.add(order);
            }
            order_list = calculateFine(order_list);
        }catch(Exception e){ System.out.println(e);}
        return order_list;
    }
    
    public Reader getReaderByID(int user_id){
        try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from user where role = 'reader' and user.id = "+user_id);  
            if(rs.next())
                return new Reader(user_id, rs.getString("name"), rs.getString("address"), rs.getString("phoneno"));
        }catch(Exception e){ System.out.println(e);}
        return new Reader(-1, null, null, null);
    }
    
    public void storeOrderItem(ArrayList<OrderItem> order_list) {
        try{
            Statement stmt=con.createStatement(); 
            for(OrderItem order_item : order_list){
                stmt.executeUpdate("INSERT INTO orders(id, user_id, book_id, copies_id, status_id) VALUES ("+order_item.getOrderID()+", "+order_item.getUser().getID()+", "+order_item.getBook().getID()+", '"+order_item.getBookCopyID()+"',"+order_item.getStatusID()+")");
                decrementBookCount(order_item.getBook().getID());
                stmt.executeUpdate("UPDATE book_copies SET availability_status = 'Not Available' WHERE copies_id ='"+order_item.getBookCopyID()+"'");
            }
        }catch(Exception e){ System.out.println(e);}
    }
    
    public HashMap<Integer, String> getBookCopyIDList(ArrayList<CartItem> cart){
        HashMap<Integer, String> book_copy_list = new HashMap<>();
        try{
            Statement stmt=con.createStatement();  
            for(CartItem item : cart){
                ResultSet rs=stmt.executeQuery("select * from book_copies where book_id = "+item.getBook().getID()+" and availability_status = 'available' LIMIT 1");
                if(rs.next())
                    book_copy_list.put(rs.getInt("book_id"), rs.getString("copies_id"));
            }
        }catch(Exception e){ System.out.println(e);}
        return book_copy_list;
    }
    
    public void changeDeliveryStatus(int order_id, int status_id) {
        ArrayList<OrderItem> order_list = getOrderList();
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE orders SET status_id = "+status_id+" WHERE id ="+order_id);
        }catch(Exception e){ System.out.println(e);}
    }

    public String getStatusByID(int status_id) {
        String status = "";
        if(status_id == 1) status = "Ordered ";
        else if(status_id == 2) status = "Delivered";
        else if(status_id == 3) status = "Return Request";
        else if(status_id == 4) status = "Returned";
        return status;
    }
    
    public Book getBookByID(int book_id){
       Book book;
       try{
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from book where id = "+book_id);  
            if(rs.next())
                return new Book(rs.getInt("id"), rs.getString("author"), rs.getString("title"), rs.getInt("available_count"), rs.getInt("ordered_count"));
        }catch(Exception e){ System.out.println(e);}
        return new Book(-1, null, null, -1, -1);
    }
    
    public void returnBookUpdate(int order_id){
        ArrayList<OrderItem> order_list = getOrderList();
        try{
            Statement stmt=con.createStatement();  
            for(OrderItem item : order_list){
                if(item.getOrderID() == order_id){
                    incrementBookCount(item.getBook().getID());
                    stmt.executeUpdate("UPDATE book_copies SET availability_status = 'Available' WHERE copies_id ='"+item.getBookCopyID()+"'");
                }
            }
        }catch(Exception e){ System.out.println(e);}       
    }
    
    public void updateOrderDate(int order_id){
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE orders SET order_date = (select Current_Date), due_date = (select Current_Date + interval 15 day) WHERE id ="+order_id);
        }catch(Exception e){ System.out.println(e);}
    }
    
    public void updateReturnDate(int order_id){
        try{
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("UPDATE orders SET return_date = (select Current_Date) WHERE id ="+order_id);
        }catch(Exception e){ System.out.println(e);}
    }
    
    public ArrayList<OrderItem> calculateFine(ArrayList<OrderItem> order_list){
        try{
            Statement stmt=con.createStatement();
            for(OrderItem order : order_list){
                double fine = 0.0;
                ResultSet rs=stmt.executeQuery("select (CASE WHEN return_date is not NULL and due_date <= return_date THEN datediff(return_date,due_date)*10.0 WHEN return_date is NULL and due_date <= (select current_date) THEN datediff(current_date, due_date)*10.0 ELSE 0.0 END)as fine from orders where id ="+ order.getOrderID());
                if(rs.next())
                fine = rs.getDouble("fine");
                order.setFine(fine);
            }
        }catch(Exception e){ System.out.println(e);}
        return order_list;
    }
}