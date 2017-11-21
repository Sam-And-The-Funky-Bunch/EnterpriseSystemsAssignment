package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Jacob Williams
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
}
