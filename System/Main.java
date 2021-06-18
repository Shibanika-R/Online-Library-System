package System;

import java.io.Console;

enum MainMenu{
    CREATE_ACCOUNT, LOGIN_AS_READER, LOGIN_AS_LIBRARIAN, LOGIN_AS_DELIVERYMAN, EXIT_SYSTEM
}

enum ReaderUser{
    DISPLAY_BOOK, VIEW_CART, ADD_TO_CART, REMOVE_FROM_CART, PLACE_ORDER, DISPLAY_ORDER, RETURN_REQUEST, LOGOUT
}

enum LibrarianUser{
    DISPLAY_BOOK, ADD_BOOK, REMOVE_BOOK, MODIFY_BOOK, DISPLAY_USER_ORDER, LOGOUT
}

enum DeliveryManUser{
    DELIVER_BOOK_DETAILS, RETURN_BOOK_DETAILS, DELIVER_DETAILS_UPDATE, RETURN_DETAILS_UPDATE, LOGOUT
}


public class Main {
    
    static Console console=System.console();

    public static void main(String[] args){
        
        int choice = -1;
        
        System.out.println("\t\t\t\t\tWelcome To Online Library System\n");
        
        while(true){
            
            System.out.println("\n\t\t\tCreate Account      - Enter 1");
            System.out.println("\t\t\tLogin Reader        - Enter 2");
            System.out.println("\t\t\tLogin Librarian     - Enter 3");
            System.out.println("\t\t\tLogin Delivery Man  - Enter 4");
            System.out.println("\t\t\tExit the System     - Enter 5");
            
            System.out.print("\t\t\tEnter your choice: ");
            
            try{
                choice = Integer.parseInt(console.readLine());
                if(choice <= 0 || choice >= 6){
                    Exception exception = new Exception();
                    throw exception;
                }
            }catch(Exception e){
                System.out.println("\t\t\tPlease enter valid choice value in numbers:(\n");
                continue;
            }
            
            MainMenu main_menu = MainMenu.values()[choice-1];
            switch(main_menu){
                
                case CREATE_ACCOUNT:
                    SignUp signup = new SignUp();
                    signup.getUserDetails();
                    break;
                
                case LOGIN_AS_READER:
                    Login login = new Login();
                    if(login.authenticateReader()){
                        readerFunctionality(login);
                    }
                    break;
                
                case LOGIN_AS_LIBRARIAN:
                    login = new Login();
                    if(login.authenticateLibrarian()){
                        librarianFunctionality(login);
                    }
                    break;
                
                case LOGIN_AS_DELIVERYMAN:
                    login = new Login();
                    if(login.authenticateDeliveryMan()){
                        deliveryManFunctionality(login);
                    }
                    break;
                    
                case EXIT_SYSTEM:
                    System.out.println("\n\t\t\t\tThank You for using Online Library System");
                    break;
            }
            
            if(main_menu == MainMenu.EXIT_SYSTEM){
                break;
            }
        }
    }
    
    static void readerFunctionality(Login login){
        int choice;
        
        System.out.println("\n\t\t\t\t\tWelcome To Reader Portal\n");
        
        while(true){
            
            System.out.println("\n\t\t\tDisplay Book Details            - Enter 1");
            System.out.println("\t\t\tView Cart                       - Enter 2");
            System.out.println("\t\t\tAdd Book to Cart                - Enter 3");
            System.out.println("\t\t\tRemove Book from Cart           - Enter 4");
            System.out.println("\t\t\tPlace Order                     - Enter 5");
            System.out.println("\t\t\tDisplay Order Details           - Enter 6");
            System.out.println("\t\t\tBook Return Request             - Enter 7");
            System.out.println("\t\t\tLogout                          - Enter 8");
            System.out.print("\t\t\tEnter your choice: ");
            
            try{
                choice = Integer.parseInt(console.readLine());
                if(choice <= 0 || choice >= 9){
                    Exception exception = new Exception();
                    throw exception;
                }
            }catch(Exception e){
                System.out.println("\t\t\tPlease enter valid choice value in numbers:(\n");
                continue;
            }
            
            ReaderUser reader_choice = ReaderUser.values()[choice-1];
            switch(reader_choice){
                
                case DISPLAY_BOOK:
                    BookOperations.displayAvailableBook(login.user_ID);
                    break;
                
                case VIEW_CART:
                    Cart.displayCart(login.user_ID);
                    break;
                    
                case ADD_TO_CART:
                    Cart.addToCart(login.user_ID);
                    break;
                
                case REMOVE_FROM_CART:
                    Cart.removeFromCart(login.user_ID);
                    break;
                
                case PLACE_ORDER:
                    Order.placeOrder(login.user_ID);
                    break;
                
                case DISPLAY_ORDER:
                    Order.displayOrderDetails(login.user_ID);
                    break;
                
                case RETURN_REQUEST:
                    DeliveryOperations.requestReturn(login.user_ID);
                    break;
                case LOGOUT:
                    login.user_name = null;
                    login.password = null;
                    break;
            }
            
            if(reader_choice == ReaderUser.LOGOUT){
                break;
            }
        }
    }

