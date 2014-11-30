<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link href="newcss.css" rel="stylesheet" type="text/css"/>
        <title>Tough Udder: Obstacle Race</title>
    </head>

    <body>
        <div id="outborder">
            <%@include file="header.jsp" %>

            <div id="log-in-bubble">
                <p>Please log in:</p>
                <p class="error">${account.loginError}</p>
                <form name="logInForm" action="Controller" method="post">
                    <input type="hidden" name="action" value="login">
                    <label>USER ID </label>
                    <input type="text" name="userID"><br>
                    <label>PASSWORD </label>
                    <input type="password" name="password"><br>
                    <label>&nbsp;</label>
                    <input type="submit" value="Log In" class="button">
                </form>
            </div>

            <%@include file="footer.jsp" %>
        </div>      
    </body>
</html>