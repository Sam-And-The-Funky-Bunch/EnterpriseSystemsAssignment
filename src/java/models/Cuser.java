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
    
    public void setUser(String uName){
        DbBean db = new DbBean();
        Connection con = db.getCon();
        String sql = "SELECT * FROM ROOT.MEMBERS WHERE members.\"id\"= '" + uName + "'";
        //SELECT * FROM ROOT.MEMBERS WHERE members."id" = 'e-simons';
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            this.id = rs.getString(1);
            this.name = rs.getString(2);
            this.address = rs.getString(3);
            this.DoB = rs.getDate(4);
            this.DoR = rs.getDate(5);
            this.userStat = rs.getString(6);
            this.balance = rs.getDouble(7);           
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addFunds(double funds){
        try {
            DbBean db = new DbBean();
            Connection con = db.getCon();
            String sql = "UPDATE ROOT.MEMBERS SET \"balance\" = " 
                    + (getBalance() + funds) + " WHERE \"id\" = '" + getID() 
                    + "'";
            System.out.println(sql);
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            setBalance(funds, "ADD");
        } catch (SQLException ex) {
            Logger.getLogger(Cuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public Date getDoB(){
        return DoB;   
    }
    
    public Date getDoR(){
        return DoR;
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
