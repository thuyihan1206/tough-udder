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
            <% Cart cart = (Cart)session.getAttribute(Controller.CART);
               String cost = cart.getCostFormat().format(cart.getTotalCost());
            %>
            <p>
                Your total cost is <%= cost %>. Please enter your credit card information below.
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
            <form action="Controller?action=complete" method="post"> 
                <fieldset>
                    <legend>Billing Address</legend>
                    <table>
                        <tr>
                            <td>Address</td>
                            <td><input type="text" name="address" size="30" value="<%= ccib.getAddress() == null ? "" : ccib.getAddress() %>"/></td>
                        </tr>
                        <tr>
                            <td>City</td>
                            <td><input type="text" name="city" size="20" value="<%= ccib.getCity() == null ? "" : ccib.getCity() %>"/></td>
                        </tr>
                        <tr>
                            <td>State</td>
                            <% String[] states = new String[] {
                                     "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC",
                                     "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN",
                                     "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN",
                                     "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ",
                                     "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI",
                                     "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA",
                                     "WI", "WV", "WY"};
                            %>
                            <td>
                                <select name="state">
                                    <% for (String st : states) {
                                 if (st.equals(ccib.getState())) { %>
                                    <option selected="selected"><%= st %></option>
                                    <%    } else { %>
                                    <option><%= st%></option>
                                    <%    }
                                       }
                                    %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>ZIP</td>
                            <td><input type="text" name="zip" size="5" value="<%= ccib.getZip() == null ? "" : ccib.getZip() %>"/></td>
                        </tr>
                    </table>
                </fieldset>
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
                <fieldset>
                    <legend>Email Address</legend>
                    <input type="text" name="email" size="30" value="<%= request.getParameter(Controller.EMAIL) == null ? "" : request.getParameter(Controller.EMAIL) %>" />
                </fieldset>
                <br />
                <table cellpadding="10">
                    <tr>
                        <th>
                            <button class="event-submit" id="cart-btn" type="button" onclick="location.href = 'Controller?action=cart'">Edit Cart</button>
                        </th>
                        <th>
                            <input class="event-submit" id="complete-btn" type="submit" value="Complete Registration" />
                        </th>
                    </tr>
                </table>
            </form>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
