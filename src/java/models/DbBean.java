package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
