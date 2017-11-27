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
    
    //VARIABLES
    public int id;
    public String mem_id;
    public Date date;
    public String reason;
    public String status;
    public double amount;
    public int claimCount;
    
    //CONSTRUCTOR
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
    
    //CLAIM ID
    public int getID(){
        return id;
    }
    
    //MEMBER ID
    public String getMem_id(){
        return mem_id;
    }
    
    //DATE OF CLAIM
    public Date getDate(){
        return date;
    }
    
    //REASON FOR CLAIM
    public String getReason(){
        return reason;
    }
    
    //STATUS OF CLAIM
    public String getStatus(){
        return status;
    }
    
    //CLAIM COST
    public double getAmount(){
        return amount;
    }
    
    //AMOUNT OF USER SPECIFIC CLAIMS
    public int getClaimCount(){
        return claimCount;
    }
    
    //TOSTRING
    @Override
    public String toString(){
        return "id= " + id + ", mem_id= " + mem_id + ", date= " + date + ", reason= " + reason + ", status= " + status + ", amount= " + amount +", " + claimCount;
    }
}
