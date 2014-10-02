<%-- 
    Document   : index
    Created on : Sep 28, 2014, 2:45:50 PM
    Author     : Evan Chan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="newcss.css" rel="stylesheet" type="text/css"/>
        <title>Tough Udder: Obstacle Race</title>
    </head>
    <body>
        <div id="outborder">
            <img id="banner" src="images/logo.jpg" alt="Tough Udder logo">

            <div id='menu'>
                <ul>
                    <li><a href='#'>COMPANY PROFILE</a></li>
                    <li><a href='#'>FIND AN EVENT</a></li>
                    <li><a href='#'>OBSTACLES</a></li>
                    <li><a href='#'>MY ACCOUNT</a></li>
                    <li><a href='#'>MY CART</a></li>
                </ul>
            </div>
            <hr>

            <table id="log-in">
                <tr>
                    <td rowspan="2">
                        <img class="cow" src="images/military-cow.jpg" alt="military cow">
                        <!-- picture from http://www.bestpsdtohtml.com/wp-content/uploads/2012/12/military-cow.jpg -->
                    </td>
                    <td>
                        <img class="join-us" src="images/join-us.png" alt="join us">
                    </td>
                </tr>
                <tr>
                    <td class="user-window">
                        <form name="logInForm" action="." method="post">
                            <label>USER ID </label>
                            <input type="text" name="userID" required><br>
                            <label>PASSWORD </label>
                            <input type="password" name="password" required><br>
                            <label>&nbsp;</label>
                            <input type="submit" value="Log In" class="button">
                        </form>
                        <p>Not a member yet? <a href="#">Register</a>!</p>
                    </td>
                </tr>
            </table>

            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
