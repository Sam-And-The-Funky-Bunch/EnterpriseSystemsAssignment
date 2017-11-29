package com;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.DbBean;
/*
Author: Michael Gregory
Contributors: Jacob Williams,
              Sam Scott,
              Esther Sully,
              Daniel Viner.
Function: Handles admin operations.
 */
public class admin extends HttpServlet {
    public DbBean db = new DbBean();
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
            
        } else if(request.getParameter("btnAnnual") != null){
            RequestDispatcher view = request.getRequestDispatcher("adminAnnual.jsp");
            view.forward(request, response);
            
        } else if(request.getParameter("btnMemApprove") != null){
            RequestDispatcher view = request.getRequestDispatcher("adminApproveMem.jsp");
            view.forward(request, response);
            
        } else if(request.getParameter("btnClaimApprove") != null){
            RequestDispatcher view = request.getRequestDispatcher("adminApproveClaims.jsp");
            view.forward(request, response);
            
            System.out.println(db.allClaims().toString());
        }else if(request.getParameter("btnBack") != null){
            RequestDispatcher view = request.getRequestDispatcher("adminDash.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnApproveMem") != null){
            System.out.println(request.getParameter("btnApproveMem"));
            db.approveUser(request.getParameter("btnApproveMem"));
            RequestDispatcher view = request.getRequestDispatcher("adminApproveMem.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnDec") != null){
            System.out.println(request.getParameter("btnDec"));
            db.claimHandler(Integer.parseInt(request.getParameter("btnDec")), "DECLINED" );
            RequestDispatcher view = request.getRequestDispatcher("adminApproveClaims.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnAcc") != null){
            System.out.println(request.getParameter("btnAcc"));
            db.claimHandler(Integer.parseInt(request.getParameter("btnAcc")), "APPROVED" );
            RequestDispatcher view = request.getRequestDispatcher("adminApproveClaims.jsp");
            view.forward(request, response);
            
        }else if(request.getParameter("btnAnnualCharge") != null){
            db.applyAnnual();
            RequestDispatcher view = request.getRequestDispatcher("adminAnnual.jsp");
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
