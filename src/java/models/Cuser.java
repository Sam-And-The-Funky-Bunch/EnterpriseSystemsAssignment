package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Daniel Viner
 * Contributors: Jacob Williams,
 *               Esther Sully,
 *               Sam Scott,
 *               Michael Gregory.
 * Function: Handles the currently logged in users profile.
 */

public class Cuser {
    //VARIABLES
    public static String id;
    public static String name;
    public static String address;
    public static Date DoB;
    public static Date DoR;
    public static String userStat;
    public static double balance;
   
    
    //ID
    public String getID(){
        return id;
    }
    
    public void setID(String id){
        this.id = id;
    }
    
    
    //NAME
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    //ADDRESS
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    //DATE OF BIRTH
    public Date getDoB(){
        return DoB;   
    }
    
    public void setDoB(Date dob){
        this.DoB = dob;
    }
    
    //DATE OF REGISTRATION
    public Date getDoR(){
        return DoR;
    }
    
    public void setDoR(Date dor){
        this.DoR = dor;
    }
    
    //USER ACCOUNT STATUS
    public String getUserStat(){
        return userStat;
    }
    
    public void setUserStat(String newStat){
        this.userStat = newStat;
    }
    
    //BALANCE
    public void setBalance(double funds, String action){
        if(action.equals("ADD")){
            this.balance = balance + funds;
        }else if(action.equals("SUB")){
            this.balance = balance - funds;
        }else if(action.equals("SET")){
            this.balance = funds;
        }
    }
    
    public double getBalance(){
        return balance;
    }
    
    @Override
    public String toString(){
        return "id='" + id + "',\nname='" + name + "',\naddress" + address 
                + "',\nDoB='" + DoB + "',\nDoR='" + DoR + "',\nStatus='" 
                + userStat + "',\nBalance='" + balance + "'.";
    }
}
