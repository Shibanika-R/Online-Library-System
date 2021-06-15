package System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database implements DatabaseInterface{
    
    private static Database instance_Ref;
    private Database(){}
    
    public static Database newInstance(){
        if(instance_Ref == null)
            instance_Ref = new Database();
        return instance_Ref;
    }
    
    public ArrayList<Librarian> getLibrarianList(){
        ArrayList<Librarian> librarian_list = new ArrayList<Librarian>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("librarian.txt"));
            while(true) {
                String id;
                String user_name;
                
                if(null == (id = br.readLine()) ) break;
                user_name = br.readLine(); 
                br.readLine();
                
                librarian_list.add(new Librarian(Integer.parseInt(id), user_name));
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return librarian_list;
    }
    
    public ArrayList<Reader> getReaderList(){
        ArrayList<Reader> reader_list = new ArrayList<Reader>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("reader.txt"));
            while(true) {
                String id;
                String user_name;
                
                if(null == (id = br.readLine()) ) break;
                user_name = br.readLine(); 
                br.readLine();
                
                reader_list.add(new Reader(Integer.parseInt(id), user_name));
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return reader_list;
    }
    
    public String getReaderPhoneNo(int user_id){
        String phone_no="";
        String id;
        try {
            BufferedReader br = new BufferedReader(new FileReader("reader_details.txt"));
            while(true) {
                
                if(null == (id = br.readLine())) break;
                if(user_id == Integer.parseInt(id)){
                    br.readLine();
                    phone_no = br.readLine();
                    break;
                }
                br.readLine();
                br.readLine();
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return phone_no;
    }
    
    public String getReaderAddress(int user_id){
        String address="";
        String id;
        try {
            BufferedReader br = new BufferedReader(new FileReader("reader_details.txt"));
            while(true) {
                
                if(null == (id = br.readLine())) break;
                if(user_id == Integer.parseInt(id)){
                    address = br.readLine();
                    break;
                }
                br.readLine();
                br.readLine();
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return address;
    }
    
    public String getReaderPassword(String user_name){
        String password="";
        try {
            BufferedReader br = new BufferedReader(new FileReader("reader.txt"));
            while(true) {
                
                if(null == br.readLine() ) break;
                if(user_name.equals(br.readLine())){
                    password = br.readLine();
                    break;
                } 
                br.readLine();
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return password;
    }
    
    public String getLibrarianPassword(String user_name){
        String password="";
        try {
            BufferedReader br = new BufferedReader(new FileReader("librarian.txt"));
            while(true){
                
                if(null == br.readLine() ) break;
                if(user_name.equals(br.readLine())){
                    password = br.readLine();
                    break;
                } 
                br.readLine();
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return password;
    }
    
    public int getTotalNumberOfReader(){
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("reader.txt"));
            while(br.readLine() != null) {
                count++;
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return count/3;
    }
    
    public ArrayList<Book> getBookList(){
        
        ArrayList<Book> book_list = new ArrayList<Book>();
        
        try {
            BufferedReader br1 = new BufferedReader(new FileReader("book.txt"));
            BufferedReader br2 = new BufferedReader(new FileReader("book_count.txt"));
            while(true) {
                String id;
                String book_author;
                String book_title;
                String available_count;
                String ordered_count;
                
                if(null == (id = br1.readLine()) ) break;
                book_author = br1.readLine();
                book_title = br1.readLine();
                br2.readLine();
                available_count = br2.readLine();
                ordered_count = br2.readLine();
                
                book_list.add(new Book(Integer.parseInt(id), book_author, book_title, Integer.parseInt(available_count), Integer.parseInt(ordered_count)));
            }
            br1.close();
            br2.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return book_list;
    }
    
    public void storeReader(Reader reader, String address, String phone_number ,String password){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("reader.txt", true));
            bw.write(Integer.toString(reader.getID())+"\n"+reader.getUserName()+"\n"+password+"\n");
            bw.close();
            bw = new BufferedWriter(new FileWriter("reader_details.txt", true));
            bw.write(Integer.toString(reader.getID())+"\n"+address+"\n"+phone_number+"\n");
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public void storeBook(Book book){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("book.txt", true));
            bw.write(Integer.toString(book.getID())+"\n"+book.getBookAuthor()+"\n"+book.getBookTitle()+"\n");
            bw.close();
            bw = new BufferedWriter(new FileWriter("book_count.txt", true));
            bw.write(Integer.toString(book.getID())+"\n"+Integer.toString(book.getAvailableCount())+"\n"+Integer.toString(book.getOrderedCount())+"\n");
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public Book getBookByID(int book_id){
        ArrayList<Book> book_list = getBookList();
        for(Book book: book_list){
            if(book.getID() == book_id){
                return book;
            }
        }
        return new Book(0, null, null, -1, -1);
    }
    
    public void deleteBook(int book_id){
        ArrayList<Book> book_list = getBookList();
        Book book = getBookByID(book_id);
        int index = -1;
        for(Book b : book_list){
            index++;
            if(b.getID() == book.getID())
                break;
        }
        book_list.remove(index);
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter("book.txt"));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("book_count.txt"));
            for(Book b: book_list){
                bw1.write(Integer.toString(b.getID())+"\n"+b.getBookAuthor()+"\n"+b.getBookTitle()+"\n");
                bw2.write(Integer.toString(b.getID())+"\n"+Integer.toString(b.getAvailableCount())+"\n"+Integer.toString(b.getOrderedCount())+"\n");
            }
            bw1.close();
            bw2.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public void decrementBookCount(int book_id){
        ArrayList<Book> book_list = getBookList();
        Book book = getBookByID(book_id);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("book_count.txt"));
            for(Book b: book_list){
                if(b.getID() == book_id)
                    bw.write(Integer.toString(b.getID())+"\n"+Integer.toString(b.getAvailableCount()-1)+"\n"+Integer.toString(b.getOrderedCount()+1)+"\n");
                else
                    bw.write(Integer.toString(b.getID())+"\n"+Integer.toString(b.getAvailableCount())+"\n"+Integer.toString(b.getOrderedCount())+"\n");
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public void incrementBookCount(int book_id){
        ArrayList<Book> book_list = getBookList();
        Book book = getBookByID(book_id);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("book_count.txt"));
            for(Book b: book_list){
                if(b.getID() == book_id)
                    bw.write(Integer.toString(b.getID())+"\n"+Integer.toString(b.getAvailableCount()+1)+"\n"+Integer.toString(b.getOrderedCount()-1)+"\n");
                else
                    bw.write(Integer.toString(b.getID())+"\n"+Integer.toString(b.getAvailableCount())+"\n"+Integer.toString(b.getOrderedCount())+"\n");
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public void updateBook(Book book_new){
        ArrayList<Book> book_list = getBookList();
        Book book_old = getBookByID(book_new.getID());
        int index = -1;
        book_new.setOrderedCount(book_old.getOrderedCount());
        if("".equals(book_new.getBookAuthor())){
            book_new.setBookAuthor(book_old.getBookAuthor());
        }
        if("".equals(book_new.getBookTitle())){
            book_new.setBookTitle(book_old.getBookTitle());
        }
        for(Book b : book_list){
            ++index;
            if(b.getID() == book_old.getID()){
                break;
            }
        }
        book_list.set(index, book_new);
        try {
            BufferedWriter bw1 = new BufferedWriter(new FileWriter("book.txt"));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("book_count.txt"));
            for(Book b: book_list){
                bw1.write(Integer.toString(b.getID())+"\n"+b.getBookAuthor()+"\n"+b.getBookTitle()+"\n");
                bw2.write(Integer.toString(b.getID())+"\n"+Integer.toString(b.getAvailableCount())+"\n"+Integer.toString(b.getOrderedCount())+"\n");
            }
            bw1.close();
            bw2.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public void storeCartItem(CartItem cart_item){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("cart.txt", true));
            bw.write(Integer.toString(cart_item.getUserID())+"\n"+Integer.toString(cart_item.getBookID())+"\n");
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public ArrayList<CartItem> getCartList(){
        ArrayList<CartItem> cart = new ArrayList<CartItem>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("cart.txt"));
            
            while(true) {
                String user_id;
                String book_id;
                                
                if(null == (user_id = br.readLine()) ) break;
                book_id = br.readLine();
                
                cart.add(new CartItem(Integer.parseInt(user_id), Integer.parseInt(book_id)));
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return cart;
    }
    
    public boolean isCartItemAvailable(int user_id, int book_id){
        ArrayList<CartItem> cart = getCartList();
        for(CartItem item : cart){
            if(item.getUserID() == user_id && item.getBookID() == book_id){
                return true;
            }
        }
        return false;
    }
    
    public void removeCartItem(int user_id, int book_id){
        ArrayList<CartItem> cart = getCartList();
        int index = -1;
        for(CartItem item : cart){
            index++;
            if(item.getUserID() == user_id && item.getBookID() == book_id)
                break;
        }
        cart.remove(index);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("cart.txt"));
            for(CartItem item : cart){
                bw.write(Integer.toString(item.getUserID())+"\n"+Integer.toString(item.getBookID())+"\n");
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public ArrayList<OrderItem> getOrderList(){
        ArrayList<OrderItem> order_list = new ArrayList<OrderItem>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("order.txt"));
            
            while(true) {
                String order_id;
                String user_id;
                String book_id;
                String status_id;
                
                if(null == (order_id = br.readLine())) break;
                user_id = br.readLine();
                book_id = br.readLine();
                status_id = br.readLine();
                order_list.add(new OrderItem(Integer.parseInt(order_id), Integer.parseInt(user_id), Integer.parseInt(book_id), Integer.parseInt(status_id)));
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return order_list;
    }
    
    public void storeOrderItem(OrderItem order_item){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("order.txt", true));
            bw.write(Integer.toString(order_item.getOrderID())+"\n"+Integer.toString(order_item.getUserID())+"\n"+Integer.toString(order_item.getBookID())+"\n"+Integer.toString(order_item.getStatusID())+"\n");
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    
    public ArrayList<DeliveryMan> getDeliveryManList(){
        ArrayList<DeliveryMan> delivery_man_list = new ArrayList<DeliveryMan>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("deliveryman.txt"));
            while(true) {
                String id;
                String user_name;
                
                if(null == (id = br.readLine()) ) break;
                user_name = br.readLine(); 
                br.readLine();
                
                delivery_man_list.add(new DeliveryMan(Integer.parseInt(id), user_name));
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return delivery_man_list;
    }
    
    public String getDeliveryManPassword(String user_name){
        String password="";
        try {
            BufferedReader br = new BufferedReader(new FileReader("deliveryman.txt"));
            while(true){
                
                if(null == br.readLine() ) break;
                if(user_name.equals(br.readLine())){
                    password = br.readLine();
                    break;
                } 
                br.readLine();
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return password;
    }
    
    public void changeDeliveryStatus(int order_id, int status_id){
        ArrayList<OrderItem> order_list = getOrderList();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("order.txt"));
            for(OrderItem item : order_list){
                if(item.getOrderID() == order_id)
                    bw.write(Integer.toString(item.getOrderID())+"\n"+Integer.toString(item.getUserID())+"\n"+Integer.toString(item.getBookID())+"\n"+Integer.toString(status_id)+"\n");
                else
                    bw.write(Integer.toString(item.getOrderID())+"\n"+Integer.toString(item.getUserID())+"\n"+Integer.toString(item.getBookID())+"\n"+Integer.toString(item.getStatusID())+"\n");
            }
            bw.close();
        }
        catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }
    public String getStatusByID(int status_id){
        String status = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("status.txt"));
            while(true){
                String id;
                if(null == (id = br.readLine())) break;
                if(Integer.parseInt(id) == status_id){
                    status = br.readLine();
                    break;
                }
                br.readLine();
            }
            br.close();
        } catch (IOException ex) {
                System.out.println("IOError");
        }
        return status;
    }
}