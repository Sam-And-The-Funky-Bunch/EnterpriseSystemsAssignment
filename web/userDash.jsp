<%-- 
    Document   : userDash
    Created on : 21-Nov-2017, 15:26:18
    Author     : Jacob Williams
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ</title>
    </head>
    <body>
        <h1 align="center">Dashboard</h1>
        <h2 align="center">Welcome Back to XYZ</h2>
        <div align="center">
            <form>
                Please select your action below:<br><br>
                <button type="submit" name="btnMakeClaim" value="makeClaim" 
                        style="width:110px">Make a Claim</button><br>
                <button type="submit" name="btnClaimStatus" value="claimStatus" 
                        style="width:110px">Claim Status</button><br>
                <button type="submit" name="btnMakePayment" value="makePayment" 
                        style="width:110px">Make Payment</button><br>
                <button type="submit" name="btnLogout" value="logout" 
                        style="width:110px">Login</button><br>
            </form>
        </div>
    </body>
</html>
