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
                <p><span>USER ID:</span>${account.userID}</p>
                <p><span>NAME:</span>${account.firstName} ${account.lastName}</p>
                <p><span>EMAIL:</span>${account.email}</p>
            </div>

            <%@include file="footer.jsp" %>
        </div>      
    </body>
</html>