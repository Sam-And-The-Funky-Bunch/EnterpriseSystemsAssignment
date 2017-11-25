<%-- 
    Document   : userPayment
    Created on : 22-Nov-2017, 15:48:41
    Author     : Jacob Williams
    Contributors: Sam Scott,
                  Michael Gregory,
                  Esther Sully,
                  Daniel Viner.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Cuser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ</title>
    </head>
    <body>
        <% Cuser us = new Cuser(); 
        request.setAttribute( "us", us );
        %>
        <h1 align="center">Make a Payment</h1>
        <h2 align="center">Please complete the details below:</h2>
        <c:set var="val" value="${us.userStat}" />
        <c:set var="balance" value="${us.balance}"/>
        <div style="text-align:center">
            <form method="POST" action="user">
            <c:choose>
                <c:when test="${val == 'PROVIS'}">
                    Pay your first monthly fee and apply for membership.<br><br>
                    <c:choose>
                        <c:when test="${balance >= 10}">
                            <button type="submit" name="btnPay" value="Provis" 
                                style="width:70px">Pay</button>
                        </c:when>
                        <c:when test="${balance < 10}">
                            <font color="red">You possess insufficient funds, add more funds to continue</font>
                        </c:when>
                    </c:choose>
                </c:when>
                <c:when test="${val == 'SUSPENDED'}">
                    Pay your monthly fee to get access.
                    <c:when test="${balance >= 10}">
                        <button type="submit" name="btnPay" value="Sus" 
                                style="width:70px">Pay</button>
                    </c:when>
                    <c:when test="${balance < 10}">
                        <font color="red">You possess insufficient funds, add more funds to continue</font>
                    </c:when>
                </c:when>
                <c:when test="${val == 'ANNUAL'}">
                    The annual fee has been issued, please complete this payment to regain access.
                        <button type="submit" name="btnPay" value="Annual" 
                                style="width:70px">Pay</button>
                </c:when>
                <c:when test="${val == 'APPLIED'}">
                    You have paid your monthly fee, but your account is still awaiting approval<br>
                    This could take some time, we apologise for the wait.
                </c:when>
                <c:when test="${val == 'APPROVED'}">
                    You have no outstanding payments
                </c:when>
            </c:choose>
             
                <button type="submit" name="btnBack" value="Back" 
                        style="width:70px">Back</button>
            </form>
        </div>
    </body>
</html>
