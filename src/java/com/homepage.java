package com;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.user;

/**
* Author: Jacob Williams
* Contributors: Esther Sully,
*               Daniel Viner,
*               Sam Scott,
*               Michael Gregory.
* Function: Handles homepage, login, and registration functions.
 */
public class homepage extends HttpServlet {
    public int  userCount = 1;
    public String savedUName;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("btnLog") != null){
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnReg") != null){
            RequestDispatcher view = request.getRequestDispatcher("registration"
                    + ".jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnUserReg") != null){
            Date dob = Date.valueOf(request.getParameter("dob"));
            String uName = request.getParameter("Rname");
            String address = request.getParameter("address");
            registration(uName, address, dob);
        
        }else if(request.getParameter("btnHome") != null){
            RequestDispatcher view = request.getRequestDispatcher("homepage.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnUserLogin") != null){
            String uName = request.getParameter("uName");
            String password = request.getParameter("password");
            boolean verUser = userLogin(uName, password);
            System.out.println(verUser);
            if(verUser == true && uName.equals("admin")){
                RequestDispatcher view = request.getRequestDispatcher("adminDash.jsp");
                view.forward(request, response);
                
            }else if(verUser == true){
                RequestDispatcher view = request.getRequestDispatcher("userDash.jsp");
                view.forward(request, response);
                
            }else{
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
        }
    }
    
    public void registration(String name, String address, Date dob){
        try {
            //get user details
            String temp[] = name.split(" ");
            String uName = "" + name.substring(0,1) + "-" + temp[1];
            savedUName = uName;
            uName = checkUName(uName);
            String pTemp = dob.toString().replaceAll("-", "");
            String password = "" + pTemp.substring(6, 8) + ""
                    + pTemp.substring(4, 6) + pTemp.substring(2, 4);
            Date dor = Date.valueOf(LocalDate.now());
            String status = "new";
            double balance = 0.0;
            
            models.DbBean db = new models.DbBean();
            Connection con = db.getCon();
            String sql = "INSERT INTO members VALUES ('" + uName + "', '" + name + "', '" + address + "', '" + dob + "', '" + dor + "', '" + status + "', " + balance + ")";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            
            sql = "INSERT INTO users VALUES ('" + uName + "', '" + password + "', '" + status + "')";
            System.out.println(sql);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String checkUName(String uName){
        try {
            models.DbBean db = new models.DbBean();
            Connection con = db.getCon();
            String sql = "SELECT * FROM ROOT.MEMBERS WHERE members.\"id\"= '" + uName + "'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                uName = savedUName + "" + userCount;
                userCount++;
                con.close();
                checkUName(uName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uName;
    }
    public boolean userLogin(String uName, String password){
        boolean loginstat = false;
        user us = new user();
        models.DbBean db = new models.DbBean();
        
        try {    
            Connection con = db.getCon();
            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(uName) && rs.getString(2).equals(password)){
                    loginstat = true;
                    us.setUser(uName);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(us.toString());
        return loginstat;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
