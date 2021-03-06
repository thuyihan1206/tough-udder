<%--
    Document   : confirmation
    Created on : Nov 29, 2014, 1:51:12 PM
    Author     : badmanistrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Tough Udder: Obstacle Race</title>
        <link href="newcss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="outborder">
            <%@include file="header.jsp" %>
            <%@ page import="java.util.List" %>
            <%@ page import="toughudder.*" %>
            <% CreditCardInfoBean ccib = (CreditCardInfoBean) session.getAttribute(Controller.CC_DATA);
                Object error = request.getAttribute(Controller.ERROR);
                String email = request.getParameter(Controller.EMAIL);
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                }
                List<Event> contents = cart.getEvents();
            %>
            <p>${account.firstName} ${account.lastName},</p>
            <p>Your Tough Udder registration is complete! A summary is shown below<% if (email != null && error == null) {%> and has been sent to <%= email%><% } %>.</p>
            <% if (email != null && email.length() > 1 && error != null) {%>
            <p>There was a problem sending a registration email to <b><%= email%></b>.</p>
            <p><%= error.toString()%></p>
            <% } %>
            <table id="events-table" class="events-table">
                <thead>
                    <tr>
                        <th></th>
                        <th>Event</th>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Cost</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Event evt : contents) {%>
                    <tr>
                        <td class="event-image-container">
                            <img src="images/<%= evt.getImgName()%>" alt="<%= evt.getName()%>" class="event-img">
                        </td>
                        <td class="event-description">
                            <span><%= evt.getName()%></span>
                        </td>
                        <td class="event-date">
                            <span><%= evt.getDate()%></span>
                        </td>
                        <td class="event-location">
                            <span><%= evt.getLocation()%></span>
                        </td>
                        <td class="event-cost">
                            <span><%= cart.getCostFormat().format(evt.getCost())%></span>
                        </td>
                    </tr>
                    <% }%>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th>Total Cost:</th>
                        <th><%= cart.getCostFormat().format(cart.getTotalCost())%></th>
                    </tr>
                </tbody>
            </table>
            <p>The above total has been charged to this credit card:<br />
                <br />
                <%= ccib.getName()%><br />
                <%= ccib.getAddress()%><br />
                <%= ccib.getCity()%>,&nbsp<%= ccib.getState()%>&nbsp<%= ccib.getZip()%><br />
                <br />
                <%= ccib.getType()%>/<%= ccib.getRedactedNumber()%><br />
                Expires <%= ccib.getExpiryMonth()%>/<%= ccib.getExpiryYear()%><br />
            </p>

            <button class="event-submit" type="button" onclick="location.href = 'Controller?action=print'">
                Print Confirmation in PDF</button>
            &nbsp;
            <button class="event-submit" type="button" onclick="location.href = 'Controller?action=startover'">
                Register More</button>

            <br />
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
