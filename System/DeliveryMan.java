package System;

public class DeliveryMan {
    private int delivery_man_id;
    private String user_name;
    
    DeliveryMan(int delivery_man_id, String user_name){
        this.delivery_man_id = delivery_man_id;
        this.user_name = user_name;
    }
    
    int getID(){
        return this.delivery_man_id;
    }
    
    String getUserName(){
        return this.user_name;
    }
}