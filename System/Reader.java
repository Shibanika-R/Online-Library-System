package System;

public class Reader extends User{
    String address;
    String phone_number;
    Reader(int id, String user_name, String address, String phone_number){
        super(id, user_name);
        this.address = address;
        this.phone_number = phone_number;
    }
    
    String getAddress(){
        return this.address;
    }
    
    String getPhoneNumber(){
        return this.phone_number;
    }
}