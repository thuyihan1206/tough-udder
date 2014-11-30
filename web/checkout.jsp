<%--
    Document   : checkout
    Created on : Nov 29, 2014, 1:51:01 PM
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
            <%@ page import="java.text.DateFormatSymbols" %>
            <%@ page import="toughudder.*" %>
            <% Cart cart = (Cart) session.getAttribute(Controller.CART);
                String cost = cart.getCostFormat().format(cart.getTotalCost());
            %>
            <p>
                Your total cost is <%= cost%>. Please enter your credit card information below.
            </p>
            <!-- First check for all of the required input. -->
            <% CreditCardInfoBean ccib = (CreditCardInfoBean) request.getAttribute("ccData");
                if (ccib == null) {
                    ccib = new CreditCardInfoBean();
                }
                Object error = request.getAttribute("error");
                if (error != null) {
            %>
            <p>There was a problem with the entered payment info:<br /><%= error.toString()%></p>
            <br />
            <% } %>
            <form action="Controller?action=submit" method="post">
                <fieldset>
                    <legend>Credit Card Information</legend>
                    <table>
                        <tr>
                            <td>Card Type</td>
                            <td>
                                <% String[] cardTypes = new String[]{
                                        "VISA", "MasterCard", "Discover", "American Express"};
                                    for (String type : cardTypes) {
                                        if (type.equals(ccib.getType())) {
                                %>
                                <input type="radio" name="cardType" value="<%= type%>" checked="checked"/><%= type%>
                                <%    } else {
                                %>
                                <input type="radio" name="cardType" value="<%= type%>" /><%= type%>
                                <%    }
                                    }
                                %>
                            </td>
                        </tr>
                        <tr>
                            <td>Name on Card</td>
                            <td><input type="text" name="cardName" size="24" value="<%= ccib.getName() == null ? "" : ccib.getName()%>"/></td>
                        </tr>
                        <tr>
                            <td>Card Number</td>
                            <td><input type="text" name="cardNum" size="16" value="<%= ccib.getNumber() == null ? "" : ccib.getNumber()%>"/></td>
                        </tr>
                        <tr>
                            <td>Card Expiry Date</td>
                            <td>
                                <select name="expYear">
                                    <% for (int yr = 2014; yr < 2026; ++yr) {
                                            if (Integer.valueOf(yr).equals(ccib.getExpiryYear())) {
                                    %>
                                    <option selected="selected"><%= yr%></option>
                                    <%    } else {
                                    %>
                                    <option><%= yr%></option>
                                    <%    }
                                        }
                                    %>
                                </select>
                                <select name="expMonth">
                                    <% for (String m : DateFormatSymbols.getInstance().getMonths()) {
                                            if (m.trim().length() > 0) {
                                                if (m.equals(ccib.getExpiryMonth())) {
                                    %>
                                    <option selected="selected"><%= m%></option>
                                    <%       } else {
                                    %>
                                    <option><%= m%></option>
                                    <%       }
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <br />
                <table cellpadding="10">
                    <tr>
                        <th>
                            <button class="event-submit" id="cart-btn" type="button" onclick="location.href = 'Controller?action=cart'">Edit Cart</button>
                        </th>
                        <th>
                            <input class="event-submit" id="complete-btn" type="button" value="Complete Registration" />
                        </th>
                    </tr>
                </table>
            </form>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
