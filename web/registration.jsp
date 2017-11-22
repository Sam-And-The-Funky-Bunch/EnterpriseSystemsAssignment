<%-- 
    Document   : registration
    Created on : 19-Nov-2017, 22:28:27
    Author     : Jacob Williams
    Contributors: Michael Gregory,
                  Sam Scott,
                  Daniel Viner,
                  Esther Sully.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ</title>
    </head>
    <body>
        <h1 align="center">Registration</h1>
        <div style="text-align:center">
            <h2>Please enter your name, address and date of birth below</h2>
            <form method="POST"
                   action="homepage">
                <input type="text" name="Rname" placeholder="Name" 
                       style="width:150px"><br>
                <input type="text" name="address" placeholder="Address" 
                       style="width:150px"><br>
                <input type="date" name="dob" placeholder="Date of Birth" 
                       style="width:150px"><br>
                <input type="submit" name="btnReg" value="Register" 
                       style="width:70px"><br><br>
                <button type="submit" name="btnLog" value="Login" 
                        style="width:70px">Login</button>
                <button type="submit" name="btnHome" value="Home" 
                        style="width:70px">Home</button>
            </form>
        </div>
    </body>
</html>
