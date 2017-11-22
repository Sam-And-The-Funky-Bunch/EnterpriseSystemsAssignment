<%-- 
    Document   : login
    Created on : 19-Nov-2017, 21:21:53
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
        <title align="center">XYZ</title>
    </head>
    <body>
        <h1 align="center">Login</h1>
        <div style="text-align:center">
            <form method="POST" action="homepage">
                
                <input type="text" name="uName" placeholder="Username" 
                       style="width:150px"><br>
                <input type="password" name="password" placeholder="Password" 
                       style="width:150px"><br>
                
                <button type="submit" name="btnUserLogin" value="UserLogin" 
                        style="width:70px">Login</button><br><br>
                <button type="submit" name="btnReg" value="Reg" 
                        style="width:70px">Register</button>
                <button type="submit" name="btnHome" value="Home" 
                        style="width:70px">Home</button>
            </form>   

        </div>
    </body>
</html>
