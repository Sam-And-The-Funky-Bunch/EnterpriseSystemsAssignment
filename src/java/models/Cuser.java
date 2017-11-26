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
    public static String id;
    public static String name;
    public static String address;
    public static Date DoB;
    public static Date DoR;
    public static String userStat;
    public static double balance;
   
    public String getID(){
        return id;
    }
    
    public void setID(String id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public Date getDoB(){
        return DoB;   
    }
    
    public void setDoB(Date dob){
        this.DoB = dob;
    }
    
    public Date getDoR(){
        return DoR;
    }
    
    public void setDoR(Date dor){
        this.DoR = dor;
    }
    
    public String getUserStat(){
        return userStat;
    }
    
    public void setUserStat(String newStat){
        this.userStat = newStat;
    }
    
    public void setBalance(double funds, String action){
        if(action.equals("ADD")){
        this.balance = balance + funds;
        }else if(action.equals("SUB")){
            this.balance = balance - funds;
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
