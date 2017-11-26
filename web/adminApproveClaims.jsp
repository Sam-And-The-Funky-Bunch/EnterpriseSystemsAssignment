<%-- 
    Document   : adminApproveClaims
    Created on : 22-Nov-2017, 15:40:46
    Author     : Michael Gregory
    Contributors: Sam Scott,
                  Esther Sully,
                  Jacob Williams,
                  Daniel Viner.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.DbBean" %>
<%@page import="models.claims"%>
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
            ArrayList<claims> claim = db.allClaims();
            request.setAttribute( "claimList", claim );
        %>
        <h1 align="center">Admin Operation:</h1>
        <h2 align="center">Claim Approval</h2>  
        <div style="text-align:center">
            <form method="POST" action="admin">
                <c:forEach var="current" items="${claimList}">
                    <c:set var="claimCount" value="${current.claimCount}"/>
                    User: <c:out value="${current.mem_id}"/><br>
                    Applied: <c:out value="${current.date}"/><br>
                    Reason: <c:out value="${current.reason}"/><br>
                    £<c:out value="${current.amount}"/><br>
                    Claims approved: <c:out value="${current.claimCount}"/><br>
                    <c:choose>
                        <c:when test="${claimCount >= 2}">
                            <button type="submit" name="btnDec" value="${current.ID}" 
                                    style="width:70px">Decline</button><br>
                        </c:when>
                        <c:when test="${claimCount < 2}">
                            <button type="submit" name="btnAcc" value="${current.ID}" 
                                    style="width:70px">Approve</button><br>
                        </c:when>
                    </c:choose>
                    ---------------------------------<br><br>
                </c:forEach>       
                <button type="submit" name="btnBack" value="Back" 
                        style="width:70px">Back</button>
            </form>
        </div>
    </body>
</html>
