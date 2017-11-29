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
      
    /*
    Name: getCon()
    Inputs: N/A
    Outputs: Con - Database connection String
    Function: returns the connection string for database communication.
    */
    public Connection getCon(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/XYZDB", 
                    "root", "root");       
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
        return con;
    }
    
    /*
    ----------------------------------------------------------------------------
    
                                GENERAL FUNCTIONS
    
    ----------------------------------------------------------------------------
    */
    
    /*
    Name: getLastRow
    Inputs: table - The table you need the last row of.
    Outputs: Lastrow - returns the last row of the table + 1.
    Function: Returns the index required for inserting a new record into either
              payments or claims.
    */
    public int getLastRow(String table){
        //Initialise variables.
        int lastrow = 0;
        Connection con1 = getCon();
        PreparedStatement ps;
        
        //execute sql and calculate lastrow.
        try {
            String sql = "SELECT COUNT(*) FROM " + table;
            ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            lastrow = rs.getInt(1) + 1;
            con1.close();
        } catch (SQLException ex) {
            System.out.println("ERROR IN LASTROW");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
        return lastrow;
    }
    
    /*
    ----------------------------------------------------------------------------
    
                                  USER FUNCTIONS
    
    ----------------------------------------------------------------------------
    */
    
    /*
    Name: approveUser
    Input: id - A users username.
    Output: N/A
    Function: sets a users status to approved across all tables.
    */
    public void approveUser(String id){
        //create connection
        Connection con1 = getCon();
        
        try {
            //Create and execute sql
            String sql = "UPDATE members SET \"status\"='APPROVED' "
                    + "WHERE \"id\"= '" + id + "'";
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
            
            //define next sql and execute.
            sql = "UPDATE users SET \"status\"='APPROVED' WHERE \"id\"= '" 
                    + id + "'";
            ps = con1.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("SQL ERROR IN APPROVEUSER");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
    }
    
    /*
    Name: setUser
    Input: uName - A users username.
    Output: n/a
    Function: Creates and stores a local profile of the currently logged in user
    */
    public void setUser(String uName){
        //create connection
        Connection con1 = getCon();
       

        try {
            //create and execute sql
            String sql = "SELECT * FROM ROOT.MEMBERS WHERE members.\"id\"= '" 
                    + uName + "'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            //store results.
            if(rs.next()){
            us.setID(rs.getString(1));
            us.setName(rs.getString(2));
            us.setAddress(rs.getString(3));
            us.setDoB(rs.getDate(4));
            us.setDoR(rs.getDate(5));
            us.setUserStat(rs.getString(6));
            us.setBalance(rs.getDouble(7), "SET");         
            }
        } catch (SQLException ex) {
            System.out.println("SQL ERROR IN SETUSER");
            Logger.getLogger(Cuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Name: getAppliedMems
    Input: n/a
    output: apps - an arraylist of all "applied" members.
    Function: returns all the currently applied members.
    */
    public ArrayList getAppliedMems(){
        //initialise arraylist, create connection
        ArrayList<String> apps = new ArrayList<>();
        Connection con1 = getCon();


        try {
            //create and execute sql statement.
            String SQL = "SELECT \"id\" FROM members WHERE \"status\" = "
                    + "'APPLIED'";
            PreparedStatement ps = con1.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
           
            //add results of query to arraylist.
            while(rs.next()){
                apps.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
        
        //System.out.println(apps.get(1));
        return apps;
    }
    
    /*
    ----------------------------------------------------------------------------
    
                                Payment Functions
    
    ----------------------------------------------------------------------------
    */
    
    /*
    Name: addFunds
    Input: Funds - The amount of money being added.
    Output: N/A
    Function: updates the balance of the currently logged in user.
    */
    public void addFunds(double funds){
        //create connection
        Connection con1 = getCon();
        
        try {
            //create and execute sql
            String sql = "UPDATE ROOT.MEMBERS SET \"balance\" = " 
                + (us.getBalance() + funds) + " WHERE \"id\" = '" + us.getID() 
                + "'";
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
            
            //update local value.
            us.setBalance(funds, "ADD");
        } catch (SQLException ex) {
            Logger.getLogger(Cuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
        Name: payHandler
        Input: paytype - the type of payment being made.
        Output: n/a
        Function: Handles all the forms of payments passed from userpayment.jsp
    */
    public void payHandler(String payType){
        //initialise variables and database connection
        Connection con1 = getCon();
        int id = getLastRow("payments");
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        PreparedStatement ps;
        
        //define and execute sql based on paytype.
        try {    
            if(payType.equals("Provis")){
                //create payments
                String sql = "INSERT INTO payments VALUES(" + id + ", '"
                            + us.getID() + "', 'FEE', " + 10.0 + ", '" + date
                            + "', '" + time + "')";
                System.out.println(sql);
                    ps = con1.prepareStatement(sql);
                    ps.execute();
                    
                    //set members status to applied.
                    sql = "UPDATE members SET \"status\"='APPLIED', "
                            + "\"balance\"= " + (us.getBalance() - 10.0) 
                            + " WHERE \"id\" ='" + us.getID() + "'";
                    ps = con1.prepareStatement(sql);
                    ps.execute();
                    
                    //set users status to applied.
                    sql = "UPDATE users SET \"status\"='APPLIED' WHERE \"id\"='"
                            + us.getID() + "'";
                    ps = con1.prepareStatement(sql);
                    ps.execute();
                    
                    //update local user settings.
                    us.setUserStat("APPLIED");
                    us.setBalance(10.0, "SUB");
            }else if(payType.equals("sus")){
                
            }
            
        } catch (SQLException ex) {
            System.out.println("SQL RUNTIME ERROR IN PAYHANDLER");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
    }
    
    /*
    Name: getAnnual
    input: n/a
    output: annual - a fee calculated by adding together all the claims made
                     between 01/01/YYYY and 31/12/YYYY divided by the current 
                     number of members.
    function: calculates annual (see above).
    */
    public double getAnnual(){
        //initialise variables, create connection.
        double annual = 0.0;
        Connection con1 = getCon();
        Date start = Date.valueOf(LocalDate.now().withMonth(1)
                .withDayOfMonth(1));
        Date end = Date.valueOf(LocalDate.now().withMonth(12)
                .withDayOfMonth(31));

        try {
            //create and execute first sql.
            String sql = "SELECT \"amount\" FROM claims WHERE \"status\" = "
                    + "'APPROVED' AND \"date\" BETWEEN '" + start + "' AND '" 
                    + end +"'";
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            //use result of sql to get total cost of claims.
            while(rs.next()){
                annual = annual + rs.getDouble(1);
            }
            
            //get the number of users.
            sql = "SELECT COUNT(*) FROM members";
            ps = con1.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //use result to create the annual fee.
            rs.next();
            annual = annual / rs.getDouble(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }    
        return annual;
    }
    
    /*
    Name: applyAnnual
    Input: n/a
    Output: n/a
    Function: Applies the current annual fee to all members.
    */
    public void applyAnnual(){
        //initialise variables, create connection.
        double annual = getAnnual();
        Connection con1 = getCon();
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        PreparedStatement ps;

        try {
            //create and execute first sql.
            String sql = "UPDATE members SET \"balance\" = \"balance\" - " 
                    + annual + "";
            ps = con1.prepareStatement(sql);
            ps.execute();
            
            //create and execute second sql.
            sql = "SELECT \"id\" FROM members";
            ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
    //use previous sql results to create and execute consecutive sql statements.
            while(rs.next()){
                int id = getLastRow("payments");
                
                sql = "INSERT INTO payments VALUES (" + id + ", '" 
                        + rs.getString(1) + "', 'ANNUAL', " + annual + ", '" 
                        + date + "', '" + time + "')";
                ps = con1.prepareStatement(sql);
                ps.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
    }
    
    /*
    ----------------------------------------------------------------------------
    
                                CLAIM FUNCTIONS
    
    ----------------------------------------------------------------------------
    */
    
    /*
    Name: claimHandler
    Input: id - the users unique id.
         type - the new status of the claim.
    Output: n/a
    Function: Either approves or declines a specific claim.
    */
    public void claimHandler(int id, String type){
        //initalise database connection.
        Connection con1 = getCon();

        //create and execute sql statement.
        try {
            String sql = "UPDATE claims SET \"status\"='" + type + "' WHERE "
                    + "\"id\"=" + id + "";
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("ERROR IN CLAIM HANDLER");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
    }
    
    /*
    Name: submitClaim
    Input: amount - cost of the claim.
           reason - the reason for the claim
    Output: n/a
    Function: submits a claim.
    */
    public void submitClaim(double amount, String reason){
        //initialise variables and database connection.
        Connection con1 = getCon();
        int id = getLastRow("claims");
        Date date = Date.valueOf(LocalDate.now());
        
        //create and execute sql statement.
        try {
            String sql = "INSERT INTO claims VALUES(" + id + ", " + "'" 
                    + us.getID() + "', '" + date + "', '" + reason 
                    + "', 'PENDING', " + amount + ")";
            PreparedStatement ps = con1.prepareStatement(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(claims.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
    }
    
    /*
    Name: getClaims
    Input: n/a
    Output: claim - an arraylist of all the current claims by the current user.
    Function: returns all the claims made by the current user.
    */
    public ArrayList<claims> getClaims(){
        //create arraylist and database connection.
        ArrayList<claims> claim = new ArrayList<>();
        Connection con1 = getCon();

        //create and execute sql statement.
        try {
            String sql = "SELECT * FROM claims WHERE \"mem_id\" ='" + us.getID() 
                    + "'";
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            //use results to create new claim objects in claim arraylist.
            while(rs.next()){
                claim.add(new claims(rs.getInt(1), rs.getString(2), 
                        rs.getDate(3), rs.getString(4), rs.getString(5), 
                        rs.getDouble(6), 0));
            }
        } catch (SQLException ex) {
            Logger.getLogger(claims.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
        //reverse arraylist so most recent claims are first.
        Collections.reverse(claim);
        return claim;
    }
    
    /*
    Name: allClaims
    Input: n/a
    Output: claim - an Arraylist of all currently pending claims.
    Function: Returns all pending claims.
    */
    public ArrayList<claims> allClaims(){
        //initialise arraylist and database connection.
        ArrayList<claims> claim = new ArrayList<>();
        Connection con1 = getCon();
 
        try {
            //create and execute sql statement.
            String sql = "SELECT * FROM claims WHERE \"status\"='PENDING'";
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            //use results of sql to create claim objects in claim arraylist.
            while(rs.next()){
                claim.add(new claims(rs.getInt(1), rs.getString(2), 
                        rs.getDate(3), rs.getString(4), rs.getString(5), 
                        rs.getDouble(6), claimCount(rs.getString(2))));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR IN ALLCLAIMS FUNCTION");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
        //reverse claim array so most recent claims are first.
        Collections.reverse(claim);
        return claim;
    }
    
    /*
    Name: claimCount
    Input: id - a users id.
    Output: claimCount - the amount of claims made between 01/01/YYYY and 
                         31/12/YYYY by the user whose id has been supplied.
    Function: (see output)
    */
    public int claimCount(String id){
        //initialise variables, create database connection.
        int claimCount=0;
        Date start = Date.valueOf(LocalDate.now().withMonth(1)
                .withDayOfMonth(1));
        Date end = Date.valueOf(LocalDate.now().withMonth(12)
                .withDayOfMonth(31));
        Connection con1 = getCon();
        
        try {
            //create and execute query sql, store result in claimCount.
            String sql = "SELECT COUNT(*) FROM claims WHERE \"mem_id\" = '" 
                    + id + "' AND \"status\"= 'APPROVED' AND \"date\" BETWEEN '" 
                    + start + "' AND '" + end +"'";
            PreparedStatement ps = con1.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            claimCount = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println("ERROR IN CLAIMCOUNT FUNCTION");
            Logger.getLogger(DbBean.class.getName()).log(Level.SEVERE, null, 
                    ex);
        }
        
        return claimCount;
    }
}