    static void librarianFunctionality(Login login){
        int choice = -1;
        
        System.out.println("\n\t\t\t\t\tWelcome To Librarian Portal\n");
        
        while(true){
            
            System.out.println("\n\t\t\tDisplay Book Details            - Enter 1");
            System.out.println("\t\t\tAdd Book                        - Enter 2");
            System.out.println("\t\t\tRemove Book                     - Enter 3");
            System.out.println("\t\t\tModify Book Details             - Enter 4");
            System.out.println("\t\t\tUsers Order Details             - Enter 5");
            System.out.println("\t\t\tLogout                          - Enter 6");
            System.out.print("\t\t\tEnter your choice: ");
            
            try{
                choice = Integer.parseInt(console.readLine());
                if(choice <= 0 || choice >= 7){
                    Exception exception = new Exception();
                    throw exception;
                }
            }catch(Exception e){
                System.out.println("\t\t\tPlease enter valid choice value in numbers:(\n");
                continue;
            }
            
            LibrarianUser librarian_choice = LibrarianUser.values()[choice-1];
            switch(librarian_choice){
                
                case DISPLAY_BOOK:
                    BookOperations.displayBookDetails();
                    break;
                    
                case ADD_BOOK:
                    BookOperations.addBookDetails();
                    break;
                    
                case REMOVE_BOOK:
                    BookOperations.removeBookDetails();
                    break;
                
                case MODIFY_BOOK:
                    BookOperations.modifyBookDetails();
                    break;
                
                case DISPLAY_USER_ORDER:
                    Order.displayAllUserOrderDetails();
                    break;
                    
                case LOGOUT:
                    login.user_name = null;
                    login.password = null;
                    break;
            }
            
            if(librarian_choice == LibrarianUser.LOGOUT){
                break;
            }
        }
    }

    static void deliveryManFunctionality(Login login){
        int choice = -1;
        
        System.out.println("\n\t\t\t\t\tWelcome To Delivery Man Portal\n");
        
        while(true){
            
            System.out.println("\n\t\t\tDeliver Book Details      - Enter 1");
            System.out.println("\t\t\tReturn Book Details       - Enter 2");
            System.out.println("\t\t\tDelivery Status Update    - Enter 3");
            System.out.println("\t\t\tReturn Status Update      - Enter 4");
            System.out.println("\t\t\tLogout                    - Enter 5");
            System.out.print("\t\t\tEnter your choice: ");
            
            try{
                choice = Integer.parseInt(console.readLine());
                if(choice <= 0 || choice >= 6){
                    Exception exception = new Exception();
                    throw exception;
                }
            }catch(Exception e){
                System.out.println("\t\t\tPlease enter valid choice value in numbers:(\n");
                continue;
            }
            
            DeliveryManUser delivery_man_choice = DeliveryManUser.values()[choice-1];
            switch(delivery_man_choice){
                
                case DELIVER_BOOK_DETAILS:
                    DeliveryOperations.displayDeliverList();
                    break;
                    
                case RETURN_BOOK_DETAILS:
                    DeliveryOperations.displayReturnList();
                    break;
                
                case DELIVER_DETAILS_UPDATE:
                    DeliveryOperations.setDeliverStatus();
                    break;
                
                case RETURN_DETAILS_UPDATE:
                    DeliveryOperations.setReturnStatus();
                    break;
                    
                case LOGOUT:
                    login.user_name = null;
                    login.password = null;
                    break;
            }
            
            if(delivery_man_choice == DeliveryManUser.LOGOUT){
                break;
            }
        }
    }
}