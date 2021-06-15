package System;

import java.io.Console;

public class Main {
    
    static Console console=System.console();

    public static void main(String[] args){
        
        int choice = -1;
        
        System.out.println("\t\t\t\t\tWelcome To Online Library System\n");
        
        while(choice != 5){
            
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
            
            
            switch(choice){
                
                case 1:
                    SignUp signup = new SignUp();
                    signup.getUserDetails();
                    break;
                
                case 2:
                    Login login = new Login();
                    if(login.authenticateReader()){
                        readerFunctionality(login);
                    }
                    break;
                
                case 3:
                    login = new Login();
                    if(login.authenticateLibrarian()){
                        librarianFunctionality(login);
                    }
                    break;
                
                case 4:
                    login = new Login();
                    if(login.authenticateDeliveryMan()){
                        deliveryManFunctionality(login);
                    }
                    break;
                    
                case 5:
                    System.out.println("\n\t\t\t\tThank You for using Online Library System");
                    break;
            }
        }
    }
    
    static void readerFunctionality(Login login){
        int choice = -1;
        
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
            }
            
            switch(choice){
                
                case 1:
                    BookOperations.displayAvailableBook(login.user_ID);
                    break;
                
                case 2:
                    Cart.displayCart(login.user_ID);
                    break;
                    
                case 3:
                    Cart.addToCart(login.user_ID);
                    break;
                
                case 4:
                    Cart.removeFromCart(login.user_ID);
                    break;
                
                case 5:
                    Order.placeOrder(login.user_ID);
                    break;
                
                case 6:
                    Order.displayOrderDetails(login.user_ID);
                    break;
                
                case 7:
                    DeliveryOperations.requestReturn(login.user_ID);
                    break;
                case 8:
                    login.user_name = null;
                    login.password = null;
                    break;
            }
            
            if(choice == 8){
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
            }
            
            switch(choice){
                
                case 1:
                    BookOperations.displayBookDetails();
                    break;
                    
                case 2:
                    BookOperations.addBookDetails();
                    break;
                    
                case 3:
                    BookOperations.removeBookDetails();
                    break;
                
                case 4:
                    BookOperations.modifyBookDetails();
                    break;
                
                case 5:
                    Order.displayAllUserOrderDetails();
                    break;
                    
                case 6:
                    login.user_name = null;
                    login.password = null;
                    break;
            }
            
            if(choice == 6){
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
            }
            
            switch(choice){
                
                case 1:
                    DeliveryOperations.displayDeliverList();
                    break;
                    
                case 2:
                    DeliveryOperations.displayReturnList();
                    break;
                
                case 3:
                    DeliveryOperations.setDeliverStatus();
                    break;
                
                case 4:
                    DeliveryOperations.setReturnStatus();
                    break;
                    
                case 5:
                    login.user_name = null;
                    login.password = null;
                    break;
            }
            
            if(choice == 5){
                break;
            }
        }
    }
}