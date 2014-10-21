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
            <%@include file="header.jsp" %>

            <table id="log-in">
                <tr>
                    <td rowspan="2">
                        <img class="cow" src="images/ox-fight.jpg" alt="ox fight">
                        <!-- picture from http://www.bestpsdtohtml.com/wp-content/uploads/2012/12/military-cow.jpg -->
                    </td>
                    <td>
                        <img class="join-us" src="images/join-us.png" alt="join us">
                    </td>
                </tr>
                <tr>
                    <td class="user-window">
                        <p class="logged_in"><b>${account.firstName} ${account.lastName}</b></p>
                        <p class="logged_in">Welcome you!</p>
                        <p>Not ${account.firstName}? <a href="Controller?action=logout">Log out</a>.</p>
                    </td>
                </tr>
            </table>

            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
