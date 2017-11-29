<%-- 
    Document   : userBalance
    Created on : 24-Nov-2017, 18:26:38
    Author     : Daniel Viner,
    Contributors: Jacob Williams,
                  Sam Scott,
                  Michael Gregory,
                  Esther Sully.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Cuser" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ</title>
    </head>
    <body>
        <%
            if(session.getAttribute("user") == null){
                response.sendRedirect("login.jsp");
            }
        %>
        <% Cuser us = new Cuser();%>
        <h1 align="center">Your Balance</h1>
        <h2 align="center">Your balance is currently:</h2>
        <div align="center">
            <font color="green">£<%=(us.getBalance())%></font>
            <br><br>
            Funds you wish to add:
            <form>
                <input type="number" name="addFunds" placeholder="0.0" 
                       style="width:150px"><br>
                <button type="submit" name="btnAdd" value="add" 
                        style="width:70px">Add</button>
                        <br> <br>
                <button type="submit" name="btnBack" value="Back" 
                        style="width:70px">Back</button>
            </form>
        </div>      
    </body>
</html>
