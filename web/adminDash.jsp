<%-- 
    Document   : adminDash
    Created on : 21-Nov-2017, 15:26:28
    Author     : Jacob Williams
    Contributors: Daniel Viner,
                  Sam Scott,
                  Esther Sully,
                  Michael Gregory.
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
            if(session.getAttribute("admin") == null){
                response.sendRedirect("login.jsp");
            }
        %>
        <h1 align="center">Admin Dashboard</h1>
        <h2 align="center">Welcome Back to XYZ</h2>
        <div align="center">
            <form method="POST" action="admin">
                Please select your action below:<br><br>
                <button type="submit" name="btnMemApprove" value="memApprove" 
                        style="width:120px">Approve Member</button><br>
                <button type="submit" name="btnClaimApprove" 
                        value="claimApprove" style="width:120px">
                        Approve Claim</button><br>
                <button type="submit" name="btnAnnual" value="annual" 
                        style="width:120px">Approvals</button><br>
                <button type="submit" name="btnLogout" value="logout" 
                        style="width:120px">Log Out</button><br>
            </form>
        </div>
    </body>
</html>
