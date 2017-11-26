package models;

import java.sql.Date;
import java.util.ArrayList;

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
    public int claimCount;
    //DbBean db = new DbBean();
    //Cuser us = new Cuser();
    //public ArrayList<claims> claim = new ArrayList<>();
    
    public claims(int id, String mem_id, Date date, String reason, 
            String status, double amount, int claimCount){
        this.id = id;
        this.mem_id = mem_id;
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.amount = amount;
        this.claimCount = claimCount;
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
    
    public int getClaimCount(){
        return claimCount;
    }
    @Override
    public String toString(){
        return "id= " + id + ", mem_id= " + mem_id + ", date= " + date + ", reason= " + reason + ", status= " + status + ", amount= " + amount +", " + claimCount;
    }
}
