package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author: Michael Gregory
 * Contributors: Jacob Williams,
 *               Daniel Viner,
 *               Esther Sully,
 *               Sam Scott.
 */
public class claims {
    
    public int id;
    public String mem_id;
    public Date date;
    public String reason;
    public String status;
    public double amount;
    DbBean db = new DbBean();
    Cuser us = new Cuser();
    public ArrayList<claims> claim = new ArrayList<>();
    
    public claims(int id, String mem_id, Date date, String reason, 
            String status, double amount){
        this.id = id;
        this.mem_id = mem_id;
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.amount = amount;
    }
    
    public int getID(){
        return id;
    }
    
    public String getMem_id(){
        return mem_id;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getReason(){
        return reason;
    }
    
    public String getStatus(){
        return status;
    }
    
    public double getAmount(){
        return amount;
    }
    
    @Override
    public String toString(){
        return "id= " + id + ", mem_id= " + mem_id + ", date= " + date + ", reason= " + reason + ", status= " + status + ", amount= " + amount +".";
    }
}
