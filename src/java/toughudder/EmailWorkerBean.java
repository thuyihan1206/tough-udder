package toughudder;

import javax.servlet.http.HttpServletRequest;

import edu.jhu.email.MailUtilGmail;

public class EmailWorkerBean {

    private static final String BR = "<br />";

    private static final String FROM = "swdevconf@jhu.edu";
    private static final String SUBJ = " JHU SW Dev Conf Registration";

    private final String error;

    private final String msg;

    public EmailWorkerBean(HttpServletRequest request, CreditCardInfoBean ccib) {
        StringBuilder error = new StringBuilder();
        StringBuilder msg = new StringBuilder("<html><body>");
//
//      // Build and send a registration summary email.
//      RegistrationDataBean rdb =
//            (RegistrationDataBean)request.getSession().getAttribute(
//                  RegistrationServlet.REG_DATA_ATTR);
//
//      msg.append("<p>Thank you for registering for the 2014 JHU Software ");
//      msg.append(" Development Conference, ").append(rdb.getName());
//      msg.append("!</p>");
//
//      msg.append("<p>Your registration status is a(n) ");
//      msg.append(rdb.getStatus()).append(".</p>").append(BR);
//
//      msg.append("<table cellpadding='5'>");
//      msg.append("<col align='left' width='80%' />");
//      msg.append("<col align='right' width='20%' />");
//      msg.append("<tr><th align='left'>Registered Seminars</th>");
//      msg.append("<th align='left'>Cost</th></tr>");
//      for (String s : rdb.getSeminars()) {
//         msg.append("<tr><td>").append(s).append("</td>");
//         msg.append("<td align='right'>").append(rdb.getSeminarPrice());
//         msg.append("</td></tr>");
//      }
//      if (rdb.getHotelPrice() != null || rdb.getParkPrice() != null) {
//         msg.append("<tr><th align='left'>Accommodation Options</th></tr>");
//         if (rdb.getHotelPrice() != null) {
//            msg.append("<tr><td>Hotel Stay</td><td align='right'>");
//            msg.append(rdb.getHotelPrice()).append("</td></tr>");
//         }
//         if (rdb.getParkPrice() != null) {
//            msg.append("<tr><td>Campus Parking Permit</td><td align='right'>");
//            msg.append(rdb.getParkPrice()).append("</td></tr>");
//         }
//      }
//      msg.append("<tr><th align='right'>Total</th>");
//      msg.append("<th>").append(rdb.getTotalPrice());
//      msg.append("</th></tr>").append(BR);
//
//      msg.append("Your total was charged to the following credit card:");
//      msg.append(BR).append("Cardholder: ").append(ccib.getName()).append(BR);
//      msg.append("Card type/number: ").append(ccib.getType()).append("/");
//      msg.append(ccib.getRedactedNumber()).append(BR).append("Expires: ");
//      msg.append(ccib.getExpiryMonth()).append(" ");
//      msg.append(ccib.getExpiryYear());

        msg.append("</body></html>");

//      try {
//         MailUtilGmail.sendMail(
//               rdb.getEmail(), FROM, SUBJ, msg.toString(), true);
//      }
//      catch (Throwable t) {
//         error.append(t.getMessage());
//      }
        this.msg = msg.toString();
        this.error = error.toString();
    }

    public String getMsg() {
        return msg;
    }

    public String getError() {
        return error;
    }

    public boolean isError() {
        return error.length() > 0;
    }
}
