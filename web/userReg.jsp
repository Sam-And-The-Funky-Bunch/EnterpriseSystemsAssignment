<%-- 
    Document   : userReg
    Created on : 24-Nov-2017, 17:30:56
    Author     : Jacob Williams
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
        <% Cuser us = new Cuser();%>
        <% String password;
           String temp = us.getDoB().toString().replaceAll("-", "");
           password = "" + temp.substring(6, 8) + ""
                    + temp.substring(4, 6) + temp.substring(2, 4);
        %>
        <h1 align="center">Registration Successful!</h1>
        <h2 align="center">Welcome to XYZ, <%=(us.getName()) %></h2>
        <div align="center">
            Please make note of your username and password
            <br>
            <b>Username: <%=(us.getID())%></b>
            <br>
            <b>Password: <%=(password)%></b>
            <br>
            To make claims, you must be a member for six months, and have paid
            a monthly fee.
            <br>
            Once you are finished, press the button below to continue to your dashboard.
            <br>
            <form method="POST" action="user">
            <button type="submit" name="btnBack" value="continue" 
                        style="width:110px">Continue</button><br>
            </form>
        </div>
    </body>
</html>
