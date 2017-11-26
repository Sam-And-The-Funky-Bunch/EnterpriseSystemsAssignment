<%-- 
    Document   : adminApproveMem
    Created on : 22-Nov-2017, 15:40:22
    Author     : Michael Gregory
    Contributors: Esther Sully,
                  Daniel Viner,
                  Sam Scott,
                  Jacob Williams.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.DbBean" %>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ</title>
    </head>
    <body>
        <%
            DbBean db = new DbBean();
            ArrayList<String> apps = db.getAppliedMems();
            request.setAttribute( "applications", apps );
        %>
        <h1 align="center">Admin Operation:</h1>
        <h2 align="center">Member Approval</h2>
        <div style="text-align:center">
            <form method="POST" action="admin">
                <c:forEach var="current" items="${applications}">
                    <c:out value="${current}"/><br>
                    <button type="submit" name="btnApproveMem" value="${current}" 
                            style="width:70px">Approve</button><br>
                    <c:out value="--------"/><br>
                    
                </c:forEach>
                <button type="submit" name="btnBack" value="Back" 
                        style="width:70px">Back</button>
            </form>
        </div>
    </body>
</html>
