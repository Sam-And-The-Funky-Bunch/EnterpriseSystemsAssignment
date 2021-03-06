package com;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cuser;
import models.DbBean;
import models.claims;
/**
 * Author: Michael Gregory
 * Contributors: Jacob Williams,
 *               Esther Sully,
 *               Daniel Viner,
 *               Sam Scott.
 * Function: Handles user operations.
 */
public class user extends HttpServlet {
    public Cuser us = new Cuser();
    public DbBean db = new DbBean();
    public claims claim;
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
        if(request.getParameter("btnLogout") != null){
            RequestDispatcher view = request.getRequestDispatcher("homepage.jsp");
            view.forward(request, response);
            HttpSession session = request.getSession(false);
            if(session != null){
                session.invalidate();
            }
            
            
        }else if(request.getParameter("btnMakeClaim") != null){
            System.out.println(request.getParameter("btnMakeClaim"));
            RequestDispatcher view = request.getRequestDispatcher("userMakeClaim.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnSubmitClaim") != null){
            db.submitClaim(Double.parseDouble(request.getParameter("amount"))
                    , request.getParameter("reason"));
            RequestDispatcher view = request.getRequestDispatcher("userDash.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnClaimStatus") != null){
            RequestDispatcher view = request.getRequestDispatcher("userClaimStatus.jsp");
            view.forward(request, response);
            db.getClaims();
            
        }else if(request.getParameter("btnMakePayment") != null){
            RequestDispatcher view = request.getRequestDispatcher("userPayment.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnBack") != null){
            RequestDispatcher view = request.getRequestDispatcher("userDash.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnBalance") != null){
            RequestDispatcher view = request.getRequestDispatcher("userBalance.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnAdd") != null){
            db.addFunds(Double.parseDouble(request.getParameter("addFunds")));
            RequestDispatcher view = request.getRequestDispatcher("userBalance.jsp");
            view.forward(request, response);
        }else if(request.getParameter("btnPay") != null){
            System.out.println(request.getParameter("btnPay"));
            db.payHandler(request.getParameter("btnPay"));
            RequestDispatcher view = request.getRequestDispatcher("userPayment.jsp");
            view.forward(request, response);
        }
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
