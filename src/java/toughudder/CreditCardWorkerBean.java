package toughudder;

import javax.servlet.http.HttpServletRequest;

public class CreditCardWorkerBean {

   private static final String BR = "<br />";

   private final String error;

   private final CreditCardInfoBean ccib;

   public CreditCardWorkerBean(HttpServletRequest request) {
      StringBuilder error = new StringBuilder();

      String cardName = request.getParameter("cardName");
      if (cardName == null || cardName.length() < 1) {
         error.append("Please enter the cardholder's name.").append(BR);
      }
      String cardType = request.getParameter("cardType");
      if (cardType == null || cardType.length() < 1) {
         error.append("Please choose a credit card type.").append(BR);
      }
      String cardNum = request.getParameter("cardNum");
      if (cardNum == null || cardNum.length() < 1) {
         error.append("Please enter a credit card number.").append(BR);
      }
      String expYear = request.getParameter("expYear");
      if (expYear == null || expYear.length() < 1) {
         error.append("Please choose an expiry year.").append(BR);
      }
      String expMonth = request.getParameter("expMonth");
      if (expMonth == null || expMonth.length() < 1) {
         error.append("Please choose an expiry month.").append(BR);
      }
      String address = request.getParameter("address");
      if (address == null || address.length() < 1) {
         error.append("Please enter the billing address.").append(BR);
      }
      String city = request.getParameter("city");
      if (city == null || city.length() < 1) {
         error.append("Please enter the billing address city.").append(BR);
      }
      String state = request.getParameter("state");
      if (state == null || state.length() != 2) {
         error.append("Please enter the billing address state.").append(BR);
      }
      String zip = request.getParameter("zip");
      if (zip == null || zip.length() != 5) {
         error.append("Please enter the billing address zip code.").append(BR);
      }
      ccib = new CreditCardInfoBean(
            cardName, cardType, cardNum, expYear, expMonth, address, city,
            state, zip);
      if (!ccib.isValid()) {
         error.append("Credit card data is invalid.").append(BR);
      }

      this.error = error.toString();
   }

   public CreditCardInfoBean getData() {
      return ccib;
   }

   public String getError() {
      return error;
   }

   public boolean isError() {
      return error.length() > 0;
   }
}
