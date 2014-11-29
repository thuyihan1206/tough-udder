<%--
    Document   : cart
    Created on : Nov 29, 2014, 1:50:47 PM
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
         <% Cart cart = (Cart)session.getAttribute("cart");
            if (cart == null) {
               cart = new Cart();
            }
            List<Event> contents = cart.getEvents();
         %>
         <form id="events-form" action="Controller" method="post">
            <table id="events-table" class="events-table">
               <thead>
                  <tr>
                     <th></th>
                     <th>Event</th>
                     <th>Date</th>
                     <th>Location</th>
                     <th>Cost</th>
                     <th>Remove from Cart</th>
                  </tr>
               </thead>
               <tbody>
                  <% for (Event evt : contents) { %>
                  <tr>
                     <td class="event-image-container">
                        <img src="images/<%= evt.getImgName() %>" alt="<%= evt.getName() %>" class="event-img">
                     </td>
                     <td class="event-description">
                        <span><%= evt.getName() %></span>
                     </td>
                     <td class="event-date">
                        <span><%= cart.getDateFormat().format(evt.getDate()) %></span>
                     </td>
                     <td class="event-location">
                        <span><%= evt.getLocation() %></span>
                     </td>
                     <td class="event-cost">
                        <span><%= cart.getCostFormat().format(evt.getCost()) %></span>
                     </td>
                     <td  class="event-add">
                        <input type="checkbox" name="add-event" value="<%= evt.getName() %>"><br>
                     </td>
                  </tr>
                  <% } %>
                  <tr>
                     <th></th>
                     <th></th>
                     <th></th>
                     <th>Total Cost:</th>
                     <th><%= cart.getCostFormat().format(cart.getTotalCost()) %></th>
                     <th></th>
                  </tr>
               </tbody>
            </table>
            <input class="event-submit" id="remove-evt-btn" type="submit" name="action" value="Remove from Cart"/>
            <input class="event-submit" id="checkout-btn" type="submit" name="action" value="Checkout"/>
         </form>
         <%@include file="footer.jsp" %>
      </div>
   </body>
</html>
