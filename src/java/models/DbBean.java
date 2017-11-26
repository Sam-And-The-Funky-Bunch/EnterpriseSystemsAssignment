package models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Author: Jacob Williams
 * Contributors: Daniel Viner,
 *               Michael Gregory,
 *               Sam Scott,
 *               Esther Sully.
 * Function: Handles certain database functions.
 */
public class DbBean {
    Connection con;
    public Cuser us = new Cuser();
    
    public Connection getCon(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/XYZDB", 
                    "root", "root");       
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public int getLastRow(String table){
        int lastrow = 5;
        Connection con1 = getCon();
        String sql = "SELECT COUNT(*) FROM " + table;
        PreparedStatement ps;
        System.out.println(sql);
        try {
            ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            lastrow = rs.getInt(1) + 1;
            con1.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lastrow;
    }
    
    public void approveUser(String id){
        Connection con1 = getCon();
        String sql = "UPDATE members SET \"status\"='APPROVED' WHERE \"id\"= '" + id + "'";
        
        try {
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
            
            sql = "UPDATE users SET \"status\"='APPROVED' WHERE \"id\"= '" + id + "'";
            ps = con1.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUser(String uName){
        Connection con1 = getCon();
        String sql = "SELECT * FROM ROOT.MEMBERS WHERE members.\"id\"= '" + uName + "'";
        //SELECT * FROM ROOT.MEMBERS WHERE members."id" = 'e-simons';
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            us.setID(rs.getString(1));
            us.setName(rs.getString(2));
            us.setAddress(rs.getString(3));
            us.setDoB(rs.getDate(4));
            us.setDoR(rs.getDate(5));
            us.setUserStat(rs.getString(6));
            us.setBalance(rs.getDouble(7), "ADD");         
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList getAppliedMems(){
        ArrayList<String> apps = new ArrayList<>();
        Connection con1 = getCon();
        String SQL = "SELECT \"id\" FROM members WHERE \"status\" = 'APPLIED'";

        try {
            PreparedStatement ps = con1.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                apps.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //System.out.println(apps.get(1));
        return apps;
    }
    
        public void addFunds(double funds){
        try {
            DbBean db = new DbBean();
            Connection con1 = db.getCon();
            String sql = "UPDATE ROOT.MEMBERS SET \"balance\" = " 
                    + (us.getBalance() + funds) + " WHERE \"id\" = '" + us.getID() 
                    + "'";
            System.out.println(sql);
            
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
            us.setBalance(funds, "ADD");
        } catch (SQLException ex) {
            Logger.getLogger(Cuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void payHandler(String payType){
        try {
            Connection con1 = getCon();
            int id = getLastRow("payments");
            Date date = Date.valueOf(LocalDate.now());
            Time time = Time.valueOf(LocalTime.now());
            PreparedStatement ps;
            
            if(payType.equals("Provis")){
                String sql = "INSERT INTO payments VALUES(" + id + ", '"
                            + us.getID() + "', 'FEE', " + 10.0 + ", '" + date
                            + "', '" + time + "')";
                System.out.println(sql);
                    ps = con1.prepareStatement(sql);
                    ps.execute();
                    
                    sql = "UPDATE members SET \"status\"='APPLIED', "
                            + "\"balance\"= " + (us.getBalance() - 10.0) 
                            + " WHERE \"id\" ='" + us.getID() + "'";
                    ps = con1.prepareStatement(sql);
                    ps.execute();
                    
                    sql = "UPDATE users SET \"status\"='APPLIED' WHERE \"id\"='" + us.getID() + "'";
                    ps = con1.prepareStatement(sql);
                    ps.execute();
                    
                    us.setUserStat("APPLIED");
                    us.setBalance(10.0, "SUB");
            }else if(payType.equals("sus")){
                
            }else if(payType.equals("Annual")){
                
            }
            
        } catch (SQLException ex) {
            System.out.println("SQL RUNTIME ERROR IN PAYHANDLER");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void claimHandler(int id, String type){
        Connection con1 = getCon();
        String sql = "UPDATE claims SET \"status\"='" + type + "' WHERE \"id\"=" + id + "";
        System.out.println(sql);
        try {
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR IN CLAIM HANDLER");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void submitClaim(double Tamount, String Treason){
        //Cuser us = new Cuser();
        Connection con1 = getCon();
        int Tid = getLastRow("claims");
        Date Tdate = Date.valueOf(LocalDate.now());
        
        String sql = "INSERT INTO claims VALUES(" + Tid + ", " + "'" + us.getID() 
                + "', '" + Tdate + "', '" + Treason + "', 'PENDING', " + Tamount 
                + ")";
        try {
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(claims.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<claims> getClaims(){
        ArrayList<claims> claim = new ArrayList<>();
        Connection con1 = getCon();
        String sql = "SELECT * FROM claims WHERE \"mem_id\" ='" + us.getID() + "'";
        try {
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                claim.add(new claims(rs.getInt(1), rs.getString(2), 
                        rs.getDate(3), rs.getString(4), rs.getString(5), 
                        rs.getDouble(6), 0));
            }
        } catch (SQLException ex) {
            Logger.getLogger(claims.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.reverse(claim);
        return claim;
    }
    
    public ArrayList<claims> allClaims(){
        ArrayList<claims> claim = new ArrayList<>();
        Connection con1 = getCon();
        String sql = "SELECT * FROM claims WHERE \"status\"='PENDING'";
        
        try {
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                claim.add(new claims(rs.getInt(1), rs.getString(2), 
                        rs.getDate(3), rs.getString(4), rs.getString(5), 
                        rs.getDouble(6), claimCount(rs.getString(2))));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR IN ALLCLAIMS FUNCTION");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.reverse(claim);
        return claim;
    }
    
    public int claimCount(String id){
        int claimCount=0;
        Connection con1 = getCon();
        Date start = Date.valueOf(LocalDate.now().withMonth(1).withDayOfMonth(1));
        Date end = Date.valueOf(LocalDate.now().withMonth(12).withDayOfMonth(31));
        String sql = "SELECT COUNT(*) FROM claims WHERE \"mem_id\" = '" + id + "' AND \"status\"= 'APPROVED' AND \"date\" BETWEEN '" + start + "' AND '" + end +"'";
        try {
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            claimCount = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("ERROR IN CLAIMCOUNT FUNCTION");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return claimCount;
    }
}
