<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <form id="events-form" action="Controller" method="post">
                <table id="events-table" class="events-table">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Event</th>
                            <th>Cost</th>
                            <th>Date</th>
                            <th>Location</th>
                            <th>Add To Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="event" items="${events}">
                          <tr>
                            <td class="event-image-container">
                                <img src="images/${event.imgName}" alt="${event.name} Image" class="event-img">
                            </td>
                            <td class="event-description">
                                <span>${event.name}</span>
                            </td>
                            <td class="event-cost">
                                <span>${event.cost}</span>
                            </td>
                            <td class="event-date">
                                <span>${event.date}</span>
                            </td>
                            <td class="event-location">
                                <span>${event.location}</span>
                            </td>
                            <td  class="event-add">
                                <input type="checkbox" name="add-event" value="${event.name}"
                                       <c:if test="${cart.hasEvent(event.name)}"> checked</c:if>
                                ><br>
                            </td>
                          </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <input class="event-submit" id="event-submit-btn" type="submit" name="action" value="Update Cart"/>
            </form>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>