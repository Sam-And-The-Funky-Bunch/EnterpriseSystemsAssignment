package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
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
    
    public void submitClaim(double amount, String reason){
        DbBean db = new DbBean();
        Cuser us = new Cuser();
        Connection con = db.getCon();
        int id = db.getLastRow("claims");
        Date date = Date.valueOf(LocalDate.now());
        
        String sql = "INSERT INTO claims VALUES(" + id + ", " + "'" + us.getID() 
                + "', '" + date + "', '" + reason + "', 'PENDING', " + amount 
                + ")";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(claims.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
