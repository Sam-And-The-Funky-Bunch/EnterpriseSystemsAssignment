<%-- 
    Document   : userClaimStatus
    Created on : 22-Nov-2017, 16:07:14
    Author     : Jacob Williams
    Contributors: Sam Scott,
                  Daniel Viner,
                  Esther Sully,
                  Michael Gregory.

--%>

<%@page import="models.claims"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.DbBean" %>
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
            ArrayList<claims> claims = db.getClaims();
            //System.out.println(claims.get(1).toString());
            request.setAttribute( "claimsList", claims );
            %>
            
        <h1 align="center">Claim Status</h1>
        <h2 align="center">The status of you claims are listed below:</h2>
        <div style="text-align:center">
            <c:forEach var="current" items="${claimsList}" >
                <c:out value="${current.date}"/><br>
                <c:out value="${current.reason}"/><br>
                <c:out value="£${current.amount}"/><br>
                <c:out value="${current.status}"/><br>
                <c:out value="-----------------"/><br>
                <br>
            </c:forEach>
            <form method="POST" action="user">
                <button type="submit" name="btnBack" value="Back" 
                        style="width:70px">Back</button>
            </form>
        </div>
    </body>
</html>
