<%-- 
    Document   : index
    Created on : Sep 28, 2014, 2:45:50 PM
    Author     : Evan Chan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="newcss.css" rel="stylesheet" type="text/css"/>
        <title>Tough Udder: Obstacle Race</title>
    </head>
    <body>
        <div id="outborder"  class="outborder">
            <%@include file="header.jsp" %>

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
                        <form name="logInForm" action="Controller" method="post">
                            <input type="hidden" name="action" value="login">
                            <label>USER ID </label>
                            <input type="text" name="userID"><br>
                            <label>PASSWORD </label>
                            <input type="password" name="password"><br>
                            <label>&nbsp;</label>
                            <input type="submit" value="Log In" class="button">
                        </form>
                        <p class="error">${account.loginError}</p>
                        <!--
                        <p>Not a member yet? <a href="#">Register</a>!</p>
                        -->
                    </td>
                </tr>
            </table>

            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
