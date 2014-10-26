<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Tough Udder: Obstacle Race</title>
        <link href="newcss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="outborder">
            <%@include file="header.jsp" %>
            <form id="events-form" action="${pageContext.request.contextPath}/Controller" method="post">
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
		            	<tr>
		            		<td class="event-image-container">
		            			<img src="images/mad-cow-mud-run.png" alt="Mad Cow Image" class="event-img">
		            		</td>
		            		<td class="event-description">
		            			<span>Mad Cow Mud Run</span>
		            		</td>
		            		<td class="event-cost">
		            			<span>$75</span>
		            		</td>
		            		<td class="event-date">
		            			<span>06-15-2015</span>
		            		</td>
		            		<td class="event-location">
		            			<span>Knoxville, Tennessee</span>
		            		</td>
		            		<td  class="event-add">
		            			<input type="checkbox" name="add-event" value="Mad Cow"><br>
		            		</td>
		            	</tr>
		            	<tr>
		            		<td class="event-image-container">
		            			<img src="images/holy-cow.jpg" alt="Holy Cow Image" class="event-img">
		            		</td>
		            		<td class="event-description">
		            			<span>Holy Cow! Pasture Run</span>
		            		</td>
		            		<td class="event-cost">
		            			<span>$50</span>
		            		</td>
		            		<td class="event-date">
		            			<span>06-19-2015</span>
		            		</td>
		            		<td class="event-location">
		            			<span>Wichita, Kansas</span>
		            		</td>
		            		<td class="event-add">
		            			<input type="checkbox" name="add-event" value="Holy Cow"><br>
		            		</td>
		            	</tr>
		            	<tr>
		            		<td class="event-image-container">
		            			<img src="images/muddy-udder.png" alt="Muddy Udder Image" class="event-img">
		            		</td>
		            		<td class="event-description">
		            			<span>Muddy Udder Obstacle Course</span>
		            		</td>
		            		<td class="event-cost">
		            			<span>$120</span>
		            		</td>
		            		<td class="event-date">
		            			<span>07-07-2015</span>
		            		</td>
		            		<td class="event-location">
		            			<span>Everglades, Florida</span>
		            		</td>
		            		<td class="event-add">
		            			<input type="checkbox" name="add-event" value="Muddy Udder"><br>
		            		</td>
		            	</tr>
		            	<tr>
		            		<td class="event-image-container">
		            			<img src="images/just-an-udder-day.jpg" alt="An Udder Day Image" class="event-img">
		            		</td>
		            		<td class="event-description">
		            			<span>An Udder Day In Paradise: Water Adventure</span>
		            		</td>
		            		<td class="event-cost">
		            			<span>$2500</span>
		            		</td>
		            		<td class="event-date">
		            			<span>07-21-2015</span>
		            		</td>
		            		<td class="event-location">
		            			<span>The Bahamas</span>
		            		</td>
		            		<td  class="event-add">
		            			<input type="checkbox" name="add-event" value="Udder Day"><br>
		            		</td>
		            	</tr>
	            	</tbody>
	            </table>
	            <input class="event-submit" id="event-submit-btn" type="submit" name="pageName" value="Add Events To Cart"/>
            </form>
			<%@include file="footer.jsp" %>
        </div>
    </body>
</html>