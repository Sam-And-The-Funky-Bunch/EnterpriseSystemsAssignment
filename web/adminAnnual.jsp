<%-- 
    Document   : adminAnnual
    Created on : 26-Nov-2017, 15:09:21
    Author     : Michael Gregory
    Contributors: Jacob Williams,
                  Esther Sully,
                  Sam Scott,
                  Daniel Viner.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.DbBean" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ</title>
    </head>
    <body>
        <%
            DbBean db = new DbBean();
            double annual = db.getAnnual();
            request.setAttribute("annFee", annual);
        %>
        <h1 align="center">Admin Function:</h1>
        <h2 align="center">Create Annual Payment</h2>
        <div style="text-align:center">
            <form method="POST" action="admin">
            Annual fee per member is:<br>
            £<%=(annual)%>
            <br>
            <button type="submit" name="btnAnnualCharge" value="Annual" 
                        style="width:70px">Charge</button>
                        <br><br>     
            <br><br>
            <button type="submit" name="btnBack" value="Back" 
                        style="width:70px">Back</button>
            </form>
        </div>
    </body>
</html>
