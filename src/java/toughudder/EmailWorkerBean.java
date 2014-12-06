package toughudder;

import edu.jhu.email.MailUtilGmail;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class EmailWorkerBean {

    private static final String BR = "<br />";

    private static final String FROM = "registration@toughudder.com";
    private static final String SUBJ = "Tough Udder Registration";

    private final String error;

    private final String msg;

    public EmailWorkerBean(HttpServletRequest request, CreditCardInfoBean ccib) {

        StringBuilder error = new StringBuilder();
        StringBuilder msg = new StringBuilder("<html><body>");
        Cart cart = (Cart) request.getSession().getAttribute(Controller.CART);
        String email = request.getParameter(Controller.EMAIL);

        // check email pattern
        String stringPattern = "[_a-z0-9\\-\\+]+(?:\\.[_a-z0-9\\-\\+]+)*@(?:[a-z0-9\\-]+(?:\\.[a-z0-9\\-]+)*\\.[a-z]{2,})"; // http://en.wikipedia.org/wiki/Email_address"
        Pattern pattern = Pattern.compile(stringPattern, Pattern.CASE_INSENSITIVE);
        if (!pattern.matcher(email).matches()) {
            this.msg = new String();
            this.error = "Email format not standard.";
            return;
        }

        msg.append("<p>Your Tough Udder registration(s) have been processed.");
        msg.append("Below you will find a summary of your registration and ");
        msg.append("payment information.</p>").append(BR);

        msg.append("<table cellpadding='5'>");
        msg.append("<tr><th>Event</th><th>Date</th><th>Location</th>");
        msg.append("<th>Cost</th></tr>");
        for (Event evt : cart.getEvents()) {
            msg.append("<tr><td>").append(evt.getName()).append("</td>");
            msg.append("<td>").append(evt.getDate());
            msg.append("</td>");
            msg.append("<td>").append(evt.getLocation()).append("</td>");
            msg.append("<td align='right'>");
            msg.append(cart.getCostFormat().format(evt.getCost()));
            msg.append("</td></tr>");
        }
        msg.append("<tr><th></th><th></th><th>Total</th><th>");
        msg.append(cart.getCostFormat().format(cart.getTotalCost()));
        msg.append("</th></tr>").append(BR);

        msg.append("Your total was charged to the following credit card:");
        msg.append(BR).append("Cardholder: ").append(ccib.getName()).append(BR);
        msg.append("Card type/number: ").append(ccib.getType()).append("/");
        msg.append(ccib.getRedactedNumber()).append(BR).append("Expires: ");
        msg.append(ccib.getExpiryMonth()).append(" ");
        msg.append(ccib.getExpiryYear());

        msg.append("</body></html>");

        try {
            MailUtilGmail.sendMail(email, FROM, SUBJ, msg.toString(), true);
        } catch (Throwable t) {
            error.append(t.getMessage());
        }
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
