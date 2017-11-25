package models;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    Cuser us = new Cuser();
    
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
    
    public void submitClaim(double Tamount, String Treason){
        Cuser us = new Cuser();
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
                        rs.getDouble(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(claims.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.reverse(claim);
        return claim;
    }
}
