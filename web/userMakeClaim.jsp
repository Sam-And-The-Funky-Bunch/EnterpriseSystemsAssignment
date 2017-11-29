<%-- 
    Document   : userMakeClaim
    Created on : 22-Nov-2017, 15:48:15
    Author     : Jacob Williams
    Contributors: Daniel Viner,
                  Esther Sully,
                  Michael Gregory,
                  Sam Scott.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1 align="center">Make a Claim</h1>
        <h2 align="center">Please complete the details below</h2>
        <div style="text-align:center">
            <form method="POST" action="user">
                Please enter the cost and reason for your claim.<br>
                <input type="number" name="amount" placeholder="0.0" 
                       style="width:150px"><br>
                <input type="text" name="reason" placeholder="Reason"
                       style="width:150px"><br>
                <button type="submit" name="btnSubmitClaim" value="submit" 
                        style="width:70px">Submit</button>
                        <br><br>
                <button type="submit" name="btnBack" value="Back" 
                        style="width:70px">Back</button>
            </form>
        </div>
    </body>
</html>
